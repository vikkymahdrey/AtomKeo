package com.agiledge.atom.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.agiledge.atom.service.intfc.FilterSupportService;

public class TransportBookingInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = Logger.getLogger(TransportBookingInterceptor.class);

		
	@Autowired
	private FilterSupportService filterSupportService;
	
	
	@Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
						
		logger.debug(">>  In TransportBookingInterceptor <<");
				
		return true;
	}
	
}	

