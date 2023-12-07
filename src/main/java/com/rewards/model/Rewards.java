package com.rewards.model;

import java.math.BigDecimal;

public class Rewards {

	private Integer customerId;
	private BigDecimal lastMonthRewardPoints;
    private BigDecimal lastSecondMonthRewardPoints;
    private BigDecimal lastThirdMonthRewardPoints;
    private BigDecimal totalRewards;
    
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public BigDecimal getLastMonthRewardPoints() {
		return lastMonthRewardPoints;
	}
	public void setLastMonthRewardPoints(BigDecimal lastMonthRewardPoints) {
		this.lastMonthRewardPoints = lastMonthRewardPoints;
	}
	public BigDecimal getLastSecondMonthRewardPoints() {
		return lastSecondMonthRewardPoints;
	}
	public void setLastSecondMonthRewardPoints(BigDecimal lastSecondMonthRewardPoints) {
		this.lastSecondMonthRewardPoints = lastSecondMonthRewardPoints;
	}
	public BigDecimal getLastThirdMonthRewardPoints() {
		return lastThirdMonthRewardPoints;
	}
	public void setLastThirdMonthRewardPoints(BigDecimal lastThirdMonthRewardPoints) {
		this.lastThirdMonthRewardPoints = lastThirdMonthRewardPoints;
	}
	public BigDecimal getTotalRewards() {
		return totalRewards;
	}
	public void setTotalRewards(BigDecimal totalRewards) {
		this.totalRewards = totalRewards;
	}
    
    

}
