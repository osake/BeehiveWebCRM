package org.otsuka.beehive.util;

import org.otsuka.beehive.email.model.UploadedFile;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {

	public boolean supports(Class<?> arg0) {  
	  // TODO Auto-generated method stub  
	  return false;  
	 }  
	  
	 public void validate(Object uploadedFile, Errors errors) {  
	  
	  UploadedFile file = (UploadedFile) uploadedFile;  
	  
	  if (file.getFile().getSize() == 0) {  
	   errors.rejectValue("file", "uploadForm.selectFile",  
	     "Please select a file!");  
	  }  
	  
	 }  

}
