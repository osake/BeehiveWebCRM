package org.otsuka.beehive.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sendEmail")
public class SendEmailController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SimpleMailMessage customeMailMessage;

	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/customer/{custCode:.+}", method = RequestMethod.GET)
	public @ResponseBody Map<String, String> sendEmailByCustCode(@PathVariable("custCode") String custCode) {

		Customer customer = customerService.findByCustomerCode(custCode);

		String recipientAddress = customer.getCustEmail();
		String subject = customeMailMessage.getSubject();
		String content = customer.getCustName();
		String fileLocation = customer.getCustAttachmentLoc();
		
		Map<String, String> result = new HashMap<String, String>();
		
		// prints debug info
		try{
			sendEmail(recipientAddress, subject, content, fileLocation);
			result.put("Sucess", "Ok");
			
		}catch(Exception e){
			result.put("Error", "Email Not Sent !!!");
		}
		
		return result;

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody  Map<String, String> doSendEmail(HttpServletRequest request) {
		// takes input from e-mail form
		String recipientAddress = request.getParameter("recipient");
		String subject = request.getParameter("subject");
		String content = request.getParameter("message");

		// prints debug info
		sendEmail(recipientAddress, subject, content, "/mnt/BeehiveWebFlow/README.md");
		// forwards to the view named "Result"
		Map<String, String> result = new HashMap<String, String>();
		result.put("Sucess", "Ok");
		return result;
	}

	private void sendEmail(String recipientAddress, String subject,
			String content, String fileLoc) {
		System.out.println("To: " + recipientAddress);
		System.out.println("Subject: " + subject);
		System.out.println("Body: " + content);

		// creates a simple e-mail object
		// SimpleMailMessage email = new SimpleMailMessage();
		// email.setFrom("nagaraj.jayakumar-cw@otsuka-us.com");
		// email.setTo(recipientAddress);
		// email.setSubject(subject);
		// email.setText(message);

		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			helper.setFrom(customeMailMessage.getFrom());
			helper.setTo(recipientAddress);
			helper.setSubject(subject);
			helper.setText(String.format(customeMailMessage.getText(), content));

			FileSystemResource file = new FileSystemResource(fileLoc);
			helper.addAttachment(file.getFilename(), file);
			
			System.out.println("Attached File !!");

		} catch (MessagingException e) {
			throw new MailParseException(e);
		}

		// sends the e-mail
		try {
			mailSender.send(message);
			System.out.println("Email Sent with attachment !!");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
}