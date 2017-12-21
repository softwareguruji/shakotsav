package com.krb.shakotsav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.bean.Item;
import com.krb.shakotsav.repository.ItemRepository;

@Service("ItemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
	@Override
	public Item findByItemName(String itemName) {
		List<Item> listItems = itemRepository.findByItemName(itemName);
		if(listItems != null && !listItems.isEmpty()){
			return listItems.get(0);
		}
		
		return null;
	}

	@Override
	public Item save(Item itemObj) {
		
		itemObj = itemRepository.save(itemObj);
		return itemObj;
	}

	@Override
	public List<Item> getByAll() {
		return itemRepository.findAll();
	}
	
}
