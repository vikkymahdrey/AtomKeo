package com.agiledge.atom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agiledge.atom.dao.intfc.NotificationDao;
import com.agiledge.atom.entities.Notification;
import com.agiledge.atom.service.intfc.NotificationService;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService{

	@Autowired
	private NotificationDao notificationDao;
	
	public Notification getNotificationByName(String notificationName)
			throws Exception {
		return notificationDao.getNotificationByName(notificationName);
	}

}
