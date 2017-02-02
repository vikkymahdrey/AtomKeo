package com.agiledge.atom.dao.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.agiledge.atom.dao.impl.AbstractDao;
import com.agiledge.atom.dao.intfc.BranchDao;
import com.agiledge.atom.dao.intfc.CompanyDao;
import com.agiledge.atom.dao.intfc.SiteDao;
import com.agiledge.atom.entities.Site;

//@ContextConfiguration(locations = { "/coreatom-servlet.xml","/coreatom-hibernate.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/coreatom-servlet.xml")
public class SetupConfigurationTest extends AbstractDao{
	
	private static final Logger logger = Logger.getLogger(SetupConfigurationTest.class);

	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private SiteDao siteDao;
	
	@Test
	@Transactional
	public void getSites() throws Exception{
		int branchId=1;
		List<Site> sslist=siteDao.getSites(branchId);
		logger.debug("size of list"+sslist.size());
		if(sslist!=null || sslist.size()>0){
			for(Site ss: sslist)
			{
				logger.debug("siteId"+ss.getId()+" siteName"+ss.getSiteName());
				logger.debug("size of list"+sslist.size());
			}
			
		}
			}
	

}
