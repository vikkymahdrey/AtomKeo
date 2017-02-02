package com.agiledge.atom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.config.files.OtherFunctions;
import com.agiledge.atom.config.files.SendMailFactory;
import com.agiledge.atom.dao.intfc.PanicDao;
import com.agiledge.atom.dto.EmployeeICEDTO;
import com.agiledge.atom.dto.TripDetailsChildDto;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.entities.Driver;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Escalationmatrix;
import com.agiledge.atom.entities.Escort;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.TripDetailsChild;
import com.agiledge.atom.mail.SendMail;
import com.agiledge.atom.service.intfc.DeviceService;
import com.agiledge.atom.service.intfc.DriverService;
import com.agiledge.atom.service.intfc.EmployeeService;
import com.agiledge.atom.service.intfc.EscortService;
import com.agiledge.atom.service.intfc.SMSService;
import com.agiledge.atom.service.intfc.SettingsService;
import com.agiledge.atom.service.intfc.TripDetailsService;
import com.agiledge.atom.sms.PanicSMS;
import com.agiledge.atom.sms.SendSMS;
import com.agiledge.atom.sms.SendSMSFactory;
import com.agiledge.atom.sms.getMessages;
@Service
public class SMSServiceImpl implements SMSService {
	private static final Logger logger = Logger.getLogger(SMSServiceImpl.class);

	@Autowired
	private TripDetailsService tripDetailsService;
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private EscortService escortService;
	
	@Autowired
	private PanicDao panicDao;

	@Autowired
	private SettingsService settingsService;

	
	@Autowired
	EmployeeService employeeService;

	@Autowired
	DeviceService deviceService;
	
	
	
	
	
	public void sendPasswordToDriver(String tripId) throws Exception {
		
				TripDetailsDto tripDto = tripDetailsService.getTripSheetByTrip(tripId);
								tripDto.setTrip_date(OtherFunctions.changeDateFromat(tripDto.getTrip_date()));
				Driver driver = driverService.getDriverById(tripDto.getDriverId());
				
				String mobileNo = null;
				String message = null;
				String firstPickUpTime = OtherFunctions.addTime(tripDto.getTrip_time(), tripDto.getTripDetailsChildDtoList().get(0).getTime());
				String time = OtherFunctions.changeTimeFormat(tripDto.getTrip_time(), "HH:mm", "hh:mm a");

				int i = 0;
				if (driver!= null) {
					mobileNo = driver.getContact() == null ? "" : driver.getContact();
					message = getMessages.getDriverSMS(tripDto.getTrip_date(),tripDto.getTrip_log(), time, firstPickUpTime,
							driver.getUsername(), tripDto.getDriverPassword(),
							tripDto.getIsSecurity(), tripDto.getTrip_code());
					i++;
				}
				SendSMS sendSMS = SendSMSFactory.getSMSInstance();
				sendSMS.send(mobileNo, message);
					
	}
	
