package com.agiledge.atom.test.dao;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.agiledge.atom.dao.intfc.EmployeeDao;
import com.agiledge.atom.entities.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/coreatom-servlet.xml")
public class EmployeeDaoTest {
	
	private static Logger logger =  Logger.getLogger(EmployeeDaoTest.class);
	
	@Autowired
	EmployeeDao employeeDao;
		
	@Test
	@Transactional
	public void getEmployeeDetailsTest(){
		try{
			String empId = "2630";
			logger.debug("Fetching details of employee "+empId);
			Employee employee = employeeDao.getEmployeeByUserName("atomTeam");
			logger.debug("employee "+employee.getLoginId());
			
			// Employee manager = employee.getSpocChilds().get(0).getSpocParent().getEmployee2();
			Employee manager = employeeDao.getEmployeeById(employee.getLineManager());
			
			logger.debug("manager id = "+manager.getId());
			logger.debug("manager name = "+manager.getDisplayname());
			
			
		/*	Employee spoc = employee.getSpocChilds().get(0).getSpocParent().getEmployeeSpoc();
			
			logger.debug("SPOC id = "+spoc.getId());
			logger.debug("SPOC name = "+spoc.getDisplayname());
*/			
	    }catch(Exception ex){
			logger.error("Error in fetching employee details ",ex);
		}
		
	}

}
