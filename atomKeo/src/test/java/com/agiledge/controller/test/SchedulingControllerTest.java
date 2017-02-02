package com.agiledge.controller.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agiledge.atom.controller.SchedulingController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/test/resources/coreatom-servlet.xml")
public class SchedulingControllerTest {

	private static Logger logger = Logger.getLogger(SchedulingControllerTest.class);
	
	@Autowired
	private SchedulingController  schedulingController;
	
	@Test
	public void testAddSubscription(){
		MockHttpServletRequest request = new MockHttpServletRequest();
		Map<String,Object> map = new HashMap<String,Object> ();
		request.setParameter("subscriptionId", new String[] {"2062"});
		request.setParameter("project2062", "6");
		request.setParameter("from_date2062", "17/12/2015");
		request.setParameter("to_date2062", "24/12/2015");
		request.setParameter("weeklyoff2062", "on");
		request.setParameter("logintime2062", "wo");
		request.setParameter("logouttime2062", "wo");
		try {
			schedulingController.empSchedulingInfoInsertion(request, map);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
