package com.krb.shakotsav.controller;

import java.net.BindException;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.krb.shakotsav.bean.Item;
import com.krb.shakotsav.bean.Role;
import com.krb.shakotsav.bean.Table;
import com.krb.shakotsav.bean.User;
import com.krb.shakotsav.bean.definedentity.DefinedRole;
import com.krb.shakotsav.service.ItemService;
import com.krb.shakotsav.service.RoleService;
import com.krb.shakotsav.service.TableService;

@Controller
public class WebController extends BaseController{
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private TableService tableService;

	@Autowired
	private ItemService itemService;

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
	
	@RequestMapping(value="/tableManagement", method = RequestMethod.GET)
	public ModelAndView tableManagement(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		User userObj = setupBaseParameter(modelAndView, principal);
		
		List<Table> listTables = tableService.getByAll();
		modelAndView.addObject("tableObjs", listTables);
		
		modelAndView.setViewName("table-management");
		return modelAndView;
	}
	
	@RequestMapping(value="/tableManagementSubmit", method = RequestMethod.POST)
	public ModelAndView tableManagementSubmit(Principal principal, @ModelAttribute(name="tableName") String tableName, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		User userObj = setupBaseParameter(modelAndView, principal);
		
		Table tableObj =  tableService.findByTableName(tableName);
		if(tableObj != null){
			Errors errors = (Errors) new BindException("Table Name is already used");
			bindingResult.addAllErrors(errors);
			modelAndView.setViewName("table-management");
		}else{
			tableObj = new Table();
			tableObj.setTableName(tableName);
			tableService.save(tableObj);
			
			RedirectView redirectView = new RedirectView("tableManagement", true);
			modelAndView.setView(redirectView);
		}
		
		return modelAndView;
	}
	

	@RequestMapping(value="/itemManagement", method = RequestMethod.GET)
	public ModelAndView itemManagement(Principal principal){
		ModelAndView modelAndView = new ModelAndView();
		User userObj = setupBaseParameter(modelAndView, principal);
		
		List<Item> listItems = itemService.getByAll();
		modelAndView.addObject("itemObjs", listItems);
		
		modelAndView.setViewName("item-management");
		return modelAndView;
	}
	
	@RequestMapping(value="/itemManagementSubmit", method = RequestMethod.POST)
	public ModelAndView itemManagementSubmit(Principal principal, @ModelAttribute(name="itemName") String itemName, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		User userObj = setupBaseParameter(modelAndView, principal);
		
		Item itemObj =  itemService.findByItemName(itemName);
		if(itemObj != null){
			Errors errors = (Errors) new BindException("Item Name is already used");
			bindingResult.addAllErrors(errors);
			modelAndView.setViewName("item-management");
		}else{
			itemObj = new Item();
			itemObj.setItemName(itemName);
			itemService.save(itemObj);
			
			RedirectView redirectView = new RedirectView("itemManagement", true);
			modelAndView.setView(redirectView);
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value="/tableItemManagement", method = RequestMethod.GET)
	public ModelAndView tableItemManagementView(Principal principal, @ModelAttribute(name="tableId") Long tableId, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("tableObj", tableService.findById(tableId));
		modelAndView.addObject("itemList", itemService.getByAll());
		
		modelAndView.setViewName("table-item-management");
		
		return modelAndView;
	}

	@RequestMapping(value="/tableItemManagementSubmit", method = RequestMethod.POST)
	public ModelAndView tableItemManagementSubmit(Principal principal, @ModelAttribute(name="tableObj") Table tableObj, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();

		System.out.println(tableObj.getTableItems().size());
		//tableService.save(tableObj);
		//Table tableOldObj = tableService.findById(tableObj.getTableId());
		//System.out.println("size: "+tableObj.getTableItems().size());
		/*tableOldObj.setTableItems(tableObj.getTableItems());*/
		//tableService.save(tableOldObj);
		
		/*modelAndView.addObject("tableObj", tableOldObj);
		modelAndView.addObject("itemList", itemService.getByAll());*/
		
		modelAndView.setViewName("table-item-management");
		
		return modelAndView;
	}
}