	public void sendPasswordOfTripsToEscort(String tripId) throws Exception {
		logger.debug("111@@@");
		/*TripDetailsDto tripDto = tripDetailsService.getTripSheetByTrip(tripId);
						tripDto.setTrip_date(OtherFunctions.changeDateFromat(tripDto.getTrip_date()));*/
		TripDetail td=tripDetailsService.getTripsById(tripId);
		SendSMS sendSMSemp = SendSMSFactory.getSMSInstance();
		for(TripDetailsChild tc:td.getTripDetailsChilds()){
			try{
				String subject = "Booking confirmation";	
				String message = settingsService.getEmployeeBookingConfirmationMsg(tc);
				SendMail mail = SendMailFactory.getMailInstance();
				mail.send(tc.getEmployee().getEmailAddress(), subject, message);
			}catch(Exception e){
				logger.debug("Error During sending booking confirmation mail",e);
			}
			Employee e=tc.getEmployee();
			//int loginidlength=e.getPersonnelNo().length();
			String empmessage = "Hello "+ e.getDisplayname() 
								+" Cab "+td.getVehicleBean().getVehicleType().getType()+" "+ td.getVehicleBean().getRegNo()+" has been assigned to your booking request.Please report to the Security office 10 minutes before "+td.getTripTime()+"."+" Driver Name "+td.getDriver().getName()
								+"."+" Transport Helpline: (080)33134449. Emergency Helpline: (080)33133333. Escalations: +919886149611 ";	
			
			sendSMSemp.send(e.getContactNumber1(), empmessage);
		}
		
		Escort escort=escortService.getEscortById(String.valueOf(td.getEscort().getId()));
		
		String mobileNo = null;
		String message = null;
		String time = OtherFunctions.changeTimeFormat(td.getTripTime(), "HH:mm", "hh:mm a");

		int i = 0;
		if (escort!= null) {
			mobileNo = escort.getPhone() == null ? "" : escort.getPhone();
			message = getMessages.getEscortSMSMsg(OtherFunctions.changeDateFromat(td.getTripDate()),td.getTripTime(),td.getEscort().getName(), td.getVehicleBean().getRegNo(), td.getDriverPswd(),td.getTripCode());
					i++;
		}
		SendSMS sendSMS = SendSMSFactory.getSMSInstance();
		sendSMS.send(mobileNo, message);
			
}

	public int sendPinSMS(TripDetailsDto tripDto) throws Exception {
			String mobileNos[] = null;
			String messages[] = null;

				ArrayList<TripDetailsChildDto> tripDtos = tripDto.getTripDetailsChildDtoList();
				mobileNos = new String[tripDtos.size()];
				messages = new String[tripDtos.size()];
				String inOut = tripDto.getTrip_log();
				String[] cabNo = tripDto.getVehicleNo().trim().split(" ");
				tripDto.setVehicleNo(cabNo[cabNo.length - 1]);
				for (int i = 0; i < tripDtos.size(); i++) {
					mobileNos[i] = tripDtos.get(i).getContactNumber();
					messages[i] = getMessages
							.getPinSMS(tripDtos.get(i).getEmployeeName(), tripDtos
									.get(i).getPersonnelNo(), OtherFunctions
									.changeDateFromat(tripDto.getTrip_date()),
									tripDto.getVehicleNo(),
									tripDto.getDriverName(), tripDto
											.getDriverContact(), tripDtos.get(i)
											.getTime(),
									tripDtos.get(i).getKeyPin(), inOut, tripDto
											.getTrip_time());
				}

			
			SendSMS sendSMS = SendSMSFactory.getSMSInstance();
			sendSMS.send(mobileNos, messages);
			return 1;
		
	}

	public void sendPasswordToEscort(String tripId, String password) throws Exception {
		
			TripDetailsDto tripDto = tripDetailsService.getTripSheetByTrip(tripId);
			Escort escort = escortService.getEscortById(tripDto.getEscortId());
			String mobileNo = null;
			String message = null;
			int i = 0;
			if (escort != null) {
				mobileNo = escort.getPhone() == null ? "" : escort.getPhone();
				/*
				 * message = String .format(
				 * "Hi %s\n----------\nTrip: %s\nTime: %s %s\nUserName: %s\nPassword: %s\nVehicle: %s\nVehicle No: %s\n--------\nATOm.. "
				 * , driverDto.getDriverName(), tripDto.getTrip_code(),
				 * tripDto.getTrip_time(), tripDto.getTrip_log(),
				 * driverDto.getUsername(), tripDto.getDriverPassword(),
				 * tripDto.getVehicle_type(), tripDto.getVehicleNo());
				 */
				message = getMessages
						.getEscortMesssage(tripDto.getTrip_code(),
								tripDto.getTrip_log(), tripDto.getTrip_time(),
								tripDto.getVehicleNo(),
								tripDto.getDriverName(),
								tripDto.getDriverContact(),
								tripDto.getEscortPassword());
				i++;
			}
			
			SendSMS sendSMS = SendSMSFactory.getSMSInstance();
			sendSMS.send(mobileNo, message);
			
		

			
}	


	


