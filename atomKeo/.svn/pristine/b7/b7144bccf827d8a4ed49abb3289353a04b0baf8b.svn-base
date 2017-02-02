package com.agiledge.atom.service.intfc;

import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Role;

public interface LoginService {

	public Employee validateUser(String userName, String password) throws Exception;
	public boolean validateUserWithAPI(String sessionId, String userId,
			String clientCode);
	public boolean authenticateUserWIthSSO(String userId) throws Exception;
	public String getLinksAndSubLinks(Role role) throws Exception;
	public void SetUserNamePassword(String username, String password) throws Exception;
	public boolean checkUser() throws Exception;
}
