package com.krb.shakotsav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.repository.TableRepository;

@Service("tableService")
public class TableServiceImpl implements TableService{

	@Autowired
	private TableRepository tableRepository;
	
}
