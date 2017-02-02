package com.agiledge.atom.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.APLDao;
import com.agiledge.atom.entities.Area;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Place;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.utils.APLDto;

@Repository
public class APLDaoImpl extends AbstractDao implements APLDao {

	static final Logger logger=Logger.getLogger(APLDaoImpl.class);
    private String message;

public List<Landmark> getCDLandMarks(String landMarkName,String location,String site) throws Exception {
	
	String query="";
	String conditionVal="";
	if(site==null)
	{
		//query = "select landMark.id id, landMark.landMark landMark, place.place place, area.area area from landMark  join place on landMark.place=place.id join area on place.area=area.id  where area.location=? and (landMark.landMark like ? or place.place like ? or area.area like ?) order by area.area,place.place,landmark.landmark;";
		query="from Landmark l join l.placeBean p join p.areaBean a where a.branch=? and (l.landmark like ?  or p.place like ? or a.area like ? ) order by a.area,p.place,l.landmark";
		conditionVal=location;
	}		 
	else{
		//query = "select landMark.id id, landMark.landMark landMark, place.place place, area.area area from site, landMark  join place on landMark.place=place.id join area on place.area=area.id  where  site.id=? and  area.location=site.branch and landMark.empstat='y' and (landMark.landMark like ? or place.place like ? or area.area like ?) order by area.area,place.place,landmark.landmark;";
		query="from Landmark l,site s join l.placeBean p join p.areaBean a where s.id=? and a.branch=s.branchBean and l.empstat='y' and (l.landmark like ?  or p.place like ? or a.area like ? ) order by a.area,p.place,l.landmark";
		conditionVal=site;
	}
	
	Query q=getEntityManager().createQuery(query);
		q.setParameter(0, conditionVal);
		q.setParameter(1, "%" + landMarkName + "%");
		q.setParameter(2, "%" + landMarkName + "%");
		q.setParameter(3, "%" + landMarkName + "%");
		List<Landmark> landmarkList=q.getResultList();
		
	/*	APLDto dto = new APLDto();
		dto.setLandMarkID(rs.getString("id"));
		dto.setLandMark(rs.getString("area") + " ->"
				+ rs.getString("place") + " ->"
				+ rs.getString("landMark"));*/
		
	return landmarkList;
}


public List<Landmark> getLandMarks(String landMarkName,String location,String site) throws Exception{
	
	Query  query=null;
	String conditionVal="";
	List<Landmark> returnList = new ArrayList<Landmark>();
	query = getEntityManager().createQuery("from Landmark l where l.placeBean.areaBean.branch.id =:branchId and l.landmark like :landMarkName");
		
	query.setParameter("branchId", Integer.parseInt(location));
	query.setParameter("landMarkName", landMarkName);
		
	List<Landmark> landmarks = query.getResultList();
	if(site != null && !(site.isEmpty())){
		for(Landmark eachLandmark : landmarks){
			for(Site s :eachLandmark.getPlaceBean().getAreaBean().getBranch().getSites()){
				if(s.getId() == Integer.parseInt(site))
					returnList.add(eachLandmark);
			}
		}
	}else
		returnList = landmarks;
	
	return returnList;
}



public List<Landmark> getLandMarks() throws Exception{
	
	String query="from Landmark l order by l.landmark";
	Query q=getEntityManager().createQuery(query);
	List<Landmark> landmarkList=q.getResultList();
		
	return landmarkList;
}


public List<Branch> getBranches() throws Exception {
	String query="from Branch";
	Query q=getEntityManager().createQuery(query);
	List<Branch> branchList=q.getResultList();
	return branchList;
}


public List<Area> getAreasBybranchId(int branchId) throws Exception {
	String query="from Area where location=:location";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("location", branchId);
	List<Area> areaList=q.getResultList();
	return areaList;
	
}


public List<Area> getAreas() throws Exception {
	String query="from Area";
	Query q=getEntityManager().createQuery(query);
	List<Area> areaList=q.getResultList();
	return areaList;
}


public List<Landmark> getLandmarksByPlaceId(String placeforLandmark)
		throws Exception {
	Query query =  getEntityManager().createQuery("from Landmark l where l.placeBean.id =:placeforLandmark");
	query.setParameter("placeforLandmark", placeforLandmark);
	
	return query.getResultList();
}


public List<Landmark> getSpecificLandmarks(String area, String place,
		String location) throws Exception {
	Query query = null;
	if(area != null){
		query =getEntityManager().createQuery("from Landmark l where l.placeBean.areaBean.id =:area");
		query.setParameter("area", area);
	}else if (place != null){
		query =getEntityManager().createQuery("from Landmark l where l.placeBean.id =:place");
		query.setParameter("place", place);
	}else if (location != null){
		query =getEntityManager().createQuery("from Landmark l where l.placeBean.areaBean.branch.id =:location");
		query.setParameter("location", Integer.parseInt(location));
	}
	return query.getResultList();
		
}


public Area insertArea(Area ar,String branchId) throws Exception {
	String query="from Area a where a.area=:area and a.branch.id=:branchId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("area",ar.getArea());
	q.setParameter("branchId",Integer.parseInt(branchId));
	List<Area> areaList=q.getResultList();
	if(areaList==null || areaList.size()==0){
		persist(ar);
		flush();
		return ar;
	}
	return null;
	
	
}


public Area getAreaById(String areaId,int branchId) throws Exception {
	String query="from Area a where a.id=:areaId and a.branch.id=:branchId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("areaId",areaId);
	q.setParameter("branchId",Integer.valueOf(branchId));
	List<Area> areaList=q.getResultList();
	return areaList.get(0);
}


public Area updateArea(Area area) {
	
	String query="from Area a where a.area=:area";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("area",area.getArea());
	List<Area> areaList=q.getResultList();
	if(areaList==null || areaList.size()==0){
		update(area);
		flush();
		return area;
	}
	return null;
}


public List<Place> getPlaceByAreaId(String areaId) throws Exception {
	String query="from Place p where p.areaBean.id=:areaId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("areaId",areaId);
	List<Place> places=q.getResultList();
	return places;
}


public Area getAreaById(String areaId) throws Exception {
	String query="from Area a where a.id=:areaId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("areaId",areaId);
	List<Area> areaList=q.getResultList();
	return areaList.get(0);
}


public Place insertPlace(Place place,String areaId) throws Exception {
	String query="from Place p where p.place=:place and p.areaBean.id=:areaId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("place",place.getPlace());
	q.setParameter("areaId", areaId);
	List<Place> placeList=q.getResultList();
	if(placeList==null || placeList.size()==0){
		persist(place);
		flush();
		return place;
	}
	return null;
}


public Place getPlaceByPlaceId(String placeId) throws Exception {
	String query="from Place p where p.id=:placeId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("placeId",placeId);
		return (Place)q.getResultList().get(0);
}


public Place updatePlace(Place place,String areaId) throws Exception {
	String query="from Place p where p.place=:place and p.areaBean.id=:areaId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("place",place.getPlace());
	q.setParameter("areaId",areaId);
	List<Place> placeList=q.getResultList();
	if(placeList==null || placeList.size()==0){
		update(place);
		flush();
		return place;
	}
	return null;
}


public Landmark insertLandmark(Landmark landmark, String placeId) throws Exception {
	
	String query="from Landmark l where l.landmark=:landmark and l.placeBean.id=:placeId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("landmark",landmark.getLandmark());
	q.setParameter("placeId", placeId);
	List<Landmark> landmarkList=q.getResultList();
	if(landmarkList==null || landmarkList.size()==0){
		persist(landmark);
		flush();
		return landmark;
	}
	return null;
}


public Landmark updateLandmark(Landmark lm, String placeId) throws Exception {
	String query="from Landmark lm where lm.landmark=:landmarkName and lm.placeBean.id=:placeId";
	Query q=getEntityManager().createQuery(query);
	q.setParameter("landmarkName",lm.getLandmark());
	q.setParameter("placeId",placeId);
	List<Landmark> landmarkList=q.getResultList();
	if(landmarkList==null || landmarkList.size()==0){
		update(lm);
		flush();
		return lm;
	}
	return null;
}

public HashMap<String,APLDto> getAllAPL(int landmark[]) throws Exception {
	HashMap<String,APLDto>  aplMap= new HashMap<String, APLDto>();
	
	
		// Connection pooling implementation
		String query="from Landmark lm where lm.id=:landmarkId";
		Query q=getEntityManager().createQuery(query);
		//String query = "select landMark.id id, landMark.landMark landMark, place.place place, area.area area,landmark.latitude,landmark.longitude from landMark  join place on landMark.place=place.id join area on place.area=area.id and landmark.id=? order by area.area,place.place,landmark.landmark ";
		for(int landmarkInt : landmark ) {
			q.setParameter("landmarkId", String.valueOf(landmarkInt));
		List<Landmark> lmList=q.getResultList();
		for(Landmark lm: lmList){
			if(aplMap.get(lm.getId()) == null) {
				APLDto dto = new APLDto();
				dto.setLandMarkID(lm.getId());
				dto.setLandMark(lm.getLandmark());
				dto.setPlace(lm.getPlaceBean().getPlace());
				dto.setArea(lm.getPlaceBean().getAreaBean().getArea());
				dto.setLattitude(String.valueOf(lm.getLatitude()));
				dto.setLongitude(String.valueOf(lm.getLongitude()));
		
				aplMap.put(lm.getId(), dto);
			}
		}
	}	
		
		 
		
		
	return aplMap;

}


}
