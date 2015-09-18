package org.otsuka.beehive.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.otsuka.beehive.email.model.AuditCustomer;
import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.EmailForm;
import org.otsuka.beehive.email.model.State;
import org.otsuka.beehive.email.service.AuditCustomerService;
import org.otsuka.beehive.email.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Scope("session")
public class EmailFormController {
	
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SimpleMailMessage customeMailMessage;
	
	@Autowired
	AuditCustomerService auditCustomerService;
	
	private static Logger logger = Logger.getLogger(EmailFormController.class);
	private static Properties props = new Properties();
	private static final String propFileName = System.getProperty("jboss.server.config.dir") + "/config.properties";
	
	static {
		if(propFileName != null && !propFileName.isEmpty()) {
			logger.debug("propFileName---" + propFileName);
			File f = new File(propFileName);
			try {
				props.load(new FileInputStream(f));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	@RequestMapping(value = "/emailformedit", method = RequestMethod.GET)
	public ModelAndView handleEmailForm(HttpServletRequest req, HttpSession session) {
		
		EmailForm emailForm = new EmailForm();
		Object sessioObj  = session.getAttribute("emailForm");
		if(sessioObj != null) {
			emailForm = (EmailForm)sessioObj; 
		}
		
		ModelAndView modelAndView = new ModelAndView("emailformedit", "command", emailForm);
		HashMap<String, String> countries = new HashMap<String, String>();
        Map<String, State> statesMap = State.getStatesMap();
      
		
		 countries.put("USA", "USA");
		 modelAndView.addObject("statesMap", statesMap);
		 modelAndView.addObject("countries", countries);
		
		return modelAndView;
	}
	
	 @RequestMapping(value = "/emailform/{Id}", method = RequestMethod.GET)
	 public ModelAndView handleEmailFormView(@PathVariable("Id") String id, HttpServletRequest req, HttpSession session) {
		 System.out.println("Inside Handle email view");
		 System.out.println("request received with form id=" + id);
		 logger.debug("Inside Handle email view: " + propFileName);
		 logger.debug("Inside Handle email view--" + System.getProperty("jboss.server.config.dir"));
		
		 
		 File f = null;
		 if(props == null) {
			 try {
				 f = new File(propFileName);
				 props.load(new FileInputStream(f));
			} catch (Exception e) {
				logger.error("Error loading the prop file: " + f);
			}
			 
		 }
		 
		 StringBuffer docUrl = new StringBuffer();
	     docUrl.append(getURL(req)).append("/doc/CDA1.docx");

		 Customer customer = customerService.findByCustomerId(id);

		 
		 
		 EmailForm emailForm = new EmailForm();
		 emailForm.setEmail(customer.getCustEmail());
		 emailForm.setFirstName(customer.getFirstName());
		 emailForm.setLastName(customer.getLastName());
		 emailForm.setAddress(customer.getAddress1() + customer.getAddress2());
		 emailForm.setEmail(customer.getCustEmail());
		 emailForm.setTitle(customer.getTitle());
		 emailForm.setCountry(customer.getCountry());
		 emailForm.setZip(customer.getZip());
		 emailForm.setCity(customer.getCity());
		 emailForm.setState(customer.getState());
		 emailForm.setCustName(customer.getCustName());
		 
		 Map<String, State> statesMap = State.getStatesMap();
		 emailForm.setCustId(String.valueOf(customer.getCustID()));
		 emailForm.setStates(statesMap);
		 
		 emailForm.setSubject("Welcome to Mentrics");
		 emailForm.setEmailText("Dear " + customer.getFirstName() + " " + customer.getLastName() 
				 + ",<br></br>Welcome to Mentrics...... Text To be Added");
		 
		 System.out.println("emailform:" + emailForm.toString());
		 
	/**	 
		 emailForm.setFirstName("John");
		 emailForm.setLastName("Smith");
		 emailForm.setEmail("test@test1.com");
		 emailForm.setTitle("President and CEO");
		 emailForm.setAddress("123 Park Ave, Washington DC 20016");
		 emailForm.setRepName("Michelle Hills");
		 emailForm.setRepTitle("Director Business Development");
		 */
		 
		 //processNdaTemplate(emailForm);
		 
		 emailForm.setDocUrl(docUrl.toString());
		 
		 session.setAttribute("emailForm", emailForm);
	     
		 ModelAndView modelAndView = new ModelAndView("emailform", "command", emailForm);
		 HashMap<String, String> countries = new HashMap<String, String>();
		 countries.put("USA", "USA");
		 modelAndView.addObject("statesMap", statesMap);
		 modelAndView.addObject("countries", countries);

		 return modelAndView;
	   }
	 
	 
	 @RequestMapping(value = "/submitUpdateEmail", method = RequestMethod.POST)
	   public ModelAndView submitUpdateEmail(@ModelAttribute("SpringWeb")EmailForm emailForm, ModelMap model, HttpServletRequest req) {
		 System.out.println("<-- submitUpdateEmail start--");
		 //processNdaTemplate(emailForm);
		 
	     /* model.addAttribute("firstName", emailForm.getFirstName());
	      model.addAttribute("lastName", emailForm.getLastName());
	      model.addAttribute("email", emailForm.getEmail());
	      model.addAttribute("title", emailForm.getTitle());
	      model.addAttribute("address", emailForm.getAddress());
	      model.addAttribute("repTitle", emailForm.getRepTitle());
	      model.addAttribute("repName", emailForm.getRepName());
	      model.addAttribute("docUrl", emailForm.getDocUrl());
	      model.addAttribute("zip", emailForm.getZip());
	      model.addAttribute("country", emailForm.getCountry());
	      model.addAttribute("state", emailForm.getState());
	      model.addAttribute("city", emailForm.getCity());
	      
	     /* StringBuffer docUrl = new StringBuffer();
	      docUrl.append(getURL(req)).append("/doc/CDA1.docx");
	      
	      model.addAttribute("docLink", docUrl);*/
	      System.out.println("<-- submitUpdateEmail end--");
	      req.getSession().setAttribute("emailForm", emailForm);
	      return new ModelAndView("emailform", "command", emailForm);
	   }
	 
	 @RequestMapping(value = "/updateEmail", method = RequestMethod.POST)
	   public String updateEmail(@ModelAttribute("SpringWeb")EmailForm emailForm, ModelMap model, HttpServletRequest req) {
		 
		 //processNdaTemplate(emailForm);
		  System.out.println("<-- updateEmail start-->");
		  Object sessioObj  = req.getSession().getAttribute("emailForm");
			if(sessioObj != null) {
				emailForm = (EmailForm)sessioObj; 
			}
			
	      model.addAttribute("firstName", emailForm.getFirstName());
	      model.addAttribute("lastName", emailForm.getLastName());
	      model.addAttribute("email", emailForm.getEmail());
	      model.addAttribute("title", emailForm.getTitle());
	      model.addAttribute("address", emailForm.getAddress());
	      model.addAttribute("repTitle", emailForm.getRepTitle());
	      model.addAttribute("repName", emailForm.getRepName());
	      model.addAttribute("docUrl", emailForm.getDocUrl());
	      model.addAttribute("zip", emailForm.getZip());
	      model.addAttribute("country", emailForm.getCountry());
	      model.addAttribute("state", emailForm.getState());
	      model.addAttribute("city", emailForm.getCity());
	      model.addAttribute("states", emailForm.getStates());
	      
	      AuditCustomer auditCustomer = new AuditCustomer();
	      auditCustomer.setFirstName(emailForm.getFirstName());
	      auditCustomer.setCustEmail(emailForm.getEmail());
	      String custID=  emailForm.getCustId();
	      if(custID == null){
	    	EmailForm sessionEmailForm =  (EmailForm) req.getSession().getAttribute("emailForm");
	    	custID=sessionEmailForm.getCustId();
	    	
	      }
	      System.out.println("custID:------" + custID);
	      if(custID != null){
	    	  auditCustomer.setCustID(Integer.parseInt(custID));
	      }
	      auditCustomer.setAddress1(emailForm.getAddress());
	      auditCustomer.setAddress2(emailForm.getAddress());
	      auditCustomer.setCity(emailForm.getCity());
	      auditCustomer.setCountry(emailForm.getCountry());
	      auditCustomer.setLastName(emailForm.getLastName());
	      auditCustomer.setState(emailForm.getState());
	      auditCustomer.setZip(emailForm.getZip());
	      auditCustomer.setTitle(emailForm.getTitle());
	      auditCustomerService.save(auditCustomer);
	      
	      StringBuffer docUrl = new StringBuffer();
	      docUrl.append(getURL(req)).append("/doc/CDA1.docx");
	      
	      model.addAttribute("docLink", docUrl);
	      System.out.println("<-- updateEmail end-->");
	      return "emailpost";
	   }
	 
	 @RequestMapping(value = "/doc/{docName}", method = RequestMethod.GET, produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")	 
	 public @ResponseBody byte[] handleDocument(@PathVariable String docName, HttpServletRequest req) {
		 
		 logger.debug("Req - " + req.getPathInfo());
		 logger.debug("Req - " + docName);
		 
		 try {		
			 String dataDir = "";
			 String templateFileName = "";

			 if(props != null) {
				 dataDir = props.getProperty("docTemplDir");
				 templateFileName = props.getProperty("templFileName");
			 }

			 File file = new File(dataDir + "CDA1.docx");

			 FileInputStream fi = new FileInputStream(file);

			 return StreamUtils.copyToByteArray(fi);
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
		 return "".getBytes();		 
	 }
	 
	 
	 private void processNdaTemplate(EmailForm emailForm ) {/*
		 String dataDir = "";
		 String templateFileName = "";
		 
		 
		 if(props != null) {
			 dataDir = props.getProperty("docTemplDir");
			 templateFileName = props.getProperty("templFileName");
		 } else {
			 return;
		 }
		 
		 try {
			 Document doc = new Document(dataDir + templateFileName);
			 String fullName = emailForm.getFirstName() + " " + emailForm.getLastName();
			 doc.getRange().replace("_full_name_", fullName , true, false);
			 doc.getRange().replace("#title#", emailForm.getTitle(), true, false);
			 doc.getRange().replace("_address_", emailForm.getAddress() , true, false);

			 doc.getRange().replace("_full_name1_otsuka_rep_", emailForm.getRepName(), true, false);
			 doc.getRange().replace("_title1_otsuka_rep_", emailForm.getRepTitle(), true, false);
			 
			 Calendar date = Calendar.getInstance();
			 
			 DateFormat fmt = new SimpleDateFormat("dd-MMMM-yyyy");
			 String dateStr = fmt.format(date.getTime()); 
			 
			 String day = dateStr.substring(0,2);
			 String month = dateStr.substring(3,dateStr.indexOf("-", 3));
			 String year = dateStr.substring(dateStr.indexOf("-", 3) + 1);

			 doc.getRange().replace("_date_", day + "th", true, true);
			 doc.getRange().replace("_month_", month, true, true);
			 doc.getRange().replace("_year_", year, true, true);

			 doc.save(dataDir + "CDA1.docx");

		 } catch (Exception e) {
			 e.printStackTrace();
		 }

	 */}
	 
	 public static String getURL(HttpServletRequest req) {

		    String scheme = req.getScheme();             // http
		    String serverName = req.getServerName();     // hostname.com
		    int serverPort = req.getServerPort();        // 80
		    String contextPath = req.getContextPath();   // /mywebapp
		    String servletPath = req.getServletPath();   // /servlet/MyServlet
		    String pathInfo = req.getPathInfo();         // /a/b;c=123
		    String queryString = req.getQueryString();          // d=789

		    // Reconstruct original requesting URL
		    StringBuffer url =  new StringBuffer();
		    url.append(scheme).append("://").append(serverName);

		    if ((serverPort != 80) && (serverPort != 443)) {
		        url.append(":").append(serverPort);
		    }

		    url.append(contextPath);
		    //url.append(contextPath).append(servletPath);

		    if (pathInfo != null) {
		        //url.append(pathInfo);
		    }
		    if (queryString != null) {
		        //url.append("?").append(queryString);
		    }
		    return url.toString();
		}
	 
		@RequestMapping(value = "/sendemail", method = RequestMethod.GET)
		public ModelAndView handleEmailSend(HttpServletRequest req, HttpSession session) {
			
			EmailForm emailForm = new EmailForm();
			Object sessioObj  = session.getAttribute("emailForm");
			if(sessioObj != null) {
				emailForm = (EmailForm)sessioObj; 
			}
			
			String dataDir = "";

			 if(props != null) {
				 dataDir = props.getProperty("docTemplDir");
			 }			 
			
			//sendEmail("amit.choudhari-cw@otsuka-us.com","Welcome to Mentrics", "Sample Content", dataDir + "CDA1.docx");
			 sendEmail("amit.choudhari-cw@otsuka-us.com","Welcome to Mentrics", emailForm.getEmailText(), dataDir + "CDA1.docx");
			
			return new ModelAndView("emailconfirm", "command", emailForm);
		}	 
		
		@RequestMapping(value = "/customeredit", method = RequestMethod.POST)
		public ModelAndView handleCustomerEdit(@ModelAttribute("SpringWeb")Customer customer, HttpServletRequest req, HttpSession session) {
			
			Map<String, String> statesMap = State.getStates();
			customer.setStates(statesMap);
			
			ModelAndView modelAndView = new ModelAndView("customer", "command", customer);
			modelAndView.addObject("return", true);
			return modelAndView;
		}
		
		
		//@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
		public ModelAndView handleCustomerView(@PathVariable("id") String id, HttpServletRequest req, HttpSession session) {
			Customer customer = new Customer();
			if(!StringUtils.isEmpty(id)) {
				customer = customerService.findByCustomerId(id);
			}
			
			Map<String, String> statesMap = State.getStates();
			customer.setStates(statesMap);
			
			return new ModelAndView("customer", "command", customer);
		}
		
		@RequestMapping(value = "/customer", method = RequestMethod.GET)
		public ModelAndView handleAddCustomer(HttpServletRequest req, HttpSession session) {
			Customer customer = new Customer();
			
			Map<String, String> statesMap = State.getStates();
			customer.setStates(statesMap);
			
			return new ModelAndView("customer", "command", customer);
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

				helper.setFrom("amit.choudhari-cw@otsuka-us.com");
				helper.setTo(recipientAddress);
				helper.setSubject(subject);
				helper.setText(String.format(customeMailMessage.getText(), content), true);

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
