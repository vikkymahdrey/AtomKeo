package com.agiledge.atom.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.LandmarkDao;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.service.intfc.LandmarkService;

@Service
public class LandmarkServiceImpl  implements LandmarkService {

	@Autowired
	LandmarkDao landmarkDao;
	
	public Landmark getLandmarkById(String landmarkId) throws Exception {
		
		return landmarkDao.getLandmarkById(landmarkId);
	}

}
