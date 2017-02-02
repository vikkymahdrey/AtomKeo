package com.agiledge.atom.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.agiledge.atom.dao.intfc.DriverDao;
import com.agiledge.atom.entities.Driver;
import com.agiledge.atom.entities.DriverVehicle;

@Repository
public class DriverDaoImpl extends AbstractDao implements DriverDao {
	private static final Logger logger = Logger.getLogger(DriverDaoImpl.class);

	public List<Driver> getDriversDetails() throws Exception {
		List<Driver> driverList=getEntityManager().createQuery("from Driver where status='a'").getResultList();
		return driverList;
	}

	public Driver getDriverById(String driverId) throws Exception {
		return (Driver)getEntityManager().createQuery("from Driver d where d.status='a' and d.id="+driverId).getResultList().get(0);
	}

	public Driver addDriver(Driver driver) throws Exception {
		persist(driver);
		flush();
		return driver;
	}

	public Driver updateDriver(Driver driver) throws Exception {
		update(driver);
		return driver;
	}

	public DriverVehicle addDriversToVehicle(DriverVehicle dv) throws Exception {
		update(dv);
		flush();
		return dv;
	}
}
