package com.agiledge.atom.service.intfc;

import java.util.ArrayList;
import java.util.List;

import com.agiledge.atom.dto.EscortDto;
import com.agiledge.atom.entities.Escort;

public interface EscortService {

	List<Escort> getAllEscorts(String siteId) throws Exception;
	int assignTripEscort(ArrayList<EscortDto> escortList) throws Exception;
	Escort getEscortById(String escortId) throws Exception;
	List<Escort> getAllEscorts() throws Exception;
	String getErrorMessage() throws Exception;
	void setErrorMessage(String errorMessage) throws Exception; 
	boolean validateAddEscort(Escort es) throws Exception;
	Escort addEscort(Escort es)throws Exception;
	Escort updateEscort(Escort es)throws Exception;

}
