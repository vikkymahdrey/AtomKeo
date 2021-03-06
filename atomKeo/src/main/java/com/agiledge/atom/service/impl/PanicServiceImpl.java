package com.agiledge.atom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.PanicDao;
import com.agiledge.atom.dto.EscalationMatrixDto;
import com.agiledge.atom.entities.Escalationmatrix;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.service.intfc.PanicService;

@Service
public class PanicServiceImpl implements PanicService {
	
	@Autowired
	PanicDao panicDao;

	public List<Escalationmatrix> getEscallationmatrix() throws Exception {
		return panicDao.getEscallationmatrix();
	}
	
	public String saveescalation(List<EscalationMatrixDto> list) throws Exception{
		return panicDao.saveescalation(list);
	}
	
	public TripDetail getTripbyId(String id) throws Exception{
		return panicDao.getTripbyId(id);
	}

	public int stopPanic(String empid, String tripid, String causeOfalarm, String actionDesc) throws Exception {
		return panicDao.stopPanic(empid,tripid,causeOfalarm,actionDesc);
	}

	
}
