package com.ericsson.inventory.ejbservice.cost;

import com.ericsson.inventory.ejbservice.qualifier.Standard;

@Standard
public class NormalCostServiceImpl implements CostService {

	@Override
	public int getCost(int originalValue) {
		return originalValue;
	}

}
