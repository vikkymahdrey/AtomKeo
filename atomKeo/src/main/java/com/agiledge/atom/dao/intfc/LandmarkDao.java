package com.agiledge.atom.dao.intfc;


import java.util.List;

import com.agiledge.atom.entities.Landmark;

public interface LandmarkDao {

	public List<Landmark> getMatchingLandmarks(String landmarkText) throws Exception;
	public Landmark getLandmarkById(String landmarkId) throws Exception;

}
