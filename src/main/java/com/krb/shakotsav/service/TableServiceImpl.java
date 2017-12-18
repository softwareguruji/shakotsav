package com.krb.shakotsav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.bean.Table;
import com.krb.shakotsav.repository.TableRepository;

@Service("tableService")
public class TableServiceImpl implements TableService{

	@Autowired
	private TableRepository tableRepository;

	@Override
	public Table findByTableName(String tableName) {
		List<Table> listTables = tableRepository.findByTableName(tableName);
		if(listTables != null && !listTables.isEmpty()){
			return listTables.get(0);
		}
		
		return null;
	}

	@Override
	public Table save(Table table) {
		
		table = tableRepository.save(table);
		return table;
	}

	@Override
	public List<Table> getByAll() {
		return tableRepository.findAll();
	}
	
}
