package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.Route;
import com.agiledge.atom.entities.Routechild;
import com.agiledge.atom.entities.Routetype;

public interface RouteService {

	List<Routetype> getRouteTypes() throws Exception;

	Route insertRoute(Route route) throws Exception;

	Routechild insertRouteChild(Routechild routechild) throws Exception;

	Routetype getRouteTypeByType(String routeType) throws Exception;

	List<Route> getAllRoutesBySite(String site) throws Exception;

	int orderTheRoute(String site) throws Exception;

}
