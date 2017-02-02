package com.agiledge.atom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.ShiftDao;
import com.agiledge.atom.entities.GeneralShift;
import com.agiledge.atom.entities.Logtime;


@Repository("ShiftDao")
public class ShiftDaoImpl extends AbstractDao implements  ShiftDao{
	private final static Logger logger = Logger.getLogger(ShiftDaoImpl.class);

	/*public ArrayList<GeneralShift> getActiveLogData(String logtype) {
		ArrayList<GeneralShift> list=new ArrayList<GeneralShift>();
		String logtime = null;
		try {
		Query query = getEntityManager().createQuery("from GeneralShift generalShift where generalShift.logtype = :logtype and status is not null");
		query.setParameter("logtype", logtype);
		List<GeneralShift> generalShift = (List<GeneralShift>) query.getResultList();
		if(generalShift!=null && generalShift.size()>0)
		{
		
			for(GeneralShift shift:generalShift)
			{
				
				logtime=shift.getLogtime();
				logger.debug("logtime is "+logtime);
				list.add(shift);
			}
				
				
			}
           }catch (Exception e) {
			logger.error("Error"+e);
		} 
			return list;
	}

	public ArrayList<Logtime> getAllInactiveLogtime(String logtype, String site) {
		         logger.debug("In inactive");
				
				Logtime logtimeObj = null;
				ArrayList<Logtime> logTimeList = new ArrayList<Logtime>();
				try {
					Query query = getEntityManager().createQuery("");
					query.setParameter("status", "inactive");
					query.setParameter("logtype", logtype);
					query.setParameter("siteId", site);
					List<Logtime> logtime = (List<Logtime>) query.getResultList();
					if(logtime!=null && logtime.size()>0)
					{
						for(Logtime time:logtime)
						{
							logtimeObj.setId(time.getId());
							logtimeObj.setLogtime(time.getLogtime());
							logtimeObj.setLogtype(time.getLogtype());
							logTimeList.add(logtimeObj);
						}
					}
					
				} catch (Exception e) {
					logger.error("ERROR in Time4" + e);
				} 

				return logTimeList;
	}
*/
	}
