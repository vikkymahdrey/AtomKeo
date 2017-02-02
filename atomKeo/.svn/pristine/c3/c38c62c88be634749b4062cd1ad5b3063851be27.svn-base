package com.agiledge.atom.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.FilterSupportDao;
import com.agiledge.atom.service.intfc.FilterSupportService;

@Service
public class FilterSupportServiceImpl implements FilterSupportService {
	
	@Autowired
	private FilterSupportDao filterSupportDao;
	
	public boolean hasAuthentication() throws Exception{
		return filterSupportDao.hasAuthentication();
	}
	
	public boolean authenticatePageRole(HttpServletRequest request,  String role, String page, String employeeId) throws Exception {
	 return filterSupportDao.authenticatePageRole(request, role, page, employeeId);
	}
}
