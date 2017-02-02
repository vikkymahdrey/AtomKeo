package com.agiledge.atom.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import com.agiledge.atom.constants.CoreAtomConstants;
import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.EmployeeDao;
import com.agiledge.atom.dao.intfc.LoginDao;
import com.agiledge.atom.dao.intfc.RoleDao;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.service.intfc.LoginService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
	
	@Autowired
	private LdapTemplate ldapTemplate;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private LoginDao loginDao;

	public Employee validateUser(String userName, String password)
			throws Exception {
		//SettingsConstant.setCompany();
		logger.debug("UUUUUUUUUUUU"+userName);
		logger.debug("Passss"+password);
		String validationType = SettingsConstant.validationFlag;
		boolean userValidated = false;
		boolean isLocalAuth = false;
		String uid = "";
		Employee employee = null;
		
		try{
           employee = checkIsLocalAuthentication(userName, password);
           if(employee != null) isLocalAuth = true;
		}catch(Exception ex){
			logger.error("Error in fetching the employee ",ex);
		}if(!isLocalAuth){
			logger.debug("user has to be aiuthenticated against third party");
			logger.debug("validation type ::: "+validationType);
			if (validationType
					.equalsIgnoreCase(CoreAtomConstants.VALIDATION_TYPE_UAT)) {
				userValidated = true;
			} else if (validationType
					.equalsIgnoreCase(CoreAtomConstants.VALIDATION_TYPE_API)) {
				try{
				uid = authenticateWithAPI(userName,password);
				if(uid == null)
					userValidated = false;
				else
					userValidated = true;
				}catch(Exception ex){
					logger.error("Error in validating the user with API ",ex);
				}
			} else if (validationType
					.equalsIgnoreCase(CoreAtomConstants.VALIDATION_TYPE_LDAP)) {
				userValidated = authenticateWithLDAP(userName, password);
			} else {
	
			}	
			logger.debug("is user vallidated ::: "+userValidated);
		}
		if(isLocalAuth || userValidated){
			if(!uid.isEmpty())
				employee = employeeDao.getEmployeeByUserName(uid);
			else 
				employee = employeeDao.getEmployeeByUserName(userName);
		}
		
		return employee;
	}

		public boolean authenticateUserWIthSSO(String userId) throws Exception{
			return loginDao.authenticateUserWIthSSO(userId);	
		}


	private String authenticateWithAPI( String userName, String password) {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource webResource = client
					.resource("https://www.peopleworks.ind.in/pwwebApi/api/Logon");
			JSONObject js = new JSONObject();
			js.put("ClientCodeSlashName", userName);
			js.put("Password", password);
			ClientResponse response = webResource.type(
					MediaType.APPLICATION_JSON).put(ClientResponse.class,
					js.toString());
			if (response.getStatus() == 200) {
				JSONObject jso = new JSONObject(
						response.getEntity(String.class));
				String uid = (String) jso.get("EmployeeID");
				logger.debug("Validating the user with API successful and the uid is ::: "+uid);
				return uid;
			}
			logger.debug("Validating the user with API failed");
		return null;
	}

	private boolean authenticateWithLDAP(String userName, String password) {
		try {
		    this.ldapTemplate.authenticate("", "(uid="+userName+")", password);
		    logger.debug("Validating the user with LDAP is successful");
			return true;
		} catch (Exception e) {
			logger.error("Error in validating user with LDAP ",e);
			return false;
		}
	}

	private Employee checkIsLocalAuthentication(String userName, String password) throws Exception {
		Employee emp = employeeDao.getEmployeeByUserName(userName);
			if (emp != null
					&& (emp.getAuthType().equalsIgnoreCase("l") || emp
							.getExternalUser().equalsIgnoreCase("yes"))) {
				if (emp.getLoginId().equals(userName)
						&& emp.getPassword().equals(password)) {
					if (emp.getActive() == 1) {
						logger.debug("External user " + userName +" authenticated successfully and is active");
						return emp;
					} else {
						logger.debug("External user " + userName +" is inactive");
						return null;
					}
				} else {
					logger.debug("User "+userName+" is not an external user");
				}
			}
		return null;
	}

	public boolean validateUserWithAPI(String sessionId, String userId,
			String clientCode) {
		boolean result=false;
		try{	
			String reqString="https://www.peopleworks.ind.in/pwwebapi/api/Validation/validateTravelUser?id="+URLEncoder.encode(sessionId, "UTF-8")+"&userId="+userId+"&clientCode="+clientCode;
			DefaultHttpClient client1 = new DefaultHttpClient();	
			HttpGet request = new HttpGet(reqString);
			System.out.println("link"+reqString);
			HttpResponse response1 = client1.execute(request);
			System.out.println(response1.getStatusLine());
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response1.getEntity().getContent()));
			String line = "";
			String nLine = "";
			while ((line = rd.readLine()) != null) {
				nLine += line;
			}
			System.out.println(nLine);
			if(nLine.equalsIgnoreCase("true"))
			{
			result= true;	
			}
		} catch (Exception e) {
			System.out.println("Error" + e);
		}	
		return result;
		}

	public String getLinksAndSubLinks(Role role) throws Exception {
		return roleDao.gerUserMenuByRole(role);
	}

	public void SetUserNamePassword(String username, String password) throws Exception {
		loginDao.SetUserNamePassword(username,password);
		
	}

	public boolean checkUser() throws Exception {
		return loginDao.checkUser();
	}
	
}