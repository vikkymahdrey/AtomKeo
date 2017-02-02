package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.Place;

public interface PlaceDao {

	public List<Place> getMatchingPlaces(String placeText) throws Exception;
}
