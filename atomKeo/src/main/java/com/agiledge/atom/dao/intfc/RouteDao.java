package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.Route;
import com.agiledge.atom.entities.Routechild;
import com.agiledge.atom.entities.Routetype;

public interface RouteDao {

	List<Routetype> getRouteTypes() throws Exception;
	Route insertRoute(Route route) throws Exception;
	Routechild insertRouteChild(Routechild routechild) throws Exception;
	Routetype getRouteTypeByType(String routeType) throws Exception;
	List<Route> getAllRoutesBySite(String site)throws Exception;
	List<Routechild> getRouteDetailsInRevers(int routeId)throws Exception;
	int orderTheRoute(Object[] landmarksInOrder, int routeId)throws Exception;
}
