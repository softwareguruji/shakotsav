package com.krb.shakotsav.bean.definedentity;

public enum DefinedRole {

	SUPER_ADMIN("Super Admin"), 
	ADMIN("Admin"), 
	SUPPLIER("Supplier"), 
	TABLE_OWNER("Table Owner");
	
	private String roleName;
	
	private DefinedRole(String roleName) {
		this.roleName = roleName;
	}
	
	public String getRoleName(){
		return this.roleName;
	}
	
}
