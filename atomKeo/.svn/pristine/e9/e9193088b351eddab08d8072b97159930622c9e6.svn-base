package com.agiledge.atom.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agiledge.atom.dao.intfc.MobileTripSheetDao;
import com.agiledge.atom.dto.TripDetailsDto;
import com.agiledge.atom.service.intfc.MobileTripSheetService;

@Service
public class MobileTripSheetServiceImpl implements MobileTripSheetService {

	@Autowired
	private MobileTripSheetDao mobileTripSheetDao;
	public ArrayList<String> getDriverPasswords(String tripId, int i, int j) throws Exception {
		return mobileTripSheetDao.getDriverPasswords(tripId,i,j);
	}
	public ArrayList<String> getEscortPasswords(String tripId, int i, int j) throws Exception {
		return mobileTripSheetDao.getEscortPasswords(tripId,i,j);
	}
	public TripDetailsDto getVehicleTripSheet(String tripId) throws Exception {
		return mobileTripSheetDao.getVehicleTripSheet(tripId);
	}

}
