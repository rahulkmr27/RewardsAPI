package com.rewards.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewards.constants.ApiConstants;
import com.rewards.entity.Transaction;
import com.rewards.model.Rewards;
import com.rewards.repository.TransactionRepositroy;

@Service
public class RewardsServiceImpl implements RewardsService{

	@Autowired
	private TransactionRepositroy transactionRepository;

	public Rewards getRewardsByCustomerId(Integer customerId) {

		// At this point not considering the time stamp in day  just going with a simple date for sorting of the transactions
		
		//Step 1: pull all transactions associated to Customer
		List<Transaction> transactionsForCustomer = transactionRepository.findAllByCustomerId(customerId);
		
		//Step 2: pull all transactions for the last month
		List<Transaction> currentMonthTransactions = transactionsForCustomer.stream()
				.filter(x-> x.getTransactionDate().isAfter(LocalDate.now().minusMonths(ApiConstants.firstMonth)))
				.toList();
		//Step 3: pull all transactions for the last second month
		List<Transaction> secondMonthTransactions =  filterTrnasctionsInMonthRange(transactionsForCustomer,ApiConstants.firstMonth,
				ApiConstants.secondMonth);
		//Step 4: pull all transactions for the last third month
		List<Transaction> thridMonthTransactions = filterTrnasctionsInMonthRange(transactionsForCustomer,ApiConstants.secondMonth,
				ApiConstants.thirdMonth); 

		//Step 5: calculate rewards for each month
		BigDecimal lastMonthRewardPoints = getRewardsPerMonth(currentMonthTransactions);
		BigDecimal lastSecondMonthRewardPoints = getRewardsPerMonth(secondMonthTransactions);
		BigDecimal lastThirdMonthRewardPoints = getRewardsPerMonth(thridMonthTransactions);

		Rewards customerRewards = new Rewards();
		customerRewards.setCustomerId(customerId);
		customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
		customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
		customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
		customerRewards.setTotalRewards(lastMonthRewardPoints.add(lastSecondMonthRewardPoints)
				.add(lastSecondMonthRewardPoints));

		return customerRewards;

	}


	private List<Transaction> filterTrnasctionsInMonthRange(List<Transaction> transactionsForCustomer, Long minMonths, Long maxMonths) {
		return transactionsForCustomer.stream()
				.filter(x-> x.getTransactionDate().isBefore(LocalDate.now().minusMonths(minMonths)))
				.filter(x-> x.getTransactionDate().isAfter(LocalDate.now().minusMonths(maxMonths)))
				.toList();
	}


	private BigDecimal getRewardsPerMonth(List<Transaction> transactions) {
		  
		  return transactions.stream().map(transaction ->
		  calculateRewards(transaction)).reduce(BigDecimal.ZERO, BigDecimal::add);
	  }
	 
	private boolean isAmountGreater(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2)>0;
	}
	
	private boolean isAmountLessThanOrEqual(BigDecimal value1, BigDecimal value2) {
		return value1.compareTo(value2)<=0;
	}
	
	private BigDecimal calculateRewards(Transaction trans) {
		
		if (isAmountGreater(trans.getTransactionAmount() , ApiConstants.firstRewardLimit)
				&& isAmountLessThanOrEqual(trans.getTransactionAmount(),ApiConstants.secondRewardLimit)) {
			return trans.getTransactionAmount().subtract(ApiConstants.firstRewardLimit).setScale(2);
		} else if (isAmountGreater(trans.getTransactionAmount() , ApiConstants.firstRewardLimit)) {
			return (trans.getTransactionAmount().subtract(ApiConstants.secondRewardLimit).setScale(2)).multiply(BigDecimal.valueOf(2))
					.add(ApiConstants.secondRewardLimit.subtract(ApiConstants.firstRewardLimit));
		} else
			return BigDecimal.ONE;

	}
}
