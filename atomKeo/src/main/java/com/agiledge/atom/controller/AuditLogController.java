package com.agiledge.atom.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletContextAware;

import com.agiledge.atom.entities.AuditLog;
import com.agiledge.atom.service.intfc.AuditLogService;

@Controller
public class AuditLogController implements ServletContextAware {

	private final static Logger logger = Logger.getLogger(VendorMasterController.class);
	
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	
	@Autowired
	AuditLogService auditLogService;
	
	@RequestMapping(value = { "/showAuditLog" }, method = {RequestMethod.POST,RequestMethod.GET})
	public String ShowAuditLog(HttpServletRequest request) throws Exception {
		String relatedNodeId = request.getParameter("relatedNodeId");
		int id = Integer.parseInt(relatedNodeId);
		String moduleName = request.getParameter("moduleName");
		String current = request.getParameter("current");
		List<AuditLog> auditEntries = auditLogService.getAllAuditLogEntries(id, moduleName, current);
		servletContext.setAttribute("auditEntries", auditEntries);
				return "ShowAuditLog";

	}
	
}
