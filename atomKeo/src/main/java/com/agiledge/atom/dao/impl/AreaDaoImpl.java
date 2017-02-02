package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.AreaDao;
import com.agiledge.atom.entities.Area;

@Repository("areaDao")
public class AreaDaoImpl extends AbstractDao implements AreaDao{

	public List<Area> getMatchingAreas(String areaText) throws Exception {
		Query query = getEntityManager().createQuery("from Area a where a.area like :areaText");
		query.setParameter("areaText", "%"+areaText+"%");
		return query.getResultList();
	}

}
