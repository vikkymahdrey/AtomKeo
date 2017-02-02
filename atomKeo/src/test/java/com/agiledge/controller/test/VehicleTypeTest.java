package com.agiledge.controller.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agiledge.atom.controller.SetupController;
import com.agiledge.atom.entities.VehicleType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/coreatom-servlet.xml")
public class VehicleTypeTest {

	private static final Logger logger = Logger.getLogger(VehicleTypeTest.class);
	
	
	@Autowired
	SetupController setupController;
	
	@Test
	public void getVehicleTypes() throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		/*map.put("Ind", "indica");
		map.put("Sumo", "sumo");*/
		
		logger.debug(setupController.getVehicleType(map));
		logger.debug("getVehicleType = "+map);
		for(String key : map.keySet()){
			logger.debug("key = "+key);
			logger.debug("value = "+map.get(key));
			List<VehicleType> vts = (List<VehicleType>) map.get(key);
			for(VehicleType vt : vts){
				logger.debug("vehicle type = "+vt.getType());
			}
		}
}
}