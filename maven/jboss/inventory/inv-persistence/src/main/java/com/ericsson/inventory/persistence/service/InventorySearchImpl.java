package com.ericsson.inventory.persistence.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ericsson.inventory.persistence.domain.InventoryItem;
import com.ericsson.inventory.persistence.domain.InventoryType;

@Stateless
public class InventorySearchImpl implements InventorySearch {

    @EJB
    private InventoryHolder holder;

    @Override
    public List<InventoryItem> list(InventoryType type, String nameTerm) {
        return this.holder.getAll().stream().filter(it -> (it.getType() == type) && it.getName().startsWith(nameTerm))
                .collect(Collectors.toList());
    }

}
