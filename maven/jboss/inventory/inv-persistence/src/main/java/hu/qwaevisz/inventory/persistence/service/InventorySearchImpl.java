package hu.qwaevisz.inventory.persistence.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import hu.qwaevisz.inventory.persistence.domain.InventoryItem;
import hu.qwaevisz.inventory.persistence.domain.InventoryType;
import hu.qwaevisz.inventory.persistence.holder.InventoryHolder;

@Stateless
public class InventorySearchImpl implements InventorySearch {

	@EJB
	private InventoryHolder holder;

	@Override
	public List<InventoryItem> list(InventoryType type, String nameTerm) {
		return this.holder.getAll().stream().filter(it -> (it.getType() == type) && it.getName().startsWith(nameTerm)).collect(Collectors.toList());
	}

}
