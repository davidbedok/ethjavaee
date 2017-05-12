package com.ericsson.inventory.ejbservice.cost;

import com.ericsson.inventory.ejbservice.qualifier.Discount;

@Discount
public class PromotionalCostServiceImpl implements CostService {

	private static final float DISCOUNT_PERCENT = 20;

	@Override
	public int getCost(int originalValue) {
		return Math.round(originalValue * (1 - (DISCOUNT_PERCENT / 100)));
	}

}
