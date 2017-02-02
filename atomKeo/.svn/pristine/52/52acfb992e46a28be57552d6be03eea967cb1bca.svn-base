package com.agiledge.atom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.EmployeeDao;
import com.agiledge.atom.dao.intfc.SubscriptionDao;
import com.agiledge.atom.entities.EmployeeSubscription;
import com.agiledge.atom.service.intfc.SubscriptionService;

@Service("subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService{
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private SubscriptionDao subscriptionDao;

	public boolean subscribeEmployee(EmployeeSubscription newSubscription)
			throws Exception {
		boolean isEmpSubscribed = subscriptionDao.subscribeEmployee(newSubscription);
		if(isEmpSubscribed){
			employeeDao.updateEmployee(newSubscription.getEmployee1());
		}else return false;
		return true;
	}

}