	public void sendSMSOnPanic(TripDetail td, String time) {

		String regno=td.getVehicleBean().getRegNo();
		List<Escalationmatrix> escalationMatrixDtos;
		try {
			escalationMatrixDtos = panicDao.getEscallationmatrix();

		String escorttext="";
		String escortmail ="";
		if(td.getEscort()!=null);
		{
			escorttext="Escort Name: "+td.getEscort().getName()
					+", Escort Mobile: "+td.getEscort().getPhone()+", ";
				
			escortmail= "Escort Name: "+td.getEscort().getName()+"<br/>"
					+"Escort Mobilenumber: "+td.getEscort().getPhone()+".";
					
		}	


			
			

		ArrayList<String> newcontacts=new ArrayList<String>();
		ArrayList<String> newemails=new ArrayList<String>();
		ArrayList<String> newtimeSlots=new ArrayList<String>();
		
		String smsmessage = "The panic button has been pressed in the vehicle number: "
				+ regno + " at " + time
				+ ". "+escorttext+" Driver Name: "+td.getDriver().getName()+", Driver Mobile: "+td.getDriver().getContact()+".";

		String emailmessage ="Dear Sir/Madam,"
				+"<br/> <br/>" 
				+"Panic Button has been pressed in the vehicle No : "+ regno + " at " + time+"."
				+"<br/> <br/>"
				+"<b><u>Trip Details:</u></b>"
				+"<br/> "
				+escortmail
				+"<br/> "
				+ "Driver Name: "+td.getDriver().getName()+"<br/>  Driver Mobilenumber: "+td.getDriver().getContact()
				+"<br/> <br/>"
				+"Your immediate action is required"
				+"."
				+"<br/><br/>"
				+ "With warm regards,<br/>" 
				+"Team Admin - STS"
				+"<br/><br/>"
				+"Contact:<br/>"
				+"Transport Helpline<br/>"
				+"(080) 3313 4449<br/><br/>"
				+"Emergency Helpline<br/>"
				+"(080) 3313 3333<br/><br/>"
				+"Escalation:<br/>"
				+"Aditya Bhagat <br/>"
				+"(+91 9886149611)"
				+"<br/>";

		int i = 0;

		for(Escalationmatrix dto:escalationMatrixDtos){
			if(dto.getEmployee()!=null){
			newcontacts.add(dto.getEmployee().getContactNumber1());
			newemails.add(dto.getEmployee().getEmailAddress());
			newtimeSlots.add(dto.getTimeSlot());
			i++;
			}
		}
		String[] contacts = new String[newcontacts.size()];
		String[] timeSlots = new String[newtimeSlots.size()];
		String[] emails = new String[newemails.size()];
		int k=0;
		for(String val:newcontacts){
			contacts[k]=val;
			timeSlots[k]=newtimeSlots.get(k);
			emails[k]=newemails.get(k);
			k++;
		}


		new PanicSMS().sendRepeated(contacts, emails, smsmessage,emailmessage, timeSlots,td);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error in sending Panic SMS");
			e.printStackTrace();
			
		}

		}


			
		public void sendSMSOnEmpPanic(TripDetail td, String time, String empid) {
				
				String regno=td.getVehicleBean().getRegNo();
				List<Escalationmatrix> escalationMatrixDtos;
				try {
					escalationMatrixDtos = panicDao.getEscallationmatrix();
				
					Employee emp=employeeService.getEmployeeById(empid);	
				String emptext="";
				if(emp!=null)	
				{
					
					emptext=" by " +emp.getDisplayname() +", Mobile: "+emp.getContactNumber1();
				}
					
			
					
				String escorttext="";
				String escortmail="";
				if(td.getEscort()!=null);
				{
					escorttext="Escort Name: "+td.getEscort().getName()
						+", Escort Mobile: "+td.getEscort().getPhone()+",";
				
				escortmail= "Escort Name: "+td.getEscort().getName()+"<br/>"
						+"Escort Mobilenumber: "+td.getEscort().getPhone()+".";
				}	
			
				 
		
				
				ArrayList<String> newcontacts=new ArrayList<String>();
				ArrayList<String> newemails=new ArrayList<String>();
				ArrayList<String> newtimeSlots=new ArrayList<String>();
				
				String smsmessage = "The panic button has been activated "+emptext+" in vehicle number: "
						+ regno  + " at " + time+
						 ". "+escorttext+" Driver Name: "+td.getDriver().getName()+", Driver Mobile: "+td.getDriver().getContact();

				String emailmessage ="Dear Sir/Madam,"
						+"<br/> <br/>" 
						+"Panic Button has been pressed in the vehicle No : "+ regno + " at " + time+emptext+"."
						+"<br/> <br/>"
						+"<b><u>Trip Details:</u></b>"
						+"<br/> "
						+escortmail
						+"<br/> "
						+ "Driver Name: "+td.getDriver().getName()+"<br/>  Driver Mobilenumber: "+td.getDriver().getContact()
						+"<br/> <br/>"
						+"Your immediate action is required"
						+"."
						+"<br/><br/>"
						+ "With warm regards,<br/>" 
						+"Team Admin - STS"
						+"<br/><br/>"
						+"Contact:<br/>"
						+"Transport Helpline<br/>"
						+"(080) 3313 4449<br/><br/>"
						+"Emergency Helpline<br/>"
						+"(080) 3313 3333<br/><br/>"
						+"Escalation:<br/>"
						+"Aditya Bhagat <br/>"
						+"(+91 9886149611)"
						+"<br/>";


				int i = 0;
				for(Escalationmatrix dto:escalationMatrixDtos){
					if(dto.getEmployee()!=null){
					newcontacts.add(dto.getEmployee().getContactNumber1());
					newemails.add(dto.getEmployee().getEmailAddress());
					newtimeSlots.add(dto.getTimeSlot());
					i++;
					}
				}
				String[] contacts = new String[newcontacts.size()];
				String[] timeSlots = new String[newtimeSlots.size()];
				String[] emails = new String[newemails.size()];
				int k=0;
				for(String val:newcontacts){
					contacts[k]=val;
					timeSlots[k]=newtimeSlots.get(k);
					emails[k]=newemails.get(k);
					k++;
				}
				
				
				
			

				new PanicSMS().sendRepeated(contacts, emails, smsmessage,emailmessage, timeSlots,td);
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("error in sending Panic SMS");
					e.printStackTrace();
					
				}
				
			}



