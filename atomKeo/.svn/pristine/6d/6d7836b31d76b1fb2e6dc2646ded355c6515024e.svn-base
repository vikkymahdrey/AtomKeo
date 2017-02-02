package com.agiledge.atom.test.dao;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.agiledge.atom.dao.intfc.EmployeeDao;
import com.agiledge.atom.dao.intfc.SchedulingDao;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/coreatom-servlet.xml")
public class ScheduleAlterTest {

	private static Logger logger =  Logger.getLogger(ScheduleAlterTest.class);
	
	@Autowired
	private SchedulingDao schedulingDao;
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Test
	@Transactional
	public void insertScheduleAlterTest(){
		try {
		EmployeeSchedule schedule = schedulingDao.getscheduleDetailsById("5105");
		Employee subscribedBy = employeeDao.getEmployeeById("1385");
		EmployeeScheduleAlter empSchAlter=new EmployeeScheduleAlter();
		empSchAlter.setEmployeeSchedule(schedule);
		empSchAlter.setEmployee(subscribedBy);
		empSchAlter.setStatus("new");
		empSchAlter.setDate(new Date());
		empSchAlter.setLoginTime("weekly off");
		empSchAlter.setLogoutTime("weekly off");
		
			logger.debug("before inserting ");
			schedulingDao.insertEmpWeekOff(empSchAlter);
			logger.debug("after inserting");
		} catch (Exception e) {
			logger.error("Error inserting alter schedule ",e);
		}
	}
		
}
