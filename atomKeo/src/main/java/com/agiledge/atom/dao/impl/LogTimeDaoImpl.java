package com.agiledge.atom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.controller.RoutingController;
import com.agiledge.atom.dao.intfc.LogTimeDao;
import com.agiledge.atom.entities.Atsconnect;
import com.agiledge.atom.entities.Logtime;
import com.agiledge.atom.entities.SiteShift;
import com.agiledge.atom.entities.Siteshiftadhoc;
import com.agiledge.atom.entities.Timesloat;
@Repository
public class LogTimeDaoImpl extends AbstractDao implements LogTimeDao {
	private static final Logger logger = Logger.getLogger(RoutingController.class);
	
	public List<Logtime> getAllLogtime(String log) throws Exception {
		String query="from Logtime where status='active' and logtype='"+ log + "'  order by logtime ";
			Query q=getEntityManager().createQuery(query);
			List<Logtime> logtimeList=q.getResultList();
		return logtimeList;
	}
	
	public List<Logtime> getAllGeneralLogtime() throws Exception {
		
			String query="from Logtime order by logtime";
			Query q=getEntityManager().createQuery(query);
			List<Logtime> logtimes=q.getResultList();
			
		return logtimes;
	}

	public Logtime insertLogtime(Logtime logtime) throws Exception {
		
		 String query="select lt.logtime from Logtime lt where lt.logtime=:logTime and lt.logtype=:logType and lt.status='active' ";
		 Query q=getEntityManager().createQuery(query);
		 q.setParameter("logTime", logtime.getLogtime());
		 q.setParameter("logType", logtime.getLogtype());
			
		 List<Logtime> logtimeList=q.getResultList();
		 if(logtimeList==null || logtimeList.size()==0){
				persist(logtime);
			}
		flush();
		return logtime;
		}

	public List<Logtime> getAllInactiveLogtime(String logtype, String site) throws Exception {
		
		List<Logtime> logtimeResult=new ArrayList<Logtime>();
		//String query="from Logtime lt where lt.status='inactive' and lt.logtype=:logtype and lt.id in (select ss.id  from SiteShift ss where ss.site.id="+ site + ")  order by lt.logtime ";
		String query="from Logtime lt where lt.status='inactive' and lt.logtype=:logtype order by logtime";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("logtype",logtype);
		List<Logtime> logtimeList=q.getResultList();
	
		for(Logtime logtime : logtimeList){
			for(SiteShift ss: logtime.getSiteShifts()){
				if(ss.getSite().getId()==Integer.parseInt(site)){
					logtimeResult.add(ss.getLogtime());
				}
			}
		}
		return logtimeResult;
	}

	public List<Logtime> getAllLogtime(String logtype, String site)	throws Exception {
		List<Logtime> logtimeResult=new ArrayList<Logtime>();
		/*String query="from Logtime lt where lt.status='active' and lt.logtype=:logtype and (lt.id in (select ss.id  from SiteShift ss where ss.site.id="+ site + ") or lt.id in (select ssa.id  from Siteshiftadhoc ssa where ssa.site.id="
		+ site + " and ssa.status='a') order by lt.logtime ";*/
		String query="from Logtime lt where lt.status='active' and lt.logtype=:logtype order by logtime";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("logtype",logtype);
		List<Logtime> logtimeList=q.getResultList();
			
		for(Logtime logtime : logtimeList){
			for(SiteShift ss: logtime.getSiteShifts()){
				if(ss.getSite().getId()==Integer.parseInt(site)){
					Logtime lt=ss.getLogtime();
						logtimeResult.add(lt);
						if(lt.getSiteshiftadhocs()!=null && !(lt.getSiteshiftadhocs().isEmpty())){
							for(Siteshiftadhoc ssa : lt.getSiteshiftadhocs()){
								if(ssa.getStatus().trim().equals("a") || ssa.getStatus()!=null){
									if(!(ssa.getLogtime().getLogtime().equals(lt.getLogtime()))){
										logtimeResult.add(ssa.getLogtime());
								}
							}
						}
				      }	
				    }	
				}
			}
		return logtimeResult;
	}

	public Logtime getLogtimeById(String id) throws Exception {
		String query="from Logtime lt where lt.id="+id+"" ;
			Query q=getEntityManager().createQuery(query);
				List<Logtime> logtimeList=q.getResultList();
					return logtimeList.get(0);
					
	}
	
	

	public Logtime deleteLogTime(Logtime lt) throws Exception {
		update(lt);
		return lt;
	}

	public Logtime getLogtimeByIdAndStatus(String id) throws Exception {
		String query="from Logtime lt where lt.id="+id+" and status='active'" ;
		Query q=getEntityManager().createQuery(query);
			List<Logtime> logtimeList=q.getResultList();
				return logtimeList.get(0);
	}

	public Logtime updateLogtime(Logtime lt) throws Exception {
		update(lt);
		return lt;
	}

	public List<Atsconnect> getProjectsByName(String projectName) throws Exception {
		
		
			String query="from Atsconnect where description like :like";
			Query q=getEntityManager().createQuery(query);
			q.setParameter("like", "%"+ projectName + "%");
			List<Atsconnect> atsList=q.getResultList();
			
		return atsList;
	}

	public List<Atsconnect> getProjectsByCode(String projectCode) throws Exception {
		String query="from Atsconnect where project =:projectCode";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("projectCode", projectCode);
		List<Atsconnect> atsList=q.getResultList();
		
	return atsList;
	}

	public Atsconnect getProjectsById(String project) throws Exception {
		String query="from Atsconnect where id =:projectCode";
		Query q=getEntityManager().createQuery(query);
		q.setParameter("projectCode", project);
		List<Atsconnect> atsList=q.getResultList();
		return atsList.get(0);
	}

	public Logtime mapTimeAndProject(Atsconnect ats, Logtime lt) throws Exception {
		try{
			lt.getAtsconnects().add(ats);
			//ats.getLogtimes().add(lt);
			/*List<Atsconnect> atss=new ArrayList<Atsconnect>();
			atss.add(ats);
			List<Logtime> logt=new ArrayList<Logtime>();
			logt.add(lt);
			lt.setAtsconnects(atss);
			ats.setLogtimes(logt);*/
			update(lt);
			//update(ats);
			
			}catch(Exception e){
			logger.error("Error during persist ",e);
		}
		
		return lt;
	}

	public List<Timesloat> getTimeSloats() throws Exception {
		String query="from Timesloat";
			Query q=getEntityManager().createQuery(query);
				List<Timesloat> sloatList=q.getResultList();
		return sloatList;
	}
	
	
}
