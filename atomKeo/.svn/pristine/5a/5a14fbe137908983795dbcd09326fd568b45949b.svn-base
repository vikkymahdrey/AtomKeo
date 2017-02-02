package com.agiledge.atom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.RoleDao;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.service.intfc.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleDao roleDao;
	
	public Role gerRoleByUserType(String userType) throws Exception {
		return roleDao.getRoleByUserType(userType);
	}

	public List<Role> getRoleList() throws Exception {
		return roleDao.getRoleList();
	}

	public Role getRoleOfEmployee(String roleId) throws Exception {
		return roleDao.getRoleById(roleId);
	}

}
