package com.agiledge.atom.dao.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.PanicDao;
import com.agiledge.atom.dto.EscalationMatrixDto;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Escalationmatrix;
import com.agiledge.atom.entities.Panicaction;
import com.agiledge.atom.entities.TripDetail;
import com.agiledge.atom.entities.VehiclePosition;

@Repository("panicDao")
public class PanicDaoImpl extends AbstractDao implements PanicDao{

	public List<Escalationmatrix> getEscallationmatrix() throws Exception {
		Query q=getEntityManager().createQuery("from Escalationmatrix");
		List<Escalationmatrix> list=q.getResultList();
		return list;
		
	}
	
	public String saveescalation(List<EscalationMatrixDto> list) throws Exception{
		String result="true";
		int i=1;
		List<Escalationmatrix> er=getEntityManager().createQuery("from Escalationmatrix").getResultList();
		if(er!=null&&er.size()>0){
			for(Escalationmatrix em:er){
			delete(em);
			}
		}
		for(EscalationMatrixDto em:list){
			
			if(em.getEmpid()!=null){
			Escalationmatrix e=new Escalationmatrix();
			e.setLevel(em.getLevel());
			e.setEmployee(getEntityManager().find(Employee.class, em.getEmpid()));
			e.setEscalationGroup("Admin");
			e.setTimeSlot(em.getEmptimeslot());
			update(e);
			Escalationmatrix e1=new Escalationmatrix();
			e1.setLevel(em.getLevel());
			e1.setEmployee(getEntityManager().find(Employee.class, em.getVendorempid()));
			e1.setEscalationGroup("Vendor");
			e1.setTimeSlot(em.getVendortime());
			update(e1);
			}
			else{
				Escalationmatrix e=new Escalationmatrix();
				e.setLevel(em.getLevel());
				e.setEscalationGroup("Admin");
				e.setTimeSlot(em.getEmptimeslot());
				update(e);
				Escalationmatrix e1=new Escalationmatrix();
				e1.setLevel(em.getLevel());
				e1.setEscalationGroup("Vendor");
				e1.setTimeSlot(em.getVendortime());
				update(e1);
			}
		}
		return result;
	}



public int insertPanicDetails(TripDetail t, String activatedBy) {
	int retrival = 0;
	try {
	VehiclePosition vpret=null;
	
		
			VehiclePosition vpold = t.getVehiclePositions().get(
					t.getVehiclePositions().size() - 1);

			VehiclePosition vp = new VehiclePosition();
			vp.setVehicle(t.getVehicleBean());
			vp.setLattitude(vpold.getLattitude());
			vp.setLongitude(vpold.getLongitude());
			vp.setLogstatus("danger");
			vp.setTripDetail(t);
			vp.setStatus("checkedin");
			vp.setDateTime(vpold.getDateTime());
			vpret=(VehiclePosition) update(vp);
		
		
		if(vpret!=null){
		retrival=1;
		Panicaction panic=null;
		List<Panicaction> list=t.getPanicactions();
		if(list != null && list.size()>0)	
			{
				panic=t.getPanicactions().get(0);
			}
		
		
		Date date = new java.util.Date();
		if(panic!=null)
	
		{
			panic.setTripDetail(t);
			panic.setPrimaryAction("No Action Taken");
			panic.setCurStatus("open");
			
			
			
			vp.setDateTime(vpold.getDateTime());
			update(panic);
			// if panic is activated for a trip no need to insert again
			
		}
		else
		{
			Panicaction pa= new Panicaction();
			pa.setTripDetail(t);
			pa.setCurStatus("open");
			pa.setPrimaryAction("No Action Taken");
			pa.setActivatedBy(activatedBy);
			pa.setActionTime(new Timestamp(date.getTime()));
			update(pa);
		}
		
		}

	
	
	

}
	catch(Exception e)
	{e.printStackTrace();}


return retrival;


}

public static boolean checkClosed(TripDetail td) {
	boolean flag = false;
	
		try {
			
			Panicaction pa =td.getPanicactions().get(0);
		
			String s1=new String(pa.getCurStatus());
			String s2=new String("closed"); 
			
			if(s1.equalsIgnoreCase(s2))
			{
				flag = true;
			}
			
		} catch (Exception e) {
			System.out.println("error" + e);
		}
	
		return flag;
		
}

public TripDetail getTripbyId(String id) throws Exception{
	return getEntityManager().find(TripDetail.class,id);
}




public int stopPanic(String empid, String tripid, String causeOfalarm,
		String actionDesc)  {
	int i=0;
	try{
	TripDetail t=getTripbyId(tripid);
	VehiclePosition vpret = null;

	
	System.err.println("IN stop panic"+tripid);
	
	Date dt =new Date();
	if (!t.getVehiclePositions().isEmpty()) {


		VehiclePosition vpold = t.getVehiclePositions().get(t.getVehiclePositions().size() - 1);

		VehiclePosition vp = new VehiclePosition();

		
		vp.setVehicle(t.getVehicleBean());
		vp.setLattitude(vpold.getLattitude());
		vp.setLongitude(vpold.getLongitude());
		vp.setLogstatus("run");
		vp.setTripDetail(t);
		vp.setStatus("checkedin");
		vp.setDateTime(new java.sql.Timestamp(dt.getTime()));
		

		vpret = (VehiclePosition) update(vp);
		
		if(vpret!=null){
			i=1;
		}

		System.err.println("IN stop panic 1"+vpold.getLogstatus());
	}

	Panicaction	 pa=t.getPanicactions().get(0);
	
	pa.setAcknowledgeBy(empid);
	pa.setAlarmCause(causeOfalarm);	
	pa.setCurStatus("closed");
	pa.setAcknowledgeBy(empid);
	pa.setPrimaryActiontakenBy(empid);
	pa.setPrimaryAction(actionDesc);
	pa.setApprovedBy(empid);
	pa.setStopTime(new java.sql.Timestamp(dt.getTime()));
	pa.setAcknowledgeOn(new java.sql.Timestamp(dt.getTime()));
	update(pa);
	}
	
	
	catch(Exception e){e.printStackTrace();}
	return i;
}
}	
