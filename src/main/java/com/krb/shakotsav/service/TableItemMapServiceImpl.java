package com.krb.shakotsav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.bean.Item;
import com.krb.shakotsav.bean.Status;
import com.krb.shakotsav.bean.Table;
import com.krb.shakotsav.bean.TableItemMap;
import com.krb.shakotsav.bean.TableItemPK;
import com.krb.shakotsav.repository.TableItemMapRepository;

@Service("TableItemMapService")
public class TableItemMapServiceImpl implements TableItemMapService {

	@Autowired
	private TableItemMapRepository tableItemMapRepository;

	@Override
	public void save(TableItemMap tableItemMapObj) {
		tableItemMapRepository.save(tableItemMapObj);
	}

	@Override
	public void delete(TableItemMap tableItemMapObj) {
		tableItemMapRepository.delete(tableItemMapObj);
	}

	@Override
	public void makeRequestForItemByTable(Long tableId, Long itemId) {
			
		TableItemPK tipk = new TableItemPK();
		Item it = new Item();
		it.setItemId(itemId);
		tipk.setItem(it);
		Table  tb = new Table();
		tb.setTableId(tableId);
		tipk.setTable(tb);
		
		List<TableItemMap> lstTableItem = tableItemMapRepository.findByTableItem(tipk);
		if(lstTableItem != null && lstTableItem.size()>0){
			TableItemMap tiO = lstTableItem.get(0);
			Status requestStatus = new Status();
			requestStatus.setStatusId(2L);
			tiO.setStatus(requestStatus);
			
			save(tiO);
		}
		
			
			
	}

	@Override
	public void makeDispatchForItemByTable(Long tableId, Long itemId) {

		TableItemPK tipk = new TableItemPK();
		Item it = new Item();
		it.setItemId(itemId);
		tipk.setItem(it);
		Table  tb = new Table();
		tb.setTableId(tableId);
		tipk.setTable(tb);
		
		List<TableItemMap> lstTableItem = tableItemMapRepository.findByTableItem(tipk);
		if(lstTableItem != null && lstTableItem.size()>0){
			TableItemMap tiO = lstTableItem.get(0);
			Status requestStatus = new Status();
			requestStatus.setStatusId(3L);
			tiO.setStatus(requestStatus);
			
			save(tiO);
		}
	}
	
}
