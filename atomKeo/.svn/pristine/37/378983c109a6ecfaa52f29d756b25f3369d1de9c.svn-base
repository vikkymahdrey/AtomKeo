package com.agiledge.atom.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agiledge.atom.dao.intfc.OtherDao;
import com.agiledge.atom.service.intfc.OtherService;

@Service
public class OtherServiceImpl implements OtherService {
	
	@Autowired
	private OtherDao otherDao;
	
	public String[] getCity(String site) throws Exception{
		return otherDao.getCity(site);
	}
}
