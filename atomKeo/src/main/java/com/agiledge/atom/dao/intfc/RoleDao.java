package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.Page;
import com.agiledge.atom.entities.Role;

public interface RoleDao {
	
	public String gerUserMenuByRole(Role role) throws Exception;
	public Role getRoleByUserType(String userType) throws Exception;
	public List<Role> getAllRoles() throws Exception;
	public Role addNewRole(Role role) throws Exception;
	public Role getRoleById(String roleId) throws Exception;
	public Role updateRole(Role role) throws Exception;
	public List<Role> getRoleList() throws Exception;
	

}
