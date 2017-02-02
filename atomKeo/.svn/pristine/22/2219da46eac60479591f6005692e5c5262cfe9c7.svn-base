package com.agiledge.atom.service.intfc;

import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.entities.TripDetail;

public interface SMSService {

	void sendPasswordToDriver(String id) throws Exception;
	void sendPasswordOfTripsToEscort(String id) throws Exception;
	int sendPinSMS(TripDetailsDto dto) throws Exception;
	void sendPasswordToEscort(String tripId, String password) throws Exception;
	void sendSMSOnPanic(TripDetail td, String time);
	void sendSMSOnEmpPanic(TripDetail td, String time, String empid);
	void sendsmstoICE(String empid, String time);


}
