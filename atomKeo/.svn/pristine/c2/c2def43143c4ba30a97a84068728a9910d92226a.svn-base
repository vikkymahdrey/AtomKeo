package com.agiledge.atom.service.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.DistanceListDao;
import com.agiledge.atom.dto.AplDistanceDto;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.entities.Distchart;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.service.intfc.DistanceListService;
import com.agiledge.atom.service.intfc.LandmarkService;
@Service
public class DistanceListServiceImpl  implements DistanceListService {
	private static final Logger logger = Logger.getLogger(DistanceListServiceImpl.class);
	
	@Autowired
	private DistanceListDao distanceListDao;
	
	@Autowired
	private LandmarkService landmarkService;
	
	public float[] getDistances(int[] landmarks, RoutingDto routingDto) throws Exception{
		return distanceListDao.getDistances(landmarks, routingDto);
	 }

	public float getDistance(String srcid, String destid) throws Exception {
		Landmark lndSrcId=landmarkService.getLandmarkById(srcid);
		Landmark lndDestId=landmarkService.getLandmarkById(destid);
		Distchart dc=new Distchart();
		dc.setLandmark2(lndSrcId);
		dc.setLandmark1(lndDestId);
		return distanceListDao.getDistance(srcid, destid,dc);
	}
	
	public void insertDistanceFromDistanceList(ArrayList<AplDistanceDto> distanceList) throws Exception{
		 distanceListDao.insertDistanceFromDistanceList(distanceList);
	}
}
