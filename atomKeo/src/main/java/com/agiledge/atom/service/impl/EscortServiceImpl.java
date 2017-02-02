package com.agiledge.atom.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.EscortDao;
import com.agiledge.atom.dto.EscortDto;
import com.agiledge.atom.entities.Escort;
import com.agiledge.atom.service.intfc.EscortService;
@Service
public class EscortServiceImpl implements EscortService {
	private static final Logger logger = Logger.getLogger(EscortServiceImpl.class);
	private String errorMessage="";
	
	@Autowired
	private EscortDao escortDao;

	public List<Escort> getAllEscorts(String siteId) throws Exception {
		return escortDao.getAllEscorts(siteId);
	}

	public int assignTripEscort(ArrayList<EscortDto> escortList) throws Exception {
		return escortDao.assignTripEscort(escortList);
	}

	public Escort getEscortById(String escortId) throws Exception {
		return escortDao.getEscortById(escortId);
	}

	public List<Escort> getAllEscorts() throws Exception {
		return escortDao.getAllEscorts();
	}
	public String getErrorMessage() throws Exception{
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) throws Exception {
		this.errorMessage = errorMessage;
	}

	public boolean validateAddEscort(Escort es) throws Exception {
		boolean flag=true;
		
		if(es.getName().equals("")) {
			setErrorMessage("Name field is empty");
			flag=false;
		}
		else if(es.getEscortClock().equals("")) {
			setErrorMessage("Escort# field is empty");
			flag=false;
		}
		/*else if(es.getAddress().equals("")) {
			setErrorMessage("Address field is empty");
			flag=false;
		}*/
		
		/*else if(es.getEmail().equals("")) {
			setErrorMessage("Email field is empty");
			flag=false;
		}*/
		else if(es.getSiteBean().getId()==0) {
			setErrorMessage("Site field is empty");
			flag=false;
		}
		
		return flag;
	}

	public Escort addEscort(Escort es) throws Exception {
		return escortDao.addEscort(es);
	}

	public Escort updateEscort(Escort es) throws Exception {
		return escortDao.updateEscort(es);
	}

}
