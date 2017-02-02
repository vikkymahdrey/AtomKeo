package com.agiledge.atom.dao.test;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.agiledge.atom.dao.impl.TripDetailsDaoImpl;
import com.agiledge.atom.dao.intfc.RoutingDao;
import com.agiledge.atom.dto.RoutingDto;
import com.agiledge.atom.dto.TripDetailsDto;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/coreatom-servlet.xml")
public class RoutingTest {
			
		private static final Logger logger = Logger.getLogger(RoutingTest.class);

      @Autowired
      RoutingDao routingDao;
      
      @Autowired
      TripDetailsDaoImpl tripDetailsDao;
      
		
      
      	@Test
      	@Ignore
		@Transactional
		public void getTRipSummary() throws Exception{
						
			int siteid=1;
			RoutingDto routingDto=new RoutingDto();
			routingDto.setDate("24/02/2016");
			routingDto.setTravelMode("IN");
			routingDto.setTime("21:00");
			ArrayList<TripDetailsDto> triplist=tripDetailsDao.getRoutingSummary(1, "2016-02-24", "IN","21:00");
			logger.debug("SIZE OF EMP LIST"+triplist.size());
			if(triplist!=null || triplist.size()>0){
				for(TripDetailsDto tripD: triplist)
				{
					logger.debug("TripDetails1"+tripD.getTripCount());
					logger.debug("TripDetails2"+tripD.getEmpInCount());
					logger.debug("TripDetails3"+tripD.getSecCount());
					logger.debug("TripDetails4"+tripD.getTrip_date());
					logger.debug("TripDetails5"+tripD.getTrip_log());
					logger.debug("TripDetails6"+tripD.getTrip_time());
					
					
				}
				
			
		}}
		
      	
      	@Test
      	@Transactional
      	public void getTripsSheetFrom() {			
			String str="163506";
			//TripDetailsDto dto=tripDetailsDao.getTripSheetByTrip(str);
			//logger.debug("approvalstatus"+dto.getId());
      	}
		/*@Test
		@Transactional
		public void getAllRoutingDetails() throws Exception{
						
			int siteid=1;
			RoutingDto routingDto=new RoutingDto();
			routingDto.setDate("25/01/2016");
			routingDto.setTravelMode("IN");
			routingDto.setTime("21:00");
			List<Employee> emplist=routingDao.setEmployeesForTravel(routingDto, siteid, "o");
			logger.debug("SIZE OF EMP LIST"+emplist.size());
			if(emplist!=null || emplist.size()>0){
				for(Employee emp: emplist)
				{
					logger.debug("empiddddd"+emp.getId());
					
				}
				
			
		}}*/
		
		/*@Test
		@Transactional
		public void getAllRoutingDetails() throws Exception{
						
			int siteid=1;
			String selectedEmpSubscribedIDString="2,4";
			String selectedEmployeeIDString="1986,2047";
			RoutingDto routingDto=new RoutingDto();
		
			int[] returnArray=routingDao.getBestRouteId(selectedEmpSubscribedIDString, selectedEmployeeIDString, siteid, routingDto);
		logger.debug("returnArray size"+returnArray);
			logger.debug("routeid of "+returnArray[0]);	
		logger.debug("subscriptioncount of "+returnArray[1]);	
		}*/
		
}
