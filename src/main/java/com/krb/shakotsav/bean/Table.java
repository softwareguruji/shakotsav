package com.krb.shakotsav.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@javax.persistence.Table(name = "tables")
public class Table {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "table_id", columnDefinition="int(11)")
	private Long tableId;
	
	@Column(name = "table_name")
	@NotEmpty(message = "*Please provide your table name")
	private String tableName;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy="tableItem.table")
	private List<TableItemMap> tableItems;

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

	public List<TableItemMap> getTableItems() {
		return tableItems;
	}

	public void setTableItems(List<TableItemMap> tableItems) {
		this.tableItems = tableItems;
	}
	
	
}
