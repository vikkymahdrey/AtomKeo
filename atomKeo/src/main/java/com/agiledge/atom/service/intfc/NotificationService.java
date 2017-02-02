package com.agiledge.atom.service.intfc;

import com.agiledge.atom.entities.Notification;

public interface NotificationService {

	public Notification getNotificationByName(String notificationName) throws Exception;
}