			public void sendsmstoICE(String empid, String time) {	
			try {
				
				
				Employee emp=employeeService.getEmployeeById(empid);
				
				
				
			
				String message = "Panic alarm has been pressed by "
						+ emp.getDisplayname() + " at " + time
						+ ". Please take immediate action. This is system generated message do not reply.";
					
				EmployeeICEDTO dto = deviceService.getICEdetailsbyID(empid);
					
					
					
				
				
				
					SendSMS sendSMS=SendSMSFactory.getSMSInstance();
					
					
					if (dto.getContact_number1() != null
							&& dto.getContact_number1() != "") {
						sendSMS.send(dto.getContact_number1(), message);

					}
					if (dto.getContact_number2() != null
							&& dto.getContact_number2() != "") {
						sendSMS.send(dto.getContact_number2(), message);
					}

					SendMail sendmail = SendMailFactory.getMailInstance();

					if (dto.getContact_email1() != null
							&& dto.getContact_email1() != "") {
						sendmail.send(dto.getContact_email1(), "Panic Alarm Pressed", message);
					}

					if (dto.getContact_email2() != null
							&& dto.getContact_email2() != "") {
						sendmail.send(dto.getContact_email2(), "Panic Alarm Pressed", message);
					}

					
					
					
					
					
					
					
					
					
					
					
					
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("error in sending ICE SMS");
			} 
		}


}

