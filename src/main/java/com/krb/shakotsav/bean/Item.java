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
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "item")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id", columnDefinition="int(11)")
	private Long itemId;
	
	@Column(name = "item_name")
	@NotEmpty(message = "*Please provide your item name")
	private String itemName;
	
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy="tableItem.item")
	private List<TableItemMap> tableItems;

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

	public List<TableItemMap> getTableItems() {
		return tableItems;
	}

	public void setTableItems(List<TableItemMap> tableItems) {
		this.tableItems = tableItems;
	}

	
}
