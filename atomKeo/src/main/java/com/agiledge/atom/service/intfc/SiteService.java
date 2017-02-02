package com.agiledge.atom.service.intfc;

import java.util.List;

import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.SiteShift;


public interface SiteService {
	
	public List<Site> getSites(int branchId) throws Exception;
	public Branch addSiteToBranch(Site site, int branchId) throws Exception;
	public List<Site> getSites() throws Exception;
	public Site getSiteById(int siteId) throws Exception;
	public Site updateSite(Site site) throws Exception;
	public SiteShift updateSiteShift(SiteShift ss) throws Exception;
	

}
