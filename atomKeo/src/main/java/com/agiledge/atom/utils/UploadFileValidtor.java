package com.agiledge.atom.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


public class UploadFileValidtor implements Validator {  
	  
	private String userType;
	   
	   
	 public void validate(Object uploadedFile, Errors errors) {  
	  
	  UploadedFile file = (UploadedFile) uploadedFile;  
	  
	  if (file.getFile().getSize() == 0) {  
	   errors.rejectValue("file", "uploadForm.salectFile",  
	     "Please select a file!");  
	  } /* else if (file.isTruncate()) {
			AccessRightService accessService = new AccessRightService();
			if(accessService.hasRight(AccessRightConstants.ADMIN, userType)==false) {
				errors.rejectValue("file", "uploadForm.salectFile",  
					     "You have no access to this operation!");  
				  
			} 
	  }*/
	  
	 }

	
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}  
	  
	}  