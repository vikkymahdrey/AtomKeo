package com.agiledge.atom.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.config.files.SendMailFactory;
import com.agiledge.atom.constants.PasswordGenerator;
import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.LoginDao;
import com.agiledge.atom.dao.intfc.OtherDao;
import com.agiledge.atom.dto.EmployeeDto;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.mail.SendMail;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.service.intfc.LoginService;
import com.agiledge.atom.service.intfc.SettingsService;
import com.agiledge.atom.service.intfc.SiteService;


@Controller
@RequestMapping("/")
@SessionAttributes({"userLoggedIn","role","homePage","userMenu","status"})
public class LoginController{
	
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SettingsService settingsService;
	
	@Autowired
	private LoginDao loginDao;
	
	@Autowired
	private OtherDao otherDao;
	
	@Autowired
	private SiteService siteService;
			
	@RequestMapping(value= {"/","/CoreAtom","/atomKeo","/?"}, method=RequestMethod.GET)
	public ModelAndView showIndexPage(){
		return new ModelAndView("index");
	}
		
	@RequestMapping(value= {"/login"}, method=RequestMethod.POST)
	public ModelAndView loginUser(HttpServletRequest request, HttpSession session, HttpServletResponse response,RedirectAttributes redirectAttributes) throws Exception{
		logger.debug("login user");
		boolean needToChangePwd = false;
		String username = request.getParameter("uname") == null ? "" : request
				.getParameter("uname");
		String password = request.getParameter("pass") == null ? "" : request
				.getParameter("pass");
		String sessionId = request.getParameter("UniqueSession");
		String userId = request.getParameter("userid");
		String clientCode=request.getParameter("clientCode");
		String prePage =request.getParameter("prePage");
		logger.debug("PrePage Value IN "+prePage);
		Employee emp = null;
		boolean isAuthenticated = false;
		boolean needPwdChange = false;
        String userMenu = "";
		

		if(sessionId!=null && userId!=null && clientCode!=null)
		{	
			userId=userId.split("\\\\")[1];
			isAuthenticated=loginService.validateUserWithAPI(sessionId, userId,clientCode);
			if(!isAuthenticated)
			{
			  return new ModelAndView("redirect:/","message","Username / password is invalid");
			}
			else 
			{
				try {
					isAuthenticated = loginService.authenticateUserWIthSSO(userId);
				} catch (Exception e) {
					logger.error("Error in authenticating user with SSO ",e);
					 return new ModelAndView("redirect:/","message","Error in authenticating the user");
				}
			}	
			
		}
		else if (username.equals("") || password.equals("")) {
			return new ModelAndView("redirect:/","message","Invalid User Name/Password !");
			} else {			
				loginService.SetUserNamePassword(username, password);
				isAuthenticated = loginService.checkUser();
				logger.debug("Is Authenticated "+isAuthenticated);
		}
		
				
		if(isAuthenticated){
		String role = loginDao.getRole();
		String roleId = loginDao.getRoleId();
		logger.debug("uId AS "+loginDao.getUid());
		session.setAttribute("user", loginDao.getUid());
		/*Done by Vikky.... */
		EmployeeDto empDto = employeeService.getEmployeeAccurate(session.getAttribute("user").toString());
		Role roleById=employeeService.getRoleNamebyId(Integer.valueOf(roleId).intValue());
		session.setAttribute("userDto", empDto);

		 emp = employeeService.getEmployeeById(loginDao.getUid());	
		
		 /*As a reference be use...*/
		 session.setAttribute("userLoggedIn",emp);
		 session.setAttribute("homePage", roleById.getPage().getUrl());
				
		if (emp!=null && emp.getExternalUser()!=null && emp.getExternalUser().equalsIgnoreCase("yes")) {
			if (emp.getPwdChangedDate() == null	|| emp.getPwdChangedDate().equals("")) {
				needToChangePwd = true;
			} else if (new OtherFunctions().checkDate90(OtherFunctions.changeDateFromat(emp.getPwdChangedDate()))) {
				needToChangePwd = true;
			}
		}
		
		if (loginDao.getDelegatedUId()!= null	&& (!loginDao.getDelegatedUId().equals("null")) && (!loginDao.getDelegatedUId().equals(""))) {
			session.setAttribute("delegatedId", loginDao.getDelegatedUId());
			role = loginDao.getDelegatedUrole();
			roleId = loginDao.getDelegatedUroleId();
		}
		
		try {
		empDto.setUserType(role);
		empDto.setRoleId(Integer.parseInt(roleId));
		}catch(Exception e) {
			logger.error("Error in setting roles and roleid :"+ e);
		}

		logger.debug(" role: " + role);

		session.setAttribute("role", role);
		session.setAttribute("roleId", roleId);
		
		/*As a reference be use....*/
		try{
			Role empRole=employeeService.getRoleNamebyId(Integer.parseInt(roleId));
			session.setAttribute("empRole", empRole);
			userMenu = loginService.getLinksAndSubLinks(empRole);
			request.getSession().setAttribute("userMenu",userMenu);
		}catch(Exception ex){
			logger.error("Error in loading the user menu ",ex);
		}
		
		if (loginDao.getSite() != null && !loginDao.getSite().trim().equals("")) {
			session.setAttribute("site", loginDao.getSite());
			session.setAttribute("siteById", siteService.getSiteById(Integer.valueOf(loginDao.getSite())));
		}
	
		String indexPage = "";
		try {
			
			if(prePage!=null && !prePage.equals("")){
			indexPage = prePage.substring(0, prePage.indexOf("?"));
				logger.debug("prepage : "+ prePage);
			}
		} catch (Exception e) {
			 logger.debug("prepage ? : "+ prePage);
		}
		
	
		if (needToChangePwd) {
			session.setAttribute("username", username);
			session.setAttribute("password", password);
				return new ModelAndView("redirect:/changePasswordReq");
			
		} else if (empDto.getRegisterStatus()==null || !empDto.getRegisterStatus().equals(SettingsConstant.ACTIVE_STATUS)) {
											
			//request.getRequestDispatcher("register_employee.jsp?loginId="+username+"").forward(request,response);
			//return new ModelAndView("redirect:/registerEmployee?loginId="+username);
			return new ModelAndView("redirect:/employeeRegister");
		} else if (prePage == null || prePage.equals("null") || prePage.equals("") || indexPage.equals("/")) {					
			logger.debug("INside in PrePage "+prePage);
			/*
			if (role.equalsIgnoreCase("emp")) {
				response.sendRedirect("employee_home.jsp");
			}
			if (role.equalsIgnoreCase("hrm")) {
				response.sendRedirect("manager_home.jsp");
			}
			if (role.equalsIgnoreCase("tm")) {
				response.sendRedirect("transportmanager_home.jsp");
				// request.getRequestDispatcher("WEB-INF/check.jsp").forward(request,
				// response);
			}
			if (role.equalsIgnoreCase("ta")) {
				response.sendRedirect("transportmanager_home.jsp");
				// request.getRequestDispatcher("WEB-INF/check.jsp").forward(request,
				// response);
			}
			if (role.equalsIgnoreCase("hq")) {
				response.sendRedirect("admin_home.jsp");
			}
			*/
			if (role.equalsIgnoreCase("v")) {
				logger.debug("INNNNNN" + role + loginDao.getVendorId());
				session.setAttribute("vendorCompany", loginDao.getVendorId());
				//response.sendRedirect("vendors_home.jsp");
				return new ModelAndView("redirect:/vendorsHome");
			}else if(emp.getRole().getId()==1){
				return new ModelAndView("redirect:/transportRequest");
				}
			else{
				return new ModelAndView("redirect:/employeeHomeKeo");
			}
		} else{
			//response.sendRedirect(prePage);
			logger.debug("INside in PrePage else "+prePage);
			return new ModelAndView("redirect:/"+prePage);
		}

	} else {

		//response.sendRedirect("index.jsp?message=Invalid User Name/Password !");
		return new ModelAndView("redirect:/","message","Invalid User Name/Password !");
		
	}
		
		}	
	
