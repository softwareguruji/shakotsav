package com.krb.shakotsav.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.krb.shakotsav.bean.User;
import com.krb.shakotsav.service.UserService;


public class BaseController {

	@Autowired
	UserService userService;

	User setupBaseParameter(ModelAndView modelAndView, Principal principal){
		
		User userObj = null;
		
		//Adding UserName and Cart Item
		if(principal != null){
			//find and setup user object
			userObj = userService.findByUserName(principal.getName());
			
			//Setup User to display on screen
			modelAndView.addObject("username",principal.getName());
			
			if(userObj != null){
				if(userObj.getRoles() != null && userObj.getRoles().size()>0){
					modelAndView.addObject("role",userObj.getRoles());
				}
			}
			
		}
		
		return userObj;
	}
}
