package org.otsuka.beehive.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.otsuka.beehive.email.model.FileDetails;
import org.otsuka.beehive.email.model.Meeting;
import org.otsuka.beehive.email.model.UploadedFile;
import org.otsuka.beehive.email.service.CustomerService;
import org.otsuka.beehive.email.service.UtilityService;
import org.otsuka.beehive.util.ApplicationConstant;
import org.otsuka.beehive.util.FileValidator;

@Controller
public class FileUploadController {

	@Autowired
	FileValidator fileValidator;

	@Autowired
	UtilityService utilityService;

	@Autowired
	CustomerService customerService;

	@RequestMapping("/fileUploadForm")
	public ModelAndView getUploadForm(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			Meeting meeting,
			BindingResult result) {
		return new ModelAndView("uploadForm");
	}

	@RequestMapping("/fileUpload")
	public ModelAndView fileUploaded(
			@ModelAttribute("uploadedFile") UploadedFile uploadedFile,
			 
			BindingResult result) {
		InputStream inputStream = null;
		OutputStream outputStream = null;
		FileDetails fileDetails = new FileDetails();
		Meeting meeting = null;
		boolean success = false;
		MultipartFile file = uploadedFile.getFile();
		fileValidator.validate(uploadedFile, result);

		String fileName = file.getOriginalFilename();

		if (result.hasErrors()) {
			return new ModelAndView("uploadForm");
		}

		String saveDirectory = null;
		Properties configProperties = new Properties();

		// String propertyFile = "configurations.properties";

		/*
		 * if(FileUploadController.class.getClassLoader().getResourceAsStream(
		 * propertyFile) == null){
		 * System.out.println("configurations file not loaded"); }
		 * 
		 * 
		 * try {
		 * configProperties.load(FileUploadController.class.getClassLoader(
		 * ).getResourceAsStream(propertyFile)); } catch (IOException e1) { //
		 * TODO Auto-generated catch block e1.printStackTrace(); }
		 */
		// saveDirectory = (String) configProperties.get("upload.save.dir");

		try {

			saveDirectory = utilityService.getByLovName(ApplicationConstant.FILE_UPLOAD_LOCATION).getLovValue();
			System.out.println("---------------File upload directory is:"
					+ saveDirectory);
			inputStream = file.getInputStream();
			if (saveDirectory != null || saveDirectory == "") {
				File newFile = new File(saveDirectory + fileName);
				if (!newFile.exists()) {
					newFile.createNewFile();
				}
				 meeting = customerService.findByMeetingId("1");
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				java.util.Date date = Calendar.getInstance().getTime();
				System.out.println(dateFormat.format(cal.getTime())); //2014/08/06 16:00:22
				fileDetails.setDay(date);
				fileDetails.setFileName(fileName);
				fileDetails.setURL(saveDirectory + fileName); 
				fileDetails.setMeeting(meeting);
				customerService.saveFileDetails(fileDetails);
				
				outputStream = new FileOutputStream(newFile);
				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				if(outputStream != null){
					System.out.println("Tsting");
					success= true;
				}
					
				outputStream.close();
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		meeting = new Meeting();
		ModelAndView modelAndView = new ModelAndView("addmeeting", "command", meeting);
		modelAndView.addObject("validStatus", success);
		return modelAndView;
	}

}
