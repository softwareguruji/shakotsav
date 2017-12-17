package com.krb.shakotsav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.bean.User;
import com.krb.shakotsav.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void save(User user){
		userRepository.save(user);
	}

	@Override
	public User findByUserName(String userName) {
		List<User> users = userRepository.findByUserName(userName);
		if(users != null && !users.isEmpty()){
			return users.get(0);
		}else{
			return null;
		}
	}
	

}
