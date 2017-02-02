package com.agiledge.atom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agiledge.atom.dao.intfc.DriverDao;
import com.agiledge.atom.entities.Driver;
import com.agiledge.atom.entities.DriverVehicle;
import com.agiledge.atom.service.intfc.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	private DriverDao driverDao;
	
	public List<Driver> getDriversDetails() throws Exception {
		return driverDao.getDriversDetails();
	}

	public Driver getDriverById(String driverId) throws Exception {
		return driverDao.getDriverById(driverId);
	}

	public Driver addDriver(Driver driver) throws Exception {
		return driverDao.addDriver(driver);
	}

	public Driver updateDriver(Driver driver) throws Exception {
		return driverDao.updateDriver(driver);
	}

	public DriverVehicle addDriversToVehicle(DriverVehicle dv) throws Exception {
		return driverDao.addDriversToVehicle(dv);
	}
}
