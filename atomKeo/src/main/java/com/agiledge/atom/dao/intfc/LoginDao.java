package com.agiledge.atom.dao.intfc;

public interface LoginDao {

	boolean authenticateUserWIthSSO(String userId) throws Exception;
	void SetUserNamePassword(String username, String password) throws Exception;;
	boolean checkUser() throws Exception;
	public int checkIsLocalAuthentication() throws Exception;
	public boolean authenticateUser()  throws Exception;
	public boolean LDAPAuthentication() throws Exception;
	public boolean APIAuthentication() throws Exception;
	public String getUid();
	public void setUid(String uid);
	public String getRole();
	public void setRole(String role);
	public String getRoleId();
	public void setRoleId(String roleId);
	public String getSite();
	public void setSite(String site);
	public String getVendorId();
	public void setVendorId(String vendorId);
	public String getDelegatedUroleId();
	public void setDelegatedUroleId(String delegatedUroleId);
	public String getDelegatedUrole();
	public void setDelegatedUrole(String delegatedUrole);
	public String getDelegatedUId();
	public void setDelegatedUId(String delegatedUId);
	
	
}
