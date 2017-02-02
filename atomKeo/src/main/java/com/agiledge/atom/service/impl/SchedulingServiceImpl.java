package com.agiledge.atom.service.impl;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.SchedulingDao;
import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;
import com.agiledge.atom.entities.EmployeeSubscription;
import com.agiledge.atom.service.intfc.SchedulingService;

@Service
public class SchedulingServiceImpl implements SchedulingService {
	
	
	private static Logger logger = Logger.getLogger(SchedulingServiceImpl.class);
	
	@Autowired
	private SchedulingDao schedulingDao;
	
	public List<EmployeeSubscription> getAllSubscribedEmployeeDetails() throws Exception{
		return schedulingDao.getAllSubscribedEmployeeDetails();
	}

	public EmployeeSubscription getSubscriptionDetailsById(String subsId) throws Exception {
		
		return schedulingDao.getSubscriptionDetailsById(subsId);
	}

	public Atsconnect getProjectById(String projectId) throws Exception {
		// TODO Auto-generated method stub
		return schedulingDao.getProjectById(projectId);
	}

	public List<EmployeeScheduleAlter> getAlterSchDetails() throws Exception {
		// TODO Auto-generated method stub
		return schedulingDao.getAlterSchDetails();
	}

	public void insertIntoEmployeeSchedule(EmployeeSchedule empSchedule, String weekOffStatus) throws Exception {
		
		logger.debug("inserting into employee schedule");
		schedulingDao.insertIntoEmployeeSchedule(empSchedule);

		// empSchedule = schedulingDao.getscheduleDetailsById(empSchedule.getId());
				
		EmployeeScheduleAlter empSchAlter=null;
				
		
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar tempDate = Calendar.getInstance();
		try {
			/*DateFormat formatter;
			formatter = new SimpleDateFormat("dd/MM/yyyy");*/
			if (empSchedule.getId()!=null && weekOffStatus.equalsIgnoreCase("on")) {
					fromDate.setTime(empSchedule.getFromDate());
						toDate.setTime(empSchedule.getToDate());
						int i=0;
					while (fromDate.getTimeInMillis() <= toDate.getTimeInMillis()) {

						tempDate = fromDate;
						if (tempDate.get(Calendar.DAY_OF_WEEK) == 1
								|| tempDate.get(Calendar.DAY_OF_WEEK) == 7) {
							logger.debug("It's a week-off");
							empSchAlter=new EmployeeScheduleAlter();
							empSchAlter.setEmployeeSchedule(empSchedule);
							empSchAlter.setEmployee(empSchedule.getEmployee2());
							empSchAlter.setStatus("new");
							empSchAlter.setDate(tempDate.getTime());
							empSchAlter.setLoginTime("weekly off");
							empSchAlter.setLogoutTime("weekly off");
							logger.debug("inserting into employee schedule alter");
							schedulingDao.insertEmpWeekOff(empSchAlter);
							logger.debug("inserted into employee schedule alter");
						}
						fromDate.add(Calendar.DATE, 1);
					}
			}		
	} catch(Exception ex) {
		           ex.printStackTrace();
				}
	
		
		
	}


	public EmployeeSchedule getScheduleDetailsById(String scheduleId) throws Exception {

		return schedulingDao.getscheduleDetailsById(scheduleId);
	}

	

	public EmployeeSchedule insertionOnEmployeeSchedule(EmployeeSchedule empSch) throws Exception {
		return schedulingDao.insertionOnEmployeeSchedule(empSch);
	}

	public List<EmployeeSchedule> getSchedulingForTripSheet(String tripDate,String tripMode, String tripTime) throws Exception {
		return schedulingDao.getSchedulingForTripSheet(tripDate,tripMode,tripTime);
	}

	public EmployeeSchedule getExistingSchDetails(String fromDate,String toDate, String time,String empid) throws Exception {
		return schedulingDao.getExistingSchDetails(fromDate,toDate,time,empid);
	}

	public void delete(EmployeeSchedule esDetails) throws Exception {
		schedulingDao.delete(esDetails);
	}
	
}
