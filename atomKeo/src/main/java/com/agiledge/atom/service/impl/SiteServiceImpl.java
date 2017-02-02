package com.agiledge.atom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.BranchDao;
import com.agiledge.atom.dao.intfc.SiteDao;
import com.agiledge.atom.entities.Branch;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.SiteShift;
import com.agiledge.atom.service.intfc.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteDao siteDao;
	
	@Autowired
	private BranchDao branchDao;
	
	public List<Site> getSites(int branchId) throws Exception
	{
		return siteDao.getSites(branchId);
		
	}


	public Branch addSiteToBranch(Site site, int branchId) throws Exception {
		Branch branch=branchDao.getBranchById(branchId);
		branch.addSite(site);
		siteDao.addSite(site);
		return branch;
	}


	public List<Site> getSites() throws Exception {
				return siteDao.getSites();
	}


	public Site getSiteById(int siteId) throws Exception {
		return siteDao.getSiteById(siteId);
	}


	public Site updateSite(Site site) throws Exception {
		return siteDao.updateSite(site);
	}


	public SiteShift updateSiteShift(SiteShift ss) throws Exception {
			return siteDao.updateSite(ss);
	}


	
	
}
