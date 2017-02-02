package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.dto.EscalationMatrixDto;
import com.agiledge.atom.entities.Escalationmatrix;
import com.agiledge.atom.entities.TripDetail;

public interface PanicDao {
	public List<Escalationmatrix> getEscallationmatrix() throws Exception;
	public String saveescalation(List<EscalationMatrixDto> list) throws Exception;
	public int insertPanicDetails(TripDetail td, String activatedBy);
	public TripDetail getTripbyId(String id) throws Exception;
	public int stopPanic(String empid, String tripid, String causeOfalarm,
			String actionDesc) throws Exception;
	
	
}
