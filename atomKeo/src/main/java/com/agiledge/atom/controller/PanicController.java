package com.agiledge.atom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dto.EscalationMatrixDto;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Escalationmatrix;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.service.intfc.PanicService;
import com.agiledge.atom.service.intfc.TripDetailsService;

@Controller
public class PanicController {

	@Autowired 
	PanicService panicservice;
	
	@Autowired
	EmployeeService employeeService;

	
	@RequestMapping(value= {"viewescallationmatrix"},method={RequestMethod.GET})
	public ModelAndView getPanicsetup() throws Exception{
		ModelAndView mv=new ModelAndView("escalation");
		List<Escalationmatrix> list=panicservice.getEscallationmatrix();
		List<EscalationMatrixDto> esclist=new ArrayList<EscalationMatrixDto>();
		System.out.println(list.size());
		ArrayList<String> donelevel=new ArrayList<String>();
		for(Escalationmatrix em:list){
			if(!donelevel.contains(em.getLevel())){
			System.out.println(em.getLevel()+em.getEscalationGroup());
			EscalationMatrixDto dto=new EscalationMatrixDto();
			dto.setLevel(em.getLevel());
			dto.setEscalationgroup(em.getEscalationGroup());
			if((em.getEscalationGroup().toString().equalsIgnoreCase("Admin")&&em.getEmployee()!=null)){
				dto.setEmpid(em.getEmployee().getId());
				dto.setEmployeename(em.getEmployee().getDisplayname());
				dto.setEmptimeslot(em.getTimeSlot());
			}else if(em.getEscalationGroup().equalsIgnoreCase("Vendor")&&em.getEmployee()!=null){
				dto.setVendorempid(em.getEmployee().getId());
				dto.setVendoremployeename(em.getEmployee().getDisplayname());
				dto.setVendortime(em.getTimeSlot());
			}
				for(Escalationmatrix em1:list){
					if(em1.getLevel().equalsIgnoreCase(em.getLevel().toString())){
						if(em.getEscalationGroup().equalsIgnoreCase("Admin")&&em1.getEmployee()!=null){
						dto.setVendorempid(em1.getEmployee().getId());
						dto.setVendoremployeename(em1.getEmployee().getDisplayname());
						dto.setVendortime(em1.getTimeSlot());
					}else if(em.getEscalationGroup().equalsIgnoreCase("Vendor")&&em1.getEmployee()!=null){
							dto.setEmpid(em1.getEmployee().getId());
							dto.setEmployeename(em1.getEmployee().getDisplayname());
							dto.setEmptimeslot(em1.getTimeSlot());
							}
					}
					}
			System.out.println("Dto"+dto.getLevel()+dto.getEscalationgroup()+dto.getEmployeename()+dto.getVendoremployeename());
			donelevel.add(dto.getLevel());
			esclist.add(dto);
			}
		}
						
		mv.addObject("list",esclist);
		return mv;
	}
		
	@RequestMapping(value= {"editescallationmatrix"},method={RequestMethod.GET})
	public ModelAndView editPanicsetup() throws Exception{
		ModelAndView mv=new ModelAndView();
		List<Escalationmatrix> list=panicservice.getEscallationmatrix();
		mv.addObject("list",list);
		return mv;
	}
	
	@RequestMapping(value= {"EscalationMatrix"},method={RequestMethod.POST})
	public String saveescalation(HttpServletRequest request) throws Exception{
		List<EscalationMatrixDto> eslist=new ArrayList<EscalationMatrixDto>();
		for(int i=1;i<6;i++){
			EscalationMatrixDto emdto=new EscalationMatrixDto();
			emdto.setEmpid(request.getParameter("Level"+i+"AID"));
			emdto.setEmptimeslot(request.getParameter("Level"+i+"Atime"));
			emdto.setLevel("Level"+i);
			emdto.setVendorempid(request.getParameter("Level"+i+"VID"));
			emdto.setVendortime(request.getParameter("Level"+i+"Vtime"));
			eslist.add(emdto);
		}
		String result=panicservice.saveescalation(eslist);
		return "redirect:viewescallationmatrix";
	}
	
	@RequestMapping(value={"escalationSearch"},method={RequestMethod.GET})
	public String getEscalationSearch(){
		return "escalationSearch";
	}
	
	@ResponseBody
	@RequestMapping(value={"EscalationMatrixSearch"},method={RequestMethod.POST})
	public String getEscalationSearchResults(HttpServletRequest request) throws Exception{
		String empid=request.getParameter("employeeID");
		String empname=request.getParameter("name");
		List<Employee> list=employeeService.getEmployeebyIdName(empid, empname);
		String html = "<table border='1'><thead><tr><th>Employee Name</th><th></th></tr></thead><tbody>";
				String data = "";
				String i = "";
				if (list.size() == 0) {
					data = "<td colspan='5' > No Data Found ! </td>";
				}
				for (Employee emp : list) {
					i = emp.getId();
					data = data + "<tr> " + "" + "<td id=employeeNameCell-" + i
							+ "> " + emp.getDisplayname()
							+ "</td><td id=personnelNoCell-" + i + ">"
							+ emp.getPersonnelNo()
							+ "</td><td> <a href=# onClick=selectRow('"
							+ emp.getId() + "') > Select </a> "
							+ "<input type='hidden' id='name-" + i + "' "
							+ "name='name-" + i + "'  value='"
							+ emp.getEmployeeFirstName() + "' /> " + "</td></tr>";

				}
				String lastPart = "        </tbody>" + "   </table>";

				html = html + data + lastPart;
		return html;
	}
	
	@RequestMapping(value={"panicAlarmTrip"},method={RequestMethod.GET})
	public ModelAndView panicAlarmTrip(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView("panicAlarmTrip");
		String id=request.getParameter("tripId");
		TripDetail td=panicservice.getTripbyId(id);
		request.setAttribute("tripdetails",td);
		return mv;
	}
	@RequestMapping(value={"tripInfoPopup"},method={RequestMethod.GET})
	public ModelAndView tripInfoPopup(HttpServletRequest request) throws Exception{
		ModelAndView mv=new ModelAndView("tripInfoPopup");
		String id=request.getParameter("tripId");
		TripDetail td=panicservice.getTripbyId(id);
		request.setAttribute("tripdetails",td);
		return mv;
	}
	
	@RequestMapping(value={"PanicAlarmStop"},method={RequestMethod.POST})
	public String PanicAlarmStop(HttpServletRequest request)  {
		//ModelAndView mv=new ModelAndView("panicAlarmTrip");
		try{
		Employee emp=(Employee)request.getSession().getAttribute("userLoggedIn");
		String empid=emp.getId();
		String tripid=request.getParameter("tripId");
		String causeOfalarm= request.getParameter("causeOfalarm");
		String actionDesc= request.getParameter("actionDesc");
		causeOfalarm=causeOfalarm==null?"":causeOfalarm;
		actionDesc=actionDesc==null?"":actionDesc;
		
		
		int i=panicservice.stopPanic(empid,tripid,causeOfalarm,actionDesc);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return "redirect:livetracking?message=Alarm Deactivated!";
	}
	
	
	
}
