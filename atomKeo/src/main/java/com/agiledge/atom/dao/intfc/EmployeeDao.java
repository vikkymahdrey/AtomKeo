package com.agiledge.atom.dao.intfc;

import java.util.Date;
import java.util.List;

import com.agiledge.atom.dto.EmployeeDto;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.Role;

public interface EmployeeDao {
    
	public Employee getEmployeeById(String empId) throws Exception;
	public Employee getEmployeeByUserName(String userName) throws Exception;
	public List<Employee> searchEmployees(EmployeeDto employeeDto) throws Exception;
	public Employee updateEmployee(Employee employee) throws Exception;
	public List<Employee> getEnabledExternalemployees() throws Exception;
	public List<Employee> getAllExternalemployees() throws Exception;
	public EmployeeDto getEmployeeAccurate(String empId) throws Exception;
	public Employee changePassword(Employee emp);
	public Role getRoleNamebyId(int roleId) throws Exception;
	public Employee registerEmployee(Employee employee)throws Exception;
	public Employee UpdatePassword(Employee emp) throws Exception;
	public List<Employee> getEmployeebyIdName(String id,String name) throws Exception;
	public Employee employeeDao(Employee emp) throws Exception;
	public Employee getEmployeeByEmail(String email)throws Exception;
}
