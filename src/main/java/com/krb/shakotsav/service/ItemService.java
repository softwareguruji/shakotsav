package com.krb.shakotsav.service;

import java.util.List;

import com.krb.shakotsav.bean.Item;

public interface ItemService {

	public Item findByItemName(String tableName);
	public Item save(Item table);
	public List<Item> getByAll();
	
}
