package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.dto.TestDto;
import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;
import com.agiledge.atom.entities.EmployeeSubscription;


public interface SchedulingDao {
	public List<EmployeeSubscription> getAllSubscribedEmployeeDetails() throws Exception;

	public EmployeeSubscription getSubscriptionDetailsById(String subsId) throws Exception;

	public Atsconnect getProjectById(String projectId) throws Exception;

	public List<EmployeeScheduleAlter> getAlterSchDetails() throws Exception;

	public void insertIntoEmployeeSchedule(EmployeeSchedule empSchedule) throws Exception;

	public void insertEmpWeekOff(EmployeeScheduleAlter empSchAlter) throws Exception;
	
	public EmployeeSchedule getscheduleDetailsById(String scheduleId) throws Exception;

	public List<TestDto> getAllSubscribedEmployeeDetails1() throws Exception;

	public EmployeeSchedule insertionOnEmployeeSchedule(EmployeeSchedule empSch)throws Exception;

	public List<EmployeeSchedule> getSchedulingForTripSheet(String tripDate,String tripMode, String tripTime)throws Exception;

	public EmployeeSchedule getExistingSchDetails(String fromDate,String toDate, String time,String empid)throws Exception;

	public void delete(EmployeeSchedule esDetails)throws Exception;
		
}
