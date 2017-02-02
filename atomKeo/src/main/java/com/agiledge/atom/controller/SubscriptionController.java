/*package com.agiledge.atom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agiledge.atom.constants.CoreAtomConstants;
import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dto.EmployeeDto;
import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSubscription;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Notification;
import com.agiledge.atom.entities.Place;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.entities.Roledelegation;
import com.agiledge.atom.entities.Setting;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.service.intfc.AplService;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.service.intfc.MailSenderService;
import com.agiledge.atom.service.intfc.NotificationService;
import com.agiledge.atom.service.intfc.RoleService;
import com.agiledge.atom.service.intfc.SettingsService;
import com.agiledge.atom.service.intfc.SiteService;
import com.agiledge.atom.service.intfc.SubscriptionService;
import com.agiledge.atom.utils.DateUtil;

@Controller
@RequestMapping("/")
public class SubscriptionController {
	
	private static final Logger logger = Logger.getLogger(SubscriptionController.class);
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private EmployeeService employeeeService;
	
	@Autowired
	private AplService aplService;
	
	@Autowired
	private SubscriptionService subscriptionService;
	
	@Autowired
	private MailSenderService mailSenderService;
	
	@Autowired
	private SettingsService settingsService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@RequestMapping(value= {"/transadminSubscription"}, method=RequestMethod.GET)
	public String showSubscriptionPage(Model model){
		 
		List<Site> sites = new ArrayList<Site>();
		try{	
			sites =	siteService.getSites();
		}catch(Exception ex){
			logger.error("Error in fetching the sites from database ", ex);
		}
		model.addAttribute("sites", sites);
		return "transadmin_subscriptionforemp";
	}
	
	// An ajax call comes from EmployeeSearch.jsp
	@RequestMapping(value = {"/searchEmployees"},  method=RequestMethod.POST)
	public  @ResponseBody String loadEmployeeData(@RequestParam(value = "employeeID") String employeeID,
			@RequestParam(value = "firstName") String firstName, @RequestParam(value = "lastName") String lastName)
	{
			EmployeeDto dto = new EmployeeDto();
			dto.setEmployeeID(employeeID);
			dto.setEmployeeFirstName(firstName);
			dto.setEmployeeLastName(lastName);
			List<Employee> employees = new ArrayList<Employee> ();
			
			try {
				employees = employeeeService.searchEmployees(dto);
			} catch (Exception e) {
				logger.error("No employees found for the search criteria");
			}
			String html = "<table border='1'>" + " <thead>" + " <tr>"
					+ "  <th>Employee Name</th>" + "   <th>Personnel No</th>"
					+ "     <th>Department</th>" + "      <th></th>"
					+ "   </tr>" + "</thead>" + " <tbody>";
			String data = "";
			String i = "";
			if (employees.size() == 0) {
				data = "<td colspan='5' > No Data Found ! </td>";
			}
			for (Employee emp : employees) {
				i = emp.getId();
				data = data + "<tr> " + "" + "<td id=employeeNameCell-" + i
						+ "> " + emp.getEmployeeFirstName() + " "
						+ emp.getEmployeeLastName()
						+ "</td><td id=personnelNoCell-" + i + ">"
						+ emp.getPersonnelNo() + "</td><td>"
						+ emp.getDeptName()
						+ "</td><td> <a href=# onClick=selectRow('"
						+ emp.getId() + "') > Select </a> "
						+ "<input type='hidden' id='name-" + i + "' "
						+ "name='name-" + i + "'  value='"
						+ emp.getEmployeeFirstName() + " "
						+ emp.getEmployeeLastName() + "' /> "
						+ "<input type='hidden' id='address-" + i + "' "
						+ "name='address-" + i + "'  value='"
						+ emp.getAddress() + "' /> " +
								"<input type='hidden' name='userType-" + i +"' id='userType-" + i +"' value='"+emp.getRole().getUsertype()+"' /> " +
								"" + "</td></tr>";

			}
			String lastPart = "        </tbody>" + "   </table>";

			html = html + data + lastPart;
			return html;
	}
	
	
	@RequestMapping(value= {"/subscribe"}, method=RequestMethod.POST)
	public String addSubscription(HttpServletRequest request, HttpServletResponse response, Model model){
		EmployeeSubscription newSubscription = new EmployeeSubscription();
		
		String source = "Employee";
		Employee doneBy=(Employee)request.getSession().getAttribute("userLoggedIn");
		Employee empBeingSubscribed = null;
		try{
			String empId = request.getParameter("employeeID");
			Employee spoc = employeeeService.getEmployeeById(request.getParameter("supervisorID2"));
			newSubscription.setEmployee2(spoc);
			Employee manager = employeeeService.getEmployeeById(request.getParameter("supervisorID1"));
			newSubscription.setEmployee3(manager);
			newSubscription.setSubscriptionDate(DateUtil.convertStringToDate(request.getParameter("fromDate"), "dd/MM/yyyy","yyyy-MM-dd hh:mm::ss"));
			newSubscription.setFromDate(DateUtil.getCurrentDateTime("yyyy-mm-dd"));
			newSubscription.setSubscriptionStatus("pending");
			Landmark landmark = aplService.getLandmarkById(request.getParameter("landMarkID"));
			newSubscription.setLandmark(landmark);
			if(empId == null){
				empBeingSubscribed = doneBy;
				source = "TransportAdmin";
			}else{
				empBeingSubscribed = employeeeService.getEmployeeById(empId);
			}
			empBeingSubscribed.setContactNumber1(request.getParameter("contactNo"));
			empBeingSubscribed.setSiteBean(siteService.getSiteById(Integer.parseInt(request.getParameter("site"))));
			empBeingSubscribed.setLineManager(newSubscription.getEmployee3().getId());
			newSubscription.setEmployee1(empBeingSubscribed);
			boolean isSubscribed = subscriptionService.subscribeEmployee(newSubscription);
			
			if(isSubscribed){
				// send a subscription email
				sendSubscriptionEmail(newSubscription);
				model.addAttribute("status","<div class=\"success\" width=\"100%\" > Subscription successful</div>");
			}else{
				model.addAttribute("status","<div class=\"failure\" > Already registered!</div>");
			}
			
		}catch(Exception ex){
			model.addAttribute("status","<div class=\"failure\" > Adding subscription failed!</div>");
			logger.error("Error in subscribing the user ",ex);
		}
		return "redirect:transadminSubscription";
	}
	
	@Async
	public void sendSubscriptionEmail(EmployeeSubscription newSubscription) throws Exception {
		Employee emp = newSubscription.getEmployee1();
		Setting setting = settingsService.getSettnigs();
		Notification notification = notificationService.getNotificationByName(CoreAtomConstants.SUBSCRIPTION_REQUEST_SUCCESS_EMAIL);
		
		String to = emp.getEmailAddress();
        Map<String,Object> bodyPropMap = new HashMap<String, Object>();
        bodyPropMap.put("empDispName", emp.getDisplayname());
        bodyPropMap.put("subscriptionDate", newSubscription.getSubscriptionDate());
        bodyPropMap.put("transportTeamNum", SettingsConstant.transportTeamNumber);
        bodyPropMap.put("transportTeamEmail", SettingsConstant.transportTeamEmail);
        bodyPropMap.put("domainName", setting.getDomainName());
        String body = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, notification.getTemplatePath(), "UTF-8", bodyPropMap);
		List<String> ccs = new ArrayList<String> ();
		List<String> bccs = new ArrayList<String> ();
		String spocEmail = newSubscription.getEmployee2().getEmailAddress();
		String supEmail = newSubscription.getEmployee3().getEmailAddress();
		ccs.add(spocEmail);
		ccs.add(supEmail);
		
		Date currDate = new Date();
		// Add the emails of the users that are delegated as the SPOC to the list of ccs
		for(Roledelegation rd : newSubscription.getEmployee2().getRoledelegations1())
			if(rd.getFromDate().compareTo(currDate) <= 0 && rd.getToDate().compareTo(currDate) >= 0)
				ccs.add(rd.getEmployee2().getEmailAddress());
		// Add the emails of the users that are delegated as the Supervisor to the list of ccs
		for(Roledelegation rd : newSubscription.getEmployee3().getRoledelegations1())
			if(rd.getFromDate().compareTo(currDate) <= 0 && rd.getToDate().compareTo(currDate) >= 0)
				ccs.add(rd.getEmployee2().getEmailAddress());
		
	    Role adminRole = roleService.gerRoleByUserType("ta");
	    for(Employee eachAdmin : adminRole.getEmployees())
	    	bccs.add(eachAdmin.getEmailAddress());
	    
	    logger.debug("toAddress = "+to);
	    logger.debug("ccs = "+ccs);
	    logger.debug("bccs = "+bccs);
	    mailSenderService.sendMailWithHtml("", to, ccs, bccs, notification.getSubject(), body);
		
	}

	@RequestMapping(value= {"/employeeSearch"}, method=RequestMethod.GET)
	public String showEmployeeSearch(){
		return "EmployeeSearch";
	}
	
	@RequestMapping(value= {"/supervisorSearch"}, method=RequestMethod.GET)
	public String showSupervisorSearch(){
		return "SupervisorSearch";
	}
	
	@RequestMapping(value= {"/spocSearch"}, method=RequestMethod.GET)
	public String showSpocSearch(){
		return "SpocSearch";
	}
	
	@RequestMapping(value= {"/searchLandmark"}, method=RequestMethod.GET)
	public String showAPLSearch(){
		return "APLSearch";
	}
	
	
	// An ajax call comes from APLSearch.jsp
	@RequestMapping(value = {"/searchAPL"},  method=RequestMethod.POST)
	public  @ResponseBody String searchAPL(@RequestParam(value = "landMarkText") String searchText,
			@RequestParam(required = false, value = "location") String branchId, @RequestParam(required = false, value = "site") String siteId)
	{
		String response = "";
		Landmark landmarkMatched = null;
		Place placeMatched = null;
		Area areaMatched = null;
		List<Landmark> landmarksMatched = null;
		List<Place> placesMatched = null;
		List<Area> areasMatched = null;
		
		int branch = 0;
		if(siteId != null && !(siteId.isEmpty())){
			try {
				branch = siteService.getSiteById(Integer.parseInt(siteId)).getBranchBean().getId();
			}catch (Exception e) {
				logger.error("Error in fetching site for siteId "+siteId,e);
				return "";
			}
		}
		else 
			branch = Integer.parseInt(branchId);
		
		logger.debug("branch Id ::: "+branch);
		
		
		try {
		    landmarksMatched = aplService.getMatchingLandmarks(searchText);
			
			// See if the entered text matches any landmark
			if(landmarksMatched != null && landmarksMatched.size() > 0){
				for(Landmark eachLandmarkMatched : landmarksMatched){
					landmarkMatched = eachLandmarkMatched;
					placeMatched = eachLandmarkMatched.getPlaceBean();
					areaMatched = placeMatched.getAreaBean();
					if(areaMatched.getBranch().getId() == branch)
						response += landmarkMatched.getId() + ":" +areaMatched.getArea()+" ->"+placeMatched.getPlace()
						+" ->"+landmarkMatched.getLandmark() + "|";
				}
			}
			
			// See if the entered text matches any place
			placesMatched = aplService.getmatchingPlaces(searchText);
			if(placesMatched != null && placesMatched.size() > 0){
				for(Place eachPlaceMatched : placesMatched){
					placeMatched = eachPlaceMatched;
					areaMatched = placeMatched.getAreaBean();
					if(areaMatched.getBranch().getId() == branch){
						landmarksMatched = eachPlaceMatched.getLandmarks();
						if(landmarksMatched != null && landmarksMatched.size() > 0){
							for(Landmark eachLandMarkMatched : landmarksMatched){
								landmarkMatched = eachLandMarkMatched;
								response += landmarkMatched.getId() + ":" +areaMatched.getArea()+" ->"+placeMatched.getPlace()
										+" ->"+landmarkMatched.getLandmark() + "|";
							}
						}
							
					}
				}
			}
			
			// See if the entered text matches any area
			areasMatched = aplService.getMatchingAreas(searchText);
			if(areasMatched != null && areasMatched.size() > 0){
				for(Area eachAreaMatched : areasMatched){
					areaMatched = eachAreaMatched;
					if(areaMatched.getBranch().getId() == branch){
						placesMatched = areaMatched.getPlaces();
						if(placesMatched != null && placesMatched.size() >0){
							for(Place eachPlaceMatched : placesMatched){
								placeMatched = eachPlaceMatched;
								landmarksMatched = placeMatched.getLandmarks();
								if(landmarksMatched != null && landmarksMatched.size() >0){
									for(Landmark eachLandmarkMatched : landmarksMatched){
										landmarkMatched = eachLandmarkMatched;
										response += landmarkMatched.getId() + ":" +areaMatched.getArea()+" ->"+placeMatched.getPlace()
												+" ->"+landmarkMatched.getLandmark() + "|";
									}
								}
							}
						}
					}
				}
			}
			
			
		} catch (Exception e) {
			logger.error("Error in fetching the APLs ",e);
			response = "";
		}
		
		return response;
	}
	
}
*/