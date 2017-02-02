package com.agiledge.atom.dao.intfc;

import java.util.List;

import com.agiledge.atom.entities.Driver;
import com.agiledge.atom.entities.DriverVehicle;

public interface DriverDao {

	List<Driver> getDriversDetails() throws Exception;

	Driver getDriverById(String driverId) throws Exception;

	Driver addDriver(Driver driver)throws Exception;

	Driver updateDriver(Driver driver)throws Exception;

	DriverVehicle addDriversToVehicle(DriverVehicle dv) throws Exception;

}
