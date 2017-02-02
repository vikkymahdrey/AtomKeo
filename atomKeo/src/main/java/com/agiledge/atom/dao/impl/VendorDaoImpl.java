package com.agiledge.atom.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.VendorDao;
import com.agiledge.atom.entities.Employee;
import com.agiledge.atom.entities.Vendor;


@Repository
public class VendorDaoImpl extends AbstractDao implements VendorDao  {
	private final static Logger logger = Logger.getLogger(VendorDaoImpl.class);

	public List<Vendor> getVendorList() throws Exception {
	
		Query query =  getEntityManager().createQuery("from Vendor where status='active'");
		List<Vendor> vendorList=query.getResultList();
		return vendorList;
	}
	public Vendor addVendor(Vendor vendor) throws Exception{
	       	persist(vendor);
	       	flush();
			return vendor;
		}
	public Vendor getVendorById(String vendorId) throws Exception {
		List<Vendor> vendorList=getEntityManager().createQuery("from Vendor where id="+vendorId).getResultList();
		return vendorList.get(0);
	}
	public void deleteUpdateVendor(Vendor vm) throws Exception{
		update(vm);
		flush();
	}
	public Employee getEmpByLoginId(String loginId) throws Exception {
		Query q=getEntityManager().createQuery("from Employee where loginId=:loginId");
		q.setParameter("loginId", loginId);
		List<Employee> empList=q.getResultList();
		return empList.get(0);
	}
	public List<Vendor> getVendorInSite(String siteId) throws Exception {
		String query="from Vendor v where v.status='active'";
		List<Vendor> vList=getEntityManager().createQuery(query).getResultList();
				
		return vList;
	}
	public List<Vendor> getVendorNotInSite(String siteId) throws Exception {
		
		String query="from Vendor v where v.status='active'";
		List<Vendor> vList=getEntityManager().createQuery(query).getResultList();
				
		return vList;
	}
	
		
		

}
		
		
		
