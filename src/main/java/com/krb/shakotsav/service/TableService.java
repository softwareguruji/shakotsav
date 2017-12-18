package com.krb.shakotsav.service;

import java.util.List;

import com.krb.shakotsav.bean.Table;

public interface TableService {

	public Table findByTableName(String tableName);
	public Table save(Table table);
	public List<Table> getByAll();
}
