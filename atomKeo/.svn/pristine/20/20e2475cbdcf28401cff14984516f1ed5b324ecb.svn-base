package com.agiledge.atom.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.SubscriptionDao;
import com.agiledge.atom.entities.EmployeeSubscription;

@Repository("subscriptionDao")
public class SubscriptionDaoImpl extends AbstractDao implements SubscriptionDao {

	public boolean subscribeEmployee(EmployeeSubscription newSubscription)
			throws Exception {
		    Query query = getEntityManager().createQuery("from EmployeeSubscription es where es.employee1 =:emp and es.subscriptionStatus in ('pending','subscribed')");
		    query.setParameter("emp", newSubscription.getEmployee1());
		    if(query.getResultList() == null || query.getResultList().size() == 0) {
		    	persist(newSubscription);
		    	return true;
		    }else 
		    	return false;
	}

}
