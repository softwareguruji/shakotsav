package com.krb.shakotsav.service;

import com.krb.shakotsav.bean.TableItemMap;

public interface TableItemMapService {

	public void save(TableItemMap tableItemMapObj);
	
	public void delete(TableItemMap tableItemMapObj);
	
	public void makeRequestForItemByTable(Long tableId, Long itemId);
}
