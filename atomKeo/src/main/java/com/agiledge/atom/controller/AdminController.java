package com.agiledge.atom.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class AdminController {
	
	private static final Logger logger = Logger.getLogger(AdminController.class);
	
    @RequestMapping(value= {"/adminHome"}, method=RequestMethod.GET)
	public ModelAndView home(){
		return new ModelAndView("adminHome");
	}
}
