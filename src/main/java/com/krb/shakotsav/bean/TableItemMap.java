package com.krb.shakotsav.bean;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@javax.persistence.Table(name = "table_item_map")
public class TableItemMap {

	@EmbeddedId
	private TableItemPK tableItem;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ref_status_id", nullable=false)
	private Status status;

	

	public TableItemPK getTableItem() {
		return tableItem;
	}

	public void setTableItem(TableItemPK tableItem) {
		this.tableItem = tableItem;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	
}
