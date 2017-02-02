package com.agiledge.atom.service.intfc;

import com.agiledge.atom.dto.SettingsDTO;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.EmployeeSchedule;
import com.agiledge.atom.entities.EmployeeScheduleAlter;
import com.agiledge.atom.entities.Setting;
import com.agiledge.atom.entities.TripDetailsChild;

public interface SettingsService {

	public Setting getSettnigs() throws Exception;

	public SettingsDTO getSettingValue(SettingsDTO settingsDto) throws Exception;

	public void forceUpdateSettings(SettingsDTO settingsDto) throws Exception;

	public String getPasswordResetMessage(Employee emp) throws Exception;

	public String getEmployeeBookingMessage(EmployeeSchedule es)throws Exception;
	
	public String getEmployeeBookingConfirmationMsg(TripDetailsChild tdc) throws Exception;
	public String getEmployeeBookingModificationMsg(EmployeeScheduleAlter esa) throws Exception;
	public String getEmployeeBookingCancellationMsg(EmployeeScheduleAlter esa,String logtime) throws Exception;
}
