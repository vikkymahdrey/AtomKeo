package com.agiledge.atom.dao.intfc;

import java.util.ArrayList;

import com.agiledge.atom.dto.AplDistanceDto;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.entities.Distchart;

public interface DistanceListDao {

	float[] getDistances(int[] landmarks, RoutingDto routingDto) throws Exception;
	public float getDistance(String srcid, String destid, Distchart dc) throws Exception;
	void insertDistanceFromDistanceList(ArrayList<AplDistanceDto> distanceList) throws Exception;
	ArrayList<String> getErrorMessageList() throws Exception;
	void setErrorMessageList(ArrayList<String> errorMessageList) throws Exception; 

}
