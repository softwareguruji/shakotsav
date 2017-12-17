package com.krb.shakotsav.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krb.shakotsav.bean.Role;
import com.krb.shakotsav.repository.RoleRepository;

@Service("RoleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role findByRoleName(String RoleName) {
		List<Role> roles = roleRepository.findByRole(RoleName);
		if(roles.size()>0){
			return roles.get(0);
		}else{
			return null; 
		}
	}
}
