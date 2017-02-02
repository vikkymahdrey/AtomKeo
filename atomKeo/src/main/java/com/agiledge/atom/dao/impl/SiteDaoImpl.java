package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.SiteDao;
import com.agiledge.atom.entities.Site;
import com.agiledge.atom.entities.SiteShift;

@Repository
public class SiteDaoImpl extends AbstractDao implements SiteDao {
	private static final Logger logger = Logger.getLogger(CompanyDaoImpl.class);
	
	public List<Site> getSites(int branchId) {
	  String query="from Site s where s.branchBean.id="+branchId;
		Query q=getEntityManager().createQuery(query);
		List<Site> sites=q.getResultList();	
		return sites;
		}

	public void addSite(Site site) {
			persist(site);
				flush();
	}

	public List<Site> getSites() throws Exception {
		List<Site> siteList=getEntityManager().createQuery("from Site").getResultList();
		return siteList;
	}

	public Site getSiteById(int siteId) {
		Query q=getEntityManager().createQuery("from Site where id=:siteId");
		q.setParameter("siteId", siteId);
		List<Site> sitesList=q.getResultList();
		return sitesList.get(0);
	}

	public Site updateSite(Site site) throws Exception {
		update(site);
		return site;
	}

	public SiteShift updateSite(SiteShift ss) throws Exception {
		update(ss);
		flush();
		return ss;
	}
			
}
