package com.krb.shakotsav.controller;

import java.net.BindException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.krb.shakotsav.bean.TableItemMap;
import com.krb.shakotsav.bean.TableItemPK;
import com.krb.shakotsav.bean.User;
import com.krb.shakotsav.bean.definedentity.DefinedRole;
import com.krb.shakotsav.configuration.ajaxresponse.MessageResponse;
import com.krb.shakotsav.configuration.ajaxresponse.TableListWithItemResponse;
import com.krb.shakotsav.configuration.ajaxresponse.TableListWithItemResponse.ItemData;
import com.krb.shakotsav.configuration.ajaxresponse.TableListWithItemResponse.TableData;
import com.krb.shakotsav.service.ItemService;
import com.krb.shakotsav.service.RoleService;
import com.krb.shakotsav.service.TableItemMapService;
import com.krb.shakotsav.service.TableService;

@Controller
public class WebController extends BaseController{
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private TableService tableService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private TableItemMapService tableItemMapService;
	
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
	public ModelAndView tableItemManagementSubmit(Principal principal, HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView();

		Table tableObj = tableService.findById(Long.parseLong(request.getParameter("tableId")));
		if(tableObj != null){
			//No record in list
			if(tableObj.getTableItems() == null || tableObj.getTableItems().size() == 0){
				for(String paraName: Collections.list(request.getParameterNames())){
					if(paraName.startsWith("checkbox_")){
						Long itemId = Long.parseLong(request.getParameter(paraName));
						
						TableItemMap tiM = new TableItemMap();
						TableItemPK tipk = new TableItemPK();
						tipk.setTable(tableObj);
						Item it = new Item();
						it.setItemId(itemId);
						tipk.setItem(it);
						tiM.setTableItem(tipk);
						
						List<TableItemMap> list = null;
						if(tableObj.getTableItems() != null){
							list = tableObj.getTableItems();
						}else{
							list = new ArrayList<TableItemMap>();
						}
						list.add(tiM);
						
						tableObj.setTableItems(list);
					}
				}
				tableService.save(tableObj);
			}else{ //Already some record in list. Need to update
				List<TableItemMap> list = tableObj.getTableItems();
				
				//Adding selected checkbox to database.
				for(String paraName: Collections.list(request.getParameterNames())){
					if(paraName.startsWith("checkbox_")){
						Long itemId = Long.parseLong(request.getParameter(paraName));
						
						boolean itemNotAvailableInList = true;
						for(TableItemMap tim : list){
							if(tim.getTableItem().getItem().equals(itemId)){
								itemNotAvailableInList = false;
							}
						}

						//If specific item is not available in list.
						if(itemNotAvailableInList){
							
							TableItemMap tiM = new TableItemMap();
							TableItemPK tipk = new TableItemPK();
							tipk.setTable(tableObj);
							Item it = new Item();
							it.setItemId(itemId);
							tipk.setItem(it);
							tiM.setTableItem(tipk);
							
							//list.add(tiM);
							tableItemMapService.save(tiM);
						}
						
						tableObj.setTableItems(list);
					}
				}
				
				//Removing unselected checkbox if any from database
				for (TableItemMap tim : list) {
					if(request.getParameter("checkbox_"+tim.getTableItem().getItem().getItemId()) == null){
						//list.remove(tim);
						tableItemMapService.delete(tim);
					}
				}
				
				//tableService.save(tableObj);
			}
		}
		
		modelAndView.addObject("tableObj", tableService.findById(Long.parseLong(request.getParameter("tableId"))));
		modelAndView.addObject("itemList", itemService.getByAll());
		
		modelAndView.setViewName("table-item-management");
		
		return modelAndView;
	}
	
	@RequestMapping(value="/tableItemPushPollAJAX", method = RequestMethod.GET)
	public ResponseEntity<?> tableItemPushPollAJAX(Principal principal, HttpServletRequest request){

		
		List<Table> allTables = tableService.getByAll();
		
		TableListWithItemResponse response = new TableListWithItemResponse();
		List<TableListWithItemResponse.TableData> listTDs = new ArrayList<>();

		for (Table table : allTables) {
			
			TableListWithItemResponse.TableData td = response.new TableData();
			td.setTableId(table.getTableId());
			td.setTableName(table.getTableName());
			
			List<TableListWithItemResponse.ItemData> itds = new ArrayList<>();
			
			for (TableItemMap tim : table.getTableItems()) {
				
				TableListWithItemResponse.ItemData itd = response.new ItemData();
				itd.setItemId(tim.getTableItem().getItem().getItemId());
				itd.setItemName(tim.getTableItem().getItem().getItemName());
				if(tim.getStatus() != null)
					itd.setStatus(tim.getStatus().getStatusName());
				
				itds.add(itd);
			}
			td.setItemsData(itds);
			
			listTDs.add(td);
		}
		response.setTable(listTDs);
		
		
		return ResponseEntity.ok(response);
	}

	@RequestMapping(value="/tableItemPushPollView", method = RequestMethod.GET)
	public ModelAndView tableItemPushPollView(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("table-item-pull-push");
		return modelAndView;
	}
	
	@RequestMapping(value="/tableItemPushPollViewForDispatcher", method = RequestMethod.GET)
	public ModelAndView tableItemPushPollViewForDispatcher(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("table-item-pull-push-dispatcher");
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/tableItemRequestForDeliver", method = RequestMethod.GET)
	public ResponseEntity<?> tableItemRequestForDeliver(Principal principal, HttpServletRequest request){

		String tableId = request.getParameter("tableId");
        String itemId = request.getParameter("itemId");
		
        System.out.println(tableId+" : "+itemId);
        
        tableItemMapService.makeRequestForItemByTable(Long.parseLong(tableId), Long.parseLong(itemId));

        MessageResponse mr = new MessageResponse();
        mr.setStatus("success");
        mr.setMessage("Request received. Dispatch will be initiated soon");
        
		return ResponseEntity.ok(mr);
	}
	
	
	@RequestMapping(value="/tableItemDispatched", method = RequestMethod.GET)
	public ResponseEntity<?> tableItemDispatched(Principal principal, HttpServletRequest request){

		String tableId = request.getParameter("tableId");
        String itemId = request.getParameter("itemId");
		
        System.out.println(tableId+" : "+itemId);
        
        tableItemMapService.makeDispatchForItemByTable(Long.parseLong(tableId), Long.parseLong(itemId));

        MessageResponse mr = new MessageResponse();
        mr.setStatus("success");
        mr.setMessage("Thanks for confirming request dispatch.");
        
		return ResponseEntity.ok(mr);
	}
}
