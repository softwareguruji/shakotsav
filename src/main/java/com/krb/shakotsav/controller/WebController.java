package com.krb.shakotsav.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.krb.shakotsav.bean.Role;
import com.krb.shakotsav.bean.User;
import com.krb.shakotsav.bean.definedentity.DefinedRole;
import com.krb.shakotsav.service.RoleService;

@Controller
public class WebController extends BaseController{
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value={"/", "/loginSubmit"}, method = RequestMethod.POST)
	public ModelAndView loginSubmit(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@RequestMapping(value="/registrationSubmit", method = RequestMethod.POST)
	public ModelAndView registrationSubmitPost(@Valid User user, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();

		//Setup Default Table Owner Role
		Role roleObj = roleService.findByRoleName(DefinedRole.TABLE_OWNER.getRoleName());		
		Set<Role> roles = new HashSet<>();
		roles.add(roleObj);
		user.setRoles(roles);
		
		userService.save(user);
		
		RedirectView redirectView = new RedirectView("home", true);
		modelAndView.setView(redirectView);
		return modelAndView;
	}
	
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public ModelAndView home(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		
		System.out.println(principal.getName());
		User userObj = setupBaseParameter(modelAndView, principal);
		System.out.println(userObj.getEmail());
		
		modelAndView.setViewName("home");
		return modelAndView;
	}
	
	
}
