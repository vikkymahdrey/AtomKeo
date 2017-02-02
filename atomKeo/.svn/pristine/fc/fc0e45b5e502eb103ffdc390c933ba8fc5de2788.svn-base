package com.agiledge.atom.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.EmployeeDao;
import com.agiledge.atom.dto.EmployeeDto;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.Role;
import com.agiledge.atom.service.intfc.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDao employeeDao;
	
	public List<Employee> searchEmployees(EmployeeDto employeeDto)
			throws Exception {
		return employeeDao.searchEmployees(employeeDto);
	}
	
	public Employee getEmployeeByUserName(String userId) throws Exception {
		return employeeDao.getEmployeeByUserName(userId);
	}

	public Employee getEmployeeById(String id) throws Exception {
		return employeeDao.getEmployeeById(id);
	}

	public List<Employee> getEnabledExternalemployees() throws Exception {
		return employeeDao.getEnabledExternalemployees();
	}

	public List<Employee> getAllExternalemployees() throws Exception {
		return employeeDao.getAllExternalemployees();
	}

	public EmployeeDto getEmployeeAccurate(String empId) throws Exception {
		return employeeDao.getEmployeeAccurate(empId);
	}

	public Employee changePassword(Employee emp) {
		
		return employeeDao.changePassword(emp);
	}

	public Role getRoleNamebyId(int roleId) throws Exception {
		return employeeDao.getRoleNamebyId(roleId);
	}

	public Employee registerEmployee(Employee employee) throws Exception {
		return employeeDao.registerEmployee(employee);
	}

	public Employee UpdatePassword(Employee emp) throws Exception {
		return employeeDao.UpdatePassword(emp);
	}


	public List<Employee> getEmployeebyIdName(String id, String name) throws Exception {
		// TODO Auto-generated method stub
		return employeeDao.getEmployeebyIdName(id, name);
	}

	public Employee insertEmployee(Employee emp) throws Exception {
		return employeeDao.employeeDao(emp);
	}

	public Employee changePassword(String loginid, String password) throws Exception{
		
		Employee emp=getEmployeeByUserName(loginid);
		emp.setPassword(password);
		emp.setPwdChangedDate(new Date());
		Employee e=employeeDao.UpdatePassword(emp);
		return e;
	}

	public Employee getEmployeeByEmail(String email) throws Exception {
		
		return employeeDao.getEmployeeByEmail(email);
	}

	
	

	
}
