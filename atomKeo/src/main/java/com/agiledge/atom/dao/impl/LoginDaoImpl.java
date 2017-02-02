package com.agiledge.atom.dao.impl;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.persistence.Query;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.LoginDao;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Vendor;
import com.agiledge.atom.entities.Vendormaster;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
@Repository
public class LoginDaoImpl extends AbstractDao implements LoginDao {
	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class);
	
	public String username = "";
	public String password = "";
	public String uid = "";
	public String role = "";
	public String roleId = "";
	public String delegatedUId = "";
	public String delegatedUrole = "";
	public String delegatedUroleId = "";
	public String site = "";
	public String vendorId = "";
	static String validationType = SettingsConstant.validationFlag;
	
	public boolean authenticateUserWIthSSO(String userId) throws Exception {
					
			String query = "select e.id,NULLIF(e.site,'') site,e.usertype,e.role_id,rd.employeeId delegatedEmployeeId,rd.delegatedType,rd.delegatedRoleId from employee e left outer join ( select rd.employeeId, rd.delegatedEmployeeId as delegatedEmployeeId,e1.usertype as delegatedType,e1.role_id as delegatedRoleId from   roleDelegation rd left outer join employee e1 on e1.id=rd.employeeId  where rd.from_date<=curdate() and rd.to_date>=curdate() and rd.status='a'  ) as rd on rd.delegatedEmployeeId=e.id where e.loginId="+userId+"";
				String queryTest = query.replace("?", "'%s'");
				queryTest = String.format(queryTest,  uid);				
				Query q=getEntityManager().createNativeQuery(query);
				List<Object[]> oList=q.getResultList();
				for(Object[] o:oList){					uid = String.valueOf(o[0]);
					site = String.valueOf(o[1]);
					role = String.valueOf(o[2]);
					roleId = String.valueOf(o[3]);
					delegatedUId = String.valueOf(o[4]);
					delegatedUrole = String.valueOf(o[5]);
					delegatedUroleId = String.valueOf(o[6]);
					if (role.equalsIgnoreCase("v")) {
						Query q1=getEntityManager().createNativeQuery("select vendorMaster from vendor where empLinkId="+ uid + "");
						List<Object[]> objList=q1.getResultList();
						if(objList!=null && objList.size()>0){
						for(Object[] obj : objList) {
							vendorId = String.valueOf(obj[0]);
							logger.debug("VENDORIDDDDDDDDDDDDDDDD"+vendorId);
						}
					}	else
						{
						Query q2=getEntityManager().createNativeQuery("select id from vendorMaster order by id ");
						List<Object[]> obList=q1.getResultList();
						if(obList!=null && obList.size()>0){
							for(Object ob:obList){
								vendorId = String.valueOf(ob);
							}	
						}
					}
				}
					return true;	
			}
		
		return false;
	}

	public void SetUserNamePassword(String username, String password) throws Exception {
		this.username = username;
		this.password = password;
		
	}

	public boolean checkUser() throws Exception {
		
			logger.debug("Inside check User connection established");
			
					
				if (checkIsLocalAuthentication()==1 || authenticateUser()) {
					
					logger.debug("authentication success");
					
					String query = "";
					query = "select e.id,NULLIF(e.site,'') site,e.usertype,e.role_id,rd.employeeId delegatedEmployeeId,rd.delegatedType,rd.delegatedRoleId from employee e left outer join ( select rd.employeeId, rd.delegatedEmployeeId as delegatedEmployeeId,e1.usertype as delegatedType,e1.role_id as delegatedRoleId from   roleDelegation rd left outer join employee e1 on e1.id=rd.employeeId  where rd.from_date<=curdate() and rd.to_date>=curdate() and rd.status='a'  ) as rd on rd.delegatedEmployeeId=e.id where e.loginId=?";
					
					String queryTest = query.replace("?", "'%s'");
							queryTest = String.format(queryTest,  uid);
							
					List<Object[]> oList=null;
					logger.debug("uid is blank then :"+queryTest);
					Query q=getEntityManager().createNativeQuery(query);
						q.setParameter(1, username);
							oList=q.getResultList();
					
					
					/*if (uid.equalsIgnoreCase("")) {
						logger.debug("uid is blank then :"+queryTest);
					Query q=getEntityManager().createNativeQuery(query);
					q.setParameter(1, username);
						oList=q.getResultList();
						
					} else {
						logger.debug("uid is not blank then :" +queryTest);
						logger.debug("uid is not blank and id is "+uid);
						Query q1=getEntityManager().createNativeQuery(query);
						q1.setParameter(1, uid);
						oList=q1.getResultList();
					}*/
					logger.debug("After RS...");
					for(Object[] o :oList){					
						logger.debug(" ...........................");
						logger.debug("UIDDDDDDD "+String.valueOf(o[0]));
						uid = String.valueOf(o[0]);
						site =  String.valueOf(o[1]);
						role =  String.valueOf(o[2]);
						roleId =  String.valueOf(o[3]);
						delegatedUId =  String.valueOf(o[4]);
						delegatedUrole =  String.valueOf(o[5]);
						delegatedUroleId =  String.valueOf(o[6]);
						
						if (role.equalsIgnoreCase("v")) {
							Query q1=getEntityManager().createQuery("from Vendor v where v.employee.id="+ uid + " and v.status='active'");
							List<Vendor> vList=q1.getResultList();
							if(vList!=null && vList.size()>0){
							for(Vendor v: vList){
								vendorId = String.valueOf(v.getVendormaster().getId());
							}
						}else
							{
							Query q2=getEntityManager().createQuery("from Vendormaster vm where vm.status='active' order by vm.id ");
							List<Vendormaster> vmList=q2.getResultList();
							Vendormaster vm=vmList.get(0);
								vendorId = String.valueOf(vm.getId());
							
						}
					}
						return true;	
					}
					return false;
				}
				
				return false;

	}
	
	public int checkIsLocalAuthentication()  throws Exception{
		String query="from Employee e where e.loginId=:loginId";
		Query q=getEntityManager().createQuery(query);
			q.setParameter("loginId", username);
			
			 int flag=0;
		try
		{  
			Employee emp=(Employee) q.getResultList().get(0);
		if (emp!=null && (emp.getAuthType().equalsIgnoreCase("l") || emp.getExternalUser().equalsIgnoreCase("yes")) ) {
			logger.debug(".. use name : " + username  + " password : " + password   );	
			if (emp.getLoginId().equalsIgnoreCase(username) && emp.getPassword().equalsIgnoreCase(password)) {
				if (emp.getActive() == 1) {
					logger.debug("External success");
					flag= 1;
				} else {
					logger.debug("External failure " + emp);
					flag= -1;
				}
			} else {;
				logger.debug("External failure 0 " + emp);
			}
		}
	}catch(Exception e)
	{
	logger.error("Error in extrenal employee check",e);	
	}
		return flag;

	}
	
	public boolean authenticateUser()  throws Exception{
		String validationTypeeee=SettingsConstant.utiltiyRun;
		logger.debug("ValidationType"+validationType);
		if (validationType.equalsIgnoreCase("UAT"))
			return true;		
		if (validationType.equalsIgnoreCase("LDAP"))		
			return LDAPAuthentication();	
		else if (validationType.equalsIgnoreCase("API"))
			return APIAuthentication();
		return false;
	}
	
	public boolean LDAPAuthentication() throws Exception {

		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, "ldap://groupinfra.com:389");
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, "groupinfra\\" + username);
			env.put(Context.SECURITY_CREDENTIALS, password);
			DirContext ctx = new InitialDirContext(env);
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	public boolean APIAuthentication() throws Exception {
		try {
			
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource webResource = client.resource("https://www.peopleworks.ind.in/pwwebApi/api/Logon");
			JSONObject js = new JSONObject();
			js.put("ClientCodeSlashName", username);
			js.put("Password", password);
			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).put(ClientResponse.class,
					js.toString());
			System.out.println(response.getStatus());
			if (response.getStatus() == 200) {
				JSONObject jso = new JSONObject(
						response.getEntity(String.class));
				uid = (String) jso.get("EmployeeID");
				return true;
			}
		} catch (Exception e) {
			System.out.println("Error" + e);
		}
		return false;
	}	
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDelegatedUId() {
		return delegatedUId;
	}

	public void setDelegatedUId(String delegatedUId) {
		this.delegatedUId = delegatedUId;
	}

	public String getDelegatedUrole() {
		return delegatedUrole;
	}

	public void setDelegatedUrole(String delegatedUrole) {
		this.delegatedUrole = delegatedUrole;
	}

	public String getDelegatedUroleId() {
		return delegatedUroleId;
	}

	public void setDelegatedUroleId(String delegatedUroleId) {
		this.delegatedUroleId = delegatedUroleId;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getEmployeeDet(String empId) throws Exception {
		
		return null;
	}

}
