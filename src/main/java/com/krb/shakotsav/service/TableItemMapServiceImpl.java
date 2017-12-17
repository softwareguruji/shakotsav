package com.krb.shakotsav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.repository.TableItemMapRepository;

@Service("TableItemMapService")
public class TableItemMapServiceImpl implements TableItemMapService {

	@Autowired
	private TableItemMapRepository tableItemMapRepository;
	
}
