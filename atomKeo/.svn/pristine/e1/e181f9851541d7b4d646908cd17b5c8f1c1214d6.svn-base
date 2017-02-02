package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.Logtime;
import com.agiledge.atom.entities.Timesloat;


public interface LogTimeService {
	//public List <Logtime> getAllGeneralLogtime(String log,String site) throws Exception;
	public List<Logtime> getAllLogtime(String log) throws Exception;
	public List<Logtime> getAllGeneralLogtime() throws Exception;
	public Logtime insertLogtime(Logtime logtime) throws Exception;
	public List<Logtime> getAllInactiveLogtime(String logtype, String site) throws Exception;
	public List<Logtime> getAllLogtime(String logtype, String site) throws Exception;
	public Logtime getLogtimeById(String id)throws Exception;
	public Logtime deleteLogTime(Logtime lt)throws Exception;
	public Logtime getLogtimeByIdAndStatus(String parameter) throws Exception;
	public Logtime updateLogtime(Logtime lt)throws Exception;
	public List<Atsconnect> getProjectsByName(String projectName) throws Exception;
	public List<Atsconnect> getProjectsByCode(String projectCode) throws Exception;
	public Atsconnect getProjectsById(String project) throws Exception;
	public Logtime mapTimeAndProject(Atsconnect ats, Logtime lt) throws Exception;
	public Logtime mapTimeAndProject(String project, String timeId)throws Exception;
	public List<Timesloat> getTimeSloats()throws Exception;
	
}
