package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;
import com.agiledge.atom.entities.EmployeeSubscription;


public interface SchedulingService {

	public List<EmployeeSubscription> getAllSubscribedEmployeeDetails() throws Exception;
	/*public ArrayList<SchedulingDto> getSubscribedEmployeeDetailsByManger(long managerId, long delegaterId, String site, String employeeFilter);
	public ArrayList<SchedulingDto> getSubscribedEmployeeDetailsByspoc(	long spocid , String site);
	public ArrayList<SchedulingDto> getAllSubscribedEmployeeDetailsByManger(long managerId);
	public ArrayList<SchedulingDto> getAllSubscribedEmployeeDetails(String site);
	public int setScheduleEmployees(ArrayList<SchedulingDto> scheduleEmpList);
	private void auditLogEntryforcreateschedule(int autoincNumber,String subcriptionEmpModule, int changedBy,String workflowStateEmpty, String workflowStateSubPending,	String auditLogCreateAction);
	public List<SchedulingDto> getSchedulingDetailsWhoAreAboutToExpiry(String curDate);
	public Date getFirstSchedulingFromDate(String subid);
	public Date getLastSchedulingToDate(String subid);
	public ArrayList<ScheduleAlterDto> getEmployeesWeeklyOff(ArrayList<SchedulingDto> scheduleEmpList);
	public boolean hasNoSchedule(String subscriptionId);
	public int uploadSchedule(ArrayList<SchedulingDto> dtoList, String changedBy);
	public ArrayList<String> getMessageList();
	public void setMessageList(ArrayList<String> messageList);
	public String getMessage();
	public void setMessage(String message);
	public SchedulingDto getDetailsForSchedule(String employeeId);
	public long scheduleSelf(SchedulingDto schedulingEmpDto);
	public LinkedHashMap<String, ArrayList<SchedulingDto>> getScheduleOfEmpForDownload(String fromDate, String toDate, String site);
	public ArrayList<SchedulingDto> getEmployeesHavingNoSchedule(String fromDate, String toDate, String site);
*/

	public EmployeeSubscription getSubscriptionDetailsById(String subsId) throws Exception;

	public Atsconnect getProjectById(String projectId) throws Exception;

	public List<EmployeeScheduleAlter> getAlterSchDetails() throws Exception;

	public void insertIntoEmployeeSchedule(EmployeeSchedule empSchedule, String weekOffStatus) throws Exception;
	
	public EmployeeSchedule getScheduleDetailsById(String scheduleId) throws Exception;

	public EmployeeSchedule insertionOnEmployeeSchedule(EmployeeSchedule empSch)throws Exception;

	public List<EmployeeSchedule> getSchedulingForTripSheet(String tripDate,String tripMode, String tripTime) throws Exception;

	public EmployeeSchedule getExistingSchDetails(String fromDate,String toDate, String time,String empid)throws Exception;

	public void delete(EmployeeSchedule esDetails)throws Exception;

	


	

}