	@RequestMapping(value= {"/changePasswordReq"})
	public String changePwdReqHandler() throws Exception{
		logger.debug("IN ChangePassword");
		return "changePassword";
	}
	
	@RequestMapping(value= {"/employeeHomeKeo"})
	public String employeeHomeKeoHandler() throws Exception{
		return "EmployeeInformation";
	}
	
	
	@RequestMapping(value= {"/employeeEditSubmit"},method={RequestMethod.POST,RequestMethod.GET})
	public String employeeEditSubmitHandler(HttpServletRequest request,HttpSession session,RedirectAttributes redirectAttributes) throws Exception{
		
		String firstname=request.getParameter("fname");
		String lastname=request.getParameter("lname");
		String gender=request.getParameter("gender");
		String phonenumber=request.getParameter("mob");
		String siteId=request.getParameter("siteId");
		String address=request.getParameter("addr");
		String latlong=request.getParameter("homelatlong");
		
		Site site=siteService.getSiteById(Integer.valueOf(siteId));
		
		
		latlong=latlong.replace("(", "");
		latlong=latlong.replace(")", "");
		logger.debug("Lat Long"+latlong);
		String lat=(latlong.split(", "))[0];
		String lng=(latlong.split(", "))[1];
		Employee emp=(Employee)session.getAttribute("userLoggedIn");
		emp.setEmployeeFirstName(firstname);
		emp.setEmployeeLastName(lastname);
		emp.setGender(gender);
		emp.setDisplayname(firstname+" "+lastname);
		emp.setAddress(address);
		emp.setContactNumber1(phonenumber);
		emp.setSiteBean(site);
		
		
		emp.setEmpLat(lat);
		emp.setEmpLong(lng);
		
		Employee employee=employeeService.insertEmployee(emp);
		if (employee!=null) {
        
			redirectAttributes.addFlashAttribute("status",
					"<div class='success'>Information edited successfully</div");
		} else {
			redirectAttributes.addFlashAttribute("status",
					"<div class='failure'>Operation Failed</div");
		}
		return "redirect:/employeeHomeKeo";
		
	}
	
	
	
