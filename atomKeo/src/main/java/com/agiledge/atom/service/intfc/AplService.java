package com.agiledge.atom.service.intfc;

import java.util.HashMap;
import java.util.List;

import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Place;
import com.agiledge.atom.utils.APLDto;


 public interface AplService {

	 List<Landmark> getMatchingLandmarks(String landmarkText) throws Exception;
	 List<Place> getmatchingPlaces(String placeText) throws Exception;
	 List<Area> getMatchingAreas(String areaText)  throws Exception;
	 Landmark getLandmarkById(String landmarkId) throws Exception;	
	 List<Landmark> getCDLandMarks(String landMarkName,String location,String site)throws Exception;
	 List<Landmark> getLandMarks()throws Exception;
	 List<Landmark> getLandMarks(String landMarkName,String location,String site)throws Exception;
	 List<Branch> getBranches() throws Exception;
	 List<Area> getAreasBybranchId(int branchId) throws Exception;
	 List<Area> getAreas() throws Exception;
	 List<Landmark> getLandmarksByPlaceId(String placeforLandmark) throws Exception;
	 List<Landmark> getSpecificLandmarks(String area, String place,String location) throws Exception;
	 Area insertArea(Area ar, String location) throws Exception;
	 Area updateArea(Area area) throws Exception;
	 Area getAreaById(String areaId, int branchId) throws Exception;
	 List<Place> getPlaceByAreaId(String areaId) throws Exception;
	 Area getAreaById(String areaId) throws Exception;
	 Place insertPlace(Place place, String areaId) throws Exception;
	 Place getPlaceByPlaceId(String placeId) throws Exception;
	 Place updatePlace(Place place, String areaId) throws Exception;
	 List<Landmark> getLandmarkByPlaceId(String placeId) throws Exception;
	 Landmark insertLandmark(Landmark landmark, String placeId) throws Exception;
	 Landmark updateLandmark(Landmark lm, String placeId) throws Exception;
	 HashMap<String, APLDto> getAllAPL(int[] landmarkDupe) throws Exception;
	
	
	/* int uploadAPL(InputStream in, boolean areaDupe, boolean placeDupe, boolean landmarkDupe, String location) throws Exception;
	 ArrayList<APLDto >  uploadAPLFile( InputStream in) throws Exception;
	 void showTree(AplTree aplTree) throws Exception;
	 AplTree createAplTee(ArrayList<APLDto> apls) throws Exception;*/
	
}
