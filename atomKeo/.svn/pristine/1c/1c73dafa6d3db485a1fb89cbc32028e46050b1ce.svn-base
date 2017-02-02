package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.PlaceDao;
import com.agiledge.atom.entities.Place;

@Repository("placeDao")
public class PlaceDaoImpl extends AbstractDao implements PlaceDao{

	public List<Place> getMatchingPlaces(String placeText) throws Exception {
		Query query = getEntityManager().createQuery("from Place p where p.place like :placeText");
		query.setParameter("placeText", "%"+placeText+"%");
		return query.getResultList();
	}

}