	@RequestMapping(value= {"/forgotPassword"})
	public String forgetPasswordHandler(){
		return "forgotPassword";
	}
	@RequestMapping(value= {"/resetPassword"}, method=RequestMethod.POST)
	public String forgetPasswordAction(HttpServletRequest request,RedirectAttributes redirectAttributes) throws Exception{
		String email = request.getParameter("email");
		logger.debug("Email address "+email);

		String password = new PasswordGenerator().randomString(10);
		logger.debug("Password generator "+password);
		String subject = "Password Reset";
		Employee emp=employeeService.getEmployeeByEmail(email);
			emp.setPassword(password);
			emp.setPwdChangedDate(null);
			Employee e = employeeService.UpdatePassword(emp);
			if (e!=null) {
										
				String message = settingsService.getPasswordResetMessage(emp);
				System.out.println("messageeee"+message);
				SendMail mail = SendMailFactory.getMailInstance();
				try{
				mail.send(emp.getEmailAddress(), subject, message);
				redirectAttributes.addFlashAttribute("status",
						"<div class='success'>Password Reset Successfull</div");
				}catch(Exception ex){
					ex.printStackTrace();
				}
			} else {
				redirectAttributes.addFlashAttribute("status",	"<div class='failure'>Operation Failed</div");
			}
			return "redirect:/";
		
		
	}
	
	
	@RequestMapping(value= {"/logout"})
	public String goToLogout(HttpServletRequest request,HttpServletResponse response,Map<String,Object> map){
		logger.debug("In gotoLogout Page......");
		HttpSession session = request.getSession(true);
		String empId=(String) session.getAttribute("user");
		logger.debug("print empId as "+empId);
		
			if (session.getAttribute("role").equals("hrme")) {
				session.setAttribute("role", "hrm");
				return "redirect:/managerHome";
			} else if (session.getAttribute("role").equals("tmhr")	|| session.getAttribute("role").equals("tme")) {
				session.setAttribute("role", "tm");
				return "redirect:/transportmanageHome";
			}else if (session.getAttribute("role").equals("tahr") || session.getAttribute("role").equals("tae")) {
					session.setAttribute("role", "ta");
					return "redirect:/transportmanageHome";				
			} 
			else {
				logger.debug("In Logout Page......");
				session.invalidate();
				response.setHeader("Cache-Control",
						"no-cache, no-store, must-revalidate");

				response.setHeader("Pragma", "co-cache");
				response.setDateHeader("Expires", 0);
				return "redirect:/";
				}
		

	}
	@RequestMapping(value= {"/changePasswordSubmit"},method=RequestMethod.POST)
	public String changePwdSubmitHandler(HttpServletRequest request) throws Exception{
		logger.debug("IN ChangePassword Controller....");
		HttpSession session =request.getSession();
		String empId=session.getAttribute("user").toString();
		String password=request.getParameter("pwd");
		
		Employee employee=employeeService.getEmployeeById(empId);
		employee.setPassword(password);
		employee.setPwdChangedDate(OtherFunctions.getCurrentDate());
		Employee emp=employeeService.changePassword(employee);
        if (emp!=null && !emp.getId().equals("")) {
			session.setAttribute("status",
					"<div class='success'>Password Updated Successfully</div");
		} else {
			session.setAttribute("status",
					"<div class='failure'>Password Updation Failed</div");
		}
        
		return "redirect:/";
	}
	
	@RequestMapping(value= {"/registerEmployee"},method=RequestMethod.GET)
	public String registerEmployeeHandler(HttpServletRequest request,@RequestParam("loginId") String Id,  Map<String,Object> map) throws Exception{
		logger.debug("Registered Employeeeee");
		HttpSession session=request.getSession();
		String empId=session.getAttribute("user").toString();
		logger.debug("EmployeeIDDDDDD"+Id);
		EmployeeDto empDto=employeeService.getEmployeeAccurate(session.getAttribute("user").toString());
		Role role=employeeService.getRoleNamebyId(empDto.getRoleId());
		Employee emp=employeeService.getEmployeeById(empId);
			map.put("empDto", empDto);
			map.put("role", role);
			map.put("emp", emp);
		return "register_employee";
	}
	
	@RequestMapping(value= {"/registerEmployeeSubmit"},method=RequestMethod.POST)
	public String registerEmployeeHandler(HttpServletRequest request,Map<String,Object> map) throws Exception{
		logger.debug("Registered Employeeeee Submit");
		HttpSession session = request.getSession();
		String loginId=request.getParameter("loginId");
		String employeeId=session.getAttribute("user").toString();
		
		Employee employee=employeeService.getEmployeeById(employeeId);
		employee.setRegisterStatus(SettingsConstant.ACTIVE_STATUS);
		Employee e=employeeService.registerEmployee(employee);
		if (e!=null && !e.getId().equals("")) {
			session.setAttribute("status",
					"<div class=\"success\" width=\"100%\" > Registration successful</div>");
		} else {
			session.setAttribute("status",
					"<div class=\"failure\" > Registration Failed</div>");
		}
        
		
			return "redirect:/registerEmployee?loginId="+loginId;
			
		}
				
	
}
