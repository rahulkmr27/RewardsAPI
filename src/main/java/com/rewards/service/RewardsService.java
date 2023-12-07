package com.rewards.service;

import com.rewards.model.Rewards;

public interface RewardsService {

	public Rewards getRewardsByCustomerId(Integer customerId);
}
