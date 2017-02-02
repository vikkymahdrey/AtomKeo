package com.agiledge.atom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.LogTimeDao;
import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.Logtime;
import com.agiledge.atom.entities.Timesloat;
import com.agiledge.atom.service.intfc.LogTimeService;


@Service
public class LogTimeServiceImpl implements LogTimeService {
	
	@Autowired
	LogTimeDao logTimeDao;
	
public List<Logtime> getAllLogtime(String log) throws Exception {
		
		return logTimeDao.getAllLogtime(log);
	}

public List<Logtime> getAllGeneralLogtime() throws Exception {
	return logTimeDao.getAllGeneralLogtime();
}

public Logtime insertLogtime(Logtime logtime) throws Exception {
    return logTimeDao.insertLogtime(logtime);
}

public List<Logtime> getAllInactiveLogtime(String logtype, String site)	throws Exception {
	return logTimeDao.getAllInactiveLogtime(logtype,site);
}

public List<Logtime> getAllLogtime(String logtype, String site)	throws Exception {
	
	return logTimeDao.getAllLogtime(logtype, site);
}

public Logtime getLogtimeById(String id) throws Exception {
	return logTimeDao.getLogtimeById(id);
}

public Logtime deleteLogTime(Logtime lt) throws Exception {
	return logTimeDao.deleteLogTime(lt);
}

public Logtime getLogtimeByIdAndStatus(String id) throws Exception {
	return logTimeDao.getLogtimeByIdAndStatus(id);
}

public Logtime updateLogtime(Logtime lt) throws Exception {
		return logTimeDao.updateLogtime(lt);
}

public List<Atsconnect> getProjectsByName(String projectName) throws Exception {
	return logTimeDao.getProjectsByName(projectName);
}

public List<Atsconnect> getProjectsByCode(String projectCode) throws Exception {
	return logTimeDao.getProjectsByCode(projectCode);
}

public Atsconnect getProjectsById(String project) throws Exception {
	return logTimeDao.getProjectsById(project);
}

public Logtime mapTimeAndProject(Atsconnect ats, Logtime lt) throws Exception {
	return logTimeDao.mapTimeAndProject(ats,lt);
}

public Logtime mapTimeAndProject(String project, String timeId)	throws Exception {
	Logtime lt=getLogtimeById(timeId);
		Atsconnect ats=getProjectsById(project);
			Logtime ltVal=mapTimeAndProject(ats,lt);
			
	return ltVal;
}

public List<Timesloat> getTimeSloats() throws Exception {
	
	return logTimeDao.getTimeSloats();
}
}