package com.krb.shakotsav.service;

import com.krb.shakotsav.bean.User;

public interface UserService {

	void save(User user);
	User findByUserName(String userName);
	
}
