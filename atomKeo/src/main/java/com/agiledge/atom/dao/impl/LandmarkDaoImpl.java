package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.LandmarkDao;
import com.agiledge.atom.entities.Landmark;

@Repository
public class LandmarkDaoImpl extends AbstractDao implements LandmarkDao {

	public Landmark getLandmarkById(String landmarkId) throws Exception {
	
		String query="from Landmark lm where lm.id=:landmarkId";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("landmarkId", landmarkId);
		List<Landmark> landmarks=q.getResultList();
		if(landmarks.size()>0 && landmarks!=null){
				return landmarks.get(0);
		}
		return null;
	}
	
	public List<Landmark> getMatchingLandmarks(String landmarkText)	throws Exception {
		Query query = getEntityManager().createQuery("from Landmark l where l.landmark like :landmarkText");
		query.setParameter("landmarkText", "%"+landmarkText+"%");
		return query.getResultList();
				}


	/*public Landmark getLandmarkById(String landmarkId) throws Exception {
		Query query = getEntityManager().createQuery("from Landmark l where l.id =:landmarkId");
		query.setParameter("landmarkId", landmarkId);
		return (Landmark) query.getResultList().get(0);
	}*/

}
