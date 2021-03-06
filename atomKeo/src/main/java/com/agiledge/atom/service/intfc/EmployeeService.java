package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.dto.EmployeeDto;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Role;

public interface EmployeeService {

	public Employee getEmployeeByUserName(String userId) throws Exception;
	public Employee getEmployeeById(String id) throws Exception;
	public List<Employee> searchEmployees(EmployeeDto employeeDto) throws Exception;
	public List<Employee> getEnabledExternalemployees() throws Exception;
	public List<Employee> getAllExternalemployees() throws Exception;
	public EmployeeDto getEmployeeAccurate(String empId) throws Exception;
	public Employee changePassword(Employee emp);
	public Role getRoleNamebyId(int roleId) throws Exception;
	public Employee registerEmployee(Employee employee)throws Exception;
	public Employee UpdatePassword(Employee emp) throws Exception;
	public List<Employee> getEmployeebyIdName(String id,String name) throws Exception;

	public Employee insertEmployee(Employee emp)throws Exception;
	public Employee changePassword(String loginid, String password) throws Exception;
	public Employee getEmployeeByEmail(String email) throws Exception;

} 
