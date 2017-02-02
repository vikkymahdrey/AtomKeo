package com.agiledge.atom.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.RouteDao;
import com.agiledge.atom.entities.Route;
import com.agiledge.atom.entities.Routechild;
import com.agiledge.atom.entities.Routetype;
import com.agiledge.atom.service.intfc.DistanceListService;
import com.agiledge.atom.service.intfc.RouteService;

@Service
public class RouteServiceImpl implements RouteService {
	private static final Logger logger = Logger.getLogger(RouteServiceImpl.class);
	
	@Autowired
	RouteDao routeDao;
	
	@Autowired
	DistanceListService distanceListService;
	
	public List<Routetype> getRouteTypes() throws Exception {
		return routeDao.getRouteTypes();
	}

	
	public Route insertRoute(Route route) throws Exception {
		return routeDao.insertRoute(route);
	}

	public Routechild insertRouteChild(Routechild routechild) throws Exception {
		return routeDao.insertRouteChild(routechild);
	}


	public Routetype getRouteTypeByType(String routeType) throws Exception {
			return routeDao.getRouteTypeByType(routeType);
	}


	public List<Route> getAllRoutesBySite(String site) throws Exception {
		return routeDao.getAllRoutesBySite(site);
	}


	public int orderTheRoute(String site) throws Exception {
		int retVal = 0;
		
		try {
			Map<String, Float> LandmarkDist = new HashMap<String, Float>();
			List<Route> currentRoutes = getAllRoutesBySite(site);
						
			float distt = (float) 0.0;
			for (Route route : currentRoutes) {
				List<Routechild> landmarksOfROute =getRouteDetailsInRevers(route.getId());
				
				LandmarkDist = new HashMap<String, Float>();
				float intDist = (float) -1.0;
				int lastLandmarkPos = 0;
				
				if(landmarksOfROute!=null && landmarksOfROute.size()>0){
				
					for (int i = 0; i < landmarksOfROute.size(); i++) {
						distt = distanceListService.getDistance(landmarksOfROute.get(0).getLandmark().getId(), landmarksOfROute.get(i).getLandmark().getId());
							if (intDist < distt) {
								lastLandmarkPos = i;
									intDist = distt;
							}
					}
				}	
				
				
				for (int i = 0; i < landmarksOfROute.size(); i++) {
					if (i == lastLandmarkPos) {
						LandmarkDist.put(landmarksOfROute.get(lastLandmarkPos).getLandmark().getId(), (float) 0.0);
						
					} else {
						distt = distanceListService.getDistance(landmarksOfROute
								.get(lastLandmarkPos).getLandmark().getId(),
								landmarksOfROute.get(i).getLandmark().getId());
						LandmarkDist.put(landmarksOfROute.get(i).getLandmark().getId(), distt);
						
					}
				}

				LandmarkDist = sortTheMap(LandmarkDist);
				
				retVal += routeDao.orderTheRoute(LandmarkDist.keySet().toArray(), route.getId());
			}
		} catch (Exception e) {
			logger.error("Error In ", e);
		}
		return retVal;
		
	}


	


	public List<Routechild> getRouteDetailsInRevers(int routeId) throws Exception{
		return routeDao.getRouteDetailsInRevers(routeId);
	}
	
	/*public static <K, V extends Comparable<? super V>> Map<K, V> sortTheMap(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}*/
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortTheMap(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}
