package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.controller.APLConfigurationController;
import com.agiledge.atom.dao.intfc.RouteDao;
import com.agiledge.atom.entities.Landmark;
import com.agiledge.atom.entities.Route;
import com.agiledge.atom.entities.Routechild;
import com.agiledge.atom.entities.Routetype;

@Repository
public class RouteDaoImpl extends AbstractDao implements RouteDao	{
	
	private static final Logger logger = Logger.getLogger(APLConfigurationController.class);
	public List<Routetype> getRouteTypes() throws Exception {
		Query q=getEntityManager().createQuery("from Routetype order by priority");
		return q.getResultList();
	}
	
	public Route insertRoute(Route route) throws Exception {
		persist(route);
		return route;
	}
	public Routechild insertRouteChild(Routechild routechild) throws Exception {
		persist(routechild);
		return routechild;
	}

	public Routetype getRouteTypeByType(String routeType) throws Exception {
		Query q=getEntityManager().createQuery("from Routetype where type=:routeType");
		q.setParameter("routeType",routeType);
		return (Routetype) q.getResultList().get(0);
	}

	public List<Route> getAllRoutesBySite(String siteId) throws Exception {
		Query q=getEntityManager().createQuery("from Route r where r.site.id=:siteId order by r.id desc");
		q.setParameter("siteId",Integer.valueOf(siteId));
		return q.getResultList();
	}

	public List<Routechild> getRouteDetailsInRevers(int routeId) throws Exception {
		String query="from Routechild rc where rc.route.id="+routeId+" order by rc.position";
		Query q=getEntityManager().createQuery(query);
		List<Routechild> rcList=q.getResultList();
		return rcList;
		
	}

	
	public int orderTheRoute(Object[] landmarksInOrder, int routeId) throws Exception {
		int retVal=0;
		List<Routechild> rcList=getRouteChildByRoute(routeId);
		for (int i=0;i<landmarksInOrder.length;i++) {
			rcList.get(i).setPosition(landmarksInOrder.length-i-1);
			Landmark lm=getlandmarkById(landmarksInOrder[i].toString());	
			rcList.get(i).setLandmark(lm);
			try{
				update(rcList.get(i));
					retVal+=1;
			}catch(Exception e){
				logger.error("Error during update ",e);
			}
		}
	return retVal;	
	}

	public Landmark getlandmarkById(String landmarkId) throws Exception {
		String query="from Landmark lm where lm.id=:landmarkId";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("landmarkId", landmarkId);
		List<Landmark> landmarks=q.getResultList();
		if(landmarks.size()>0 && landmarks!=null){
				return landmarks.get(0);
		}
		return null;
	}

	public  List<Routechild> getRouteChildByRoute(int routeId) throws Exception{
		String query="from Routechild rc where rc.route.id="+routeId+"";
		Query q=getEntityManager().createQuery(query);
		List<Routechild> rcList=q.getResultList();
		return rcList;
		
	}

}
