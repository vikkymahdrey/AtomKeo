package com.agiledge.atom.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.NotificationDao;
import com.agiledge.atom.entities.Notification;

@Repository("notificationDao")
public class NotificationDaoImpl extends AbstractDao implements NotificationDao{

	public Notification getNotificationByName(String notificationName)
			throws Exception {
		Query query = getEntityManager().createQuery("from Notification n where n.name =:notificationName");
		query.setParameter("notificationName", notificationName);

		return (Notification) query.getResultList().get(0);
	}

}
