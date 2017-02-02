package com.agiledge.atom.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TransportMnagerController {

	private static final Logger logger = Logger.getLogger(TransportMnagerController.class);
	
    @RequestMapping(value= {"/tmHome"}, method=RequestMethod.GET)
	public ModelAndView home(){
		return new ModelAndView("tmHome");
	}
}
