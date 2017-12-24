package com.krb.shakotsav.configuration.ajaxresponse;

import java.util.List;

public class TableListWithItemResponse {

	List<TableData> table;
	
	public List<TableData> getTable() {
		return table;
	}

	public void setTable(List<TableData> table) {
		this.table = table;
	}

	public class TableData{
		Long tableId;
		String tableName;
		List<ItemData> itemsData;
		public Long getTableId() {
			return tableId;
		}
		public void setTableId(Long tableId) {
			this.tableId = tableId;
		}
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		public List<ItemData> getItemsData() {
			return itemsData;
		}
		public void setItemsData(List<ItemData> itemsData) {
			this.itemsData = itemsData;
		}

		
	}
	
	public class ItemData{
		Long itemId;
		String itemName;
		String status;
		public Long getItemId() {
			return itemId;
		}
		public void setItemId(Long itemId) {
			this.itemId = itemId;
		}
		public String getItemName() {
			return itemName;
		}
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
		
	}
}
