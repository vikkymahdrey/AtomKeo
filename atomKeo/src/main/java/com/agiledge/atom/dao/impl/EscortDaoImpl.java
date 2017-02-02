package com.agiledge.atom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.config.files.RandomString;
import com.agiledge.atom.constants.SettingsConstant;
import com.agiledge.atom.dao.intfc.EscortDao;
import com.agiledge.atom.dto.EscortDto;
import com.agiledge.atom.entities.Escort;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.VendorTripSheetEscort;
import com.agiledge.atom.service.impl.SMSServiceImpl;
import com.agiledge.atom.service.intfc.SMSService;
@Repository
public class EscortDaoImpl extends AbstractDao implements EscortDao {
	private static final Logger logger = Logger.getLogger(EscortDaoImpl.class);

	@Autowired
	RandomString randomString;
	
	public List<Escort> getAllEscorts(String siteId) throws Exception {
		Query q=getEntityManager().createQuery("from Escort e where e.siteBean.id="+siteId);
		List<Escort> escortList=q.getResultList();
		return escortList;
	}

	public int assignTripEscort(ArrayList<EscortDto> escortList) throws Exception {
		int retVal = 0;
		randomString.RandomStr(4);
		
			for (EscortDto dto : escortList) {
				if(dto.getId()!=null)
				 {
					TripDetail td=getTripDetailById(dto.getTripId());					
					 	Escort escort = getEscortById(dto.getId());
					 		String password= randomString.nextEscortString(dto.getTripId());//passwordGenerator.nextString();
					 								 
					 		if(SettingsConstant.comp.equalsIgnoreCase("cd") || SettingsConstant.comp.equalsIgnoreCase("cd1"))
					 		{
					 			password=TripEscortPassword(dto.getId());
					 		}
				
				
				td.setEscort(escort);
				td.setEscortPswd(password);
				try{
				update(td);
				retVal=1;
				}catch(Exception e){
					logger.error("Error IN Updating Escort Details in TripDetail",e);
				}
				VendorTripSheetEscort vtse=td.getVendorTripSheetEscorts().get(0);
				VendorTripSheetEscort vtsee=null;
				if(vtse==null){
					vtsee=new VendorTripSheetEscort();
					vtsee.setTripDetail(td);
					vtsee.setEscort(escort);
					vtsee.setShowStatus("No Show");
					persist(vtsee);
				}
				if(retVal>0) {
					SMSService sms= new SMSServiceImpl();
					sms.sendPasswordToEscort(dto.getTripId(),password);
				}
			  }	
			}		
		return retVal;
	}



	public String TripEscortPassword(String escortid) throws Exception{
		
		String password="";
			String Query = "SELECT RIGHT(PHONE,10) AS PHONE FROM ESCORT WHERE ID="+escortid;
			Query q=getEntityManager().createNativeQuery(Query);
				List<Object[]> oList=q.getResultList();
					for(Object[] o: oList){
					password=String.valueOf(o[0]);
				}  						

		return password;


	}

	public TripDetail getTripDetailById(String tripId) throws Exception{
		return (TripDetail)getEntityManager().createQuery("from TripDetail td where td.id="+tripId).getResultList().get(0);
	}

	public Escort getEscortById(String escortId) throws Exception {
		return (Escort)getEntityManager().createQuery("from Escort esc where esc.id=:escortId").setParameter("escortId", Integer.valueOf(escortId)).getResultList().get(0);
	}

	public List<Escort> getAllEscorts() throws Exception {
		return getEntityManager().createQuery("from Escort").getResultList();
		
	}

	public Escort addEscort(Escort es) throws Exception {
		persist(es);
		flush();
		return es;
	}

	public Escort updateEscort(Escort es) throws Exception {
		update(es);
		flush();
		return es;
	}


}
