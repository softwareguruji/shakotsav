package com.krb.shakotsav.bean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class TableItemPK implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name="ref_table_id", nullable=false, columnDefinition="int(11)", foreignKey=@ForeignKey(name="fk_tbl_table_table_id"))
	private Table table;

	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name="ref_item_id", nullable=false, columnDefinition="int(11)", foreignKey=@ForeignKey(name="fk_tbl_item_table_id"))
	private Item item;

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	
}
