package com.agiledge.atom.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.VendorMasterDao;
import com.agiledge.atom.dto.VehicleDto;
import com.agiledge.atom.entities.Tripvendorassign;
import com.agiledge.atom.entities.Vehicle;
import com.agiledge.atom.entities.Vendormaster;

@Repository
public class VendorMasterDaoImpl extends AbstractDao implements VendorMasterDao {
	private final static Logger logger = Logger.getLogger(VendorMasterDaoImpl.class);

	public List<Vendormaster> getMasterVendorList() throws Exception {
		String query="from Vendormaster vm where vm.status='active' ";
		Query q = getEntityManager().createQuery(query);
		List<Vendormaster> vendorCompanyInfo=q.getResultList();
			return vendorCompanyInfo;
	}

	public Vendormaster addVendorMaster(Vendormaster vendormaster) throws Exception {
			persist(vendormaster);
			flush();		
		return vendormaster;
	}
	
	public void deleteUpdateVendorMaster(Vendormaster vm) {
		update(vm);
		flush();
			}

	public Vendormaster getVendorMasterById(int vendormasterId) {
		String query="from Vendormaster where id="+vendormasterId;
		Query q=getEntityManager().createQuery(query);
		List<Vendormaster> vmList=q.getResultList();
		return vmList.get(0);
	}

	public Tripvendorassign assignVendorTrip(Tripvendorassign tva)	throws Exception {
		persist(tva);
		flush();
		return tva;
	}

	public ArrayList<VehicleDto> getVendorVehicles(String vendorId) throws Exception {
		
		ArrayList<VehicleDto> vehicleDtos = new ArrayList<VehicleDto>();
		VehicleDto dto = null;
	
			String query = "select id,regNo,vehicletype,vendor from vehicles where status='a' and vendor=" + vendorId
					+ " order by vehicletype,CONVERT(SUBSTRING_INDEX(regNO ,' ',-1), UNSIGNED INTEGER)";
			
			Query q=getEntityManager().createNativeQuery(query);
			List<Object[]> oList=q.getResultList();
			for(Object[] o : oList) {
				dto = new VehicleDto();
				dto.setId(String.valueOf(o[0]));
				dto.setVehicleNo(String.valueOf(o[1]));
				dto.setVehicleType(String.valueOf(o[2]));
				dto.setVendor(String.valueOf(o[3]));
				vehicleDtos.add(dto);
			}

		
		return vehicleDtos;
	}

	public List<Vehicle> getVendorVehiclesById(String vendorId) throws Exception {
		Query q=getEntityManager().createQuery("from Vehicle v where v.status='a' and v.vendormaster.id=:vendorId");
		q.setParameter("vendorId", Integer.valueOf(vendorId));
		List<Vehicle> vehicles=q.getResultList();
		return vehicles;
	}

	
	}


