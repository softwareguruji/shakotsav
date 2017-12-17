package com.krb.shakotsav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.repository.ItemRepository;

@Service("ItemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;
	
}
