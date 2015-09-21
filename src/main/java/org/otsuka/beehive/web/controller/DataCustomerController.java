package org.otsuka.beehive.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.CustomerAudit;
import org.otsuka.beehive.email.model.CustomerStageAudit;
import org.otsuka.beehive.email.model.FileDetails;
import org.otsuka.beehive.email.model.Lov;
import org.otsuka.beehive.email.model.Meeting;
import org.otsuka.beehive.email.model.State;
import org.otsuka.beehive.email.service.CustomerService;
import org.otsuka.beehive.email.service.UtilityService;
import org.otsuka.beehive.util.ApplicationConstant;
import org.otsuka.beehive.util.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DataCustomerController {
 
	@Autowired
	CustomerService customerService;
	
	@Autowired
	FileValidator fileValidator;

	@Autowired
	UtilityService utilityService;
	
	@Autowired private ServletContext servletContext;
	
	 /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;
             
    /**
     * Path of the file to be downloaded, relative to application's directory
     */
    private String filePath = "/resources/uf/";


    
    @RequestMapping(value = "/customersel", method = RequestMethod.GET)
	public ModelAndView handleCustomerSelView(HttpServletRequest req, HttpSession session) {
    	   	
    	ModelAndView modelAndView = new ModelAndView();
    	if (!validateUser(req)) {
    		modelAndView = new ModelAndView("userRegister");
    		return modelAndView;
    	}
    	
    	try {    		
		List<Customer> custList = customerService.findAllCustomers();
    		List<Customer> uniqueCustList = new ArrayList<Customer>();
		
    		if(custList != null  && (!custList.isEmpty())) {
    			for(Customer currObj : custList) {
    				if(!uniqueCustList.contains(currObj)) {
    					uniqueCustList.add(currObj);
    				}
    			}
    		}
		Customer cust = new Customer();
    		modelAndView = new ModelAndView("custselect","command", cust);    	
    		modelAndView.addObject("customerList", uniqueCustList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelAndView;
	}
/*
	@RequestMapping(value = "/customerData/{Id}", method = RequestMethod.GET)
	public ModelAndView handleDataFormView(@PathVariable("Id") String id,
			HttpServletRequest req, HttpSession session) {

		Customer customer = customerService.findByCustomerId(id);

		ModelAndView modelAndView = new ModelAndView("dataviewCustomer",
				"command", customer);
	
		modelAndView.addObject("meetingListContainer", customer);
		// session.setAttribute("customerId", id);
		// req.setAttribute("command", customer);
		return modelAndView;

	}*/
	
	private void createCustomerModelView (ModelAndView modelAndView, Customer customer) {
		
		System.out.println("****************DataCustomerController: createCustomerModelView() enter******************");
	
		List<Lov> primarySalesLead = null;
		List<Lov> OPENMINDSME = null;
		List<Lov> IBMSME = null;
		List<Lov> OTSUKASME = null;
		
		primarySalesLead =  utilityService.getListByLovTypeAndName(ApplicationConstant.PRIMARY_SALES_LEAD,
				            ApplicationConstant.SALES_LEAD_NAME);
		OPENMINDSME =  utilityService.getListByLovTypeAndName(ApplicationConstant.OPENMINDSME,
				ApplicationConstant.OPENMINDSME_NAME);
		IBMSME =  utilityService.getListByLovTypeAndName(ApplicationConstant.IBMSME,
				ApplicationConstant.IBMSME_NAME);
		OTSUKASME =  utilityService.getListByLovTypeAndName(ApplicationConstant.OTSUKASME,
				ApplicationConstant.OTSUKASME_NAME);
		
		System.out.println("Test--------------------------------"+customer.getCustID());
		
		Map<String, String> statesMap = State.getStates();
		customer.setStates(statesMap);
		
		String oldPhoneNumber= customer.getPhone();
		String oldCellPhone = customer.getCellPhone();
		String secPhoneNumber= customer.getSecphone();
		String secCellPhone = customer.getSeccelphone();
		
		if(customer.getPhone() != null && customer.getPhone().length() > 6) {
			String phoneAreaCode = customer.getPhone().substring(0,3);
			String phonePrefix = customer.getPhone().substring(3,6);
			String phoneSuffix = customer.getPhone().substring(6);
			//String encryptedPhoneNumber  = customer.getPhone().substring(customer.getPhone().length()-4);
			customer.setPhone(phoneAreaCode + "-" + phonePrefix + "-" + phoneSuffix);
		}
		
		if(customer.getCellPhone() != null &&  customer.getCellPhone().length() > 6) {
			String phoneAreaCode = customer.getCellPhone().substring(0,3);
			String phonePrefix = customer.getCellPhone().substring(3,6);
			String phoneSuffix = customer.getCellPhone().substring(6);
			//String encryptedPhoneNumber  = customer.getPhone().substring(customer.getPhone().length()-4);
			customer.setCellPhone(phoneAreaCode + "-" + phonePrefix + "-" + phoneSuffix);
		}
		
		
		if(customer.getSecphone() != null &&  customer.getSecphone().length() > 6) {
			String phoneAreaCode = customer.getSecphone().substring(0,3);
			String phonePrefix = customer.getSecphone().substring(3,6);
			String phoneSuffix = customer.getSecphone().substring(6);
			//String encryptedPhoneNumber  = customer.getPhone().substring(customer.getPhone().length()-4);
			customer.setSecphone(phoneAreaCode + "-" + phonePrefix + "-" + phoneSuffix);
		}
		
		if(customer.getSeccelphone() != null && customer.getSeccelphone().length() > 6) {
			String phoneAreaCode = customer.getSeccelphone().substring(0,3);
			String phonePrefix = customer.getSeccelphone().substring(3,6);
			String phoneSuffix = customer.getSeccelphone().substring(6);
			//String encryptedPhoneNumber  = customer.getPhone().substring(customer.getPhone().length()-4);
			customer.setSeccelphone(phoneAreaCode + "-" + phonePrefix + "-" + phoneSuffix);
		}
		
		
		List<String> stageAuditIdList = new ArrayList<String>();
		List<String> stageAuditStartDtList = new ArrayList<String>();
		List<String> stageAuditEndDtList = new ArrayList<String>();
		
		List<CustomerStageAudit> stageAuditList = customerService.findCustomerStageAudit(customer);
		if(stageAuditList != null && (!stageAuditList.isEmpty())) {

			SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

			for(CustomerStageAudit stageAudit : stageAuditList) {
				stageAuditIdList.add(stageAudit.getStage());

				if(stageAudit.getNewStageStartDate() != null) {
					System.out.println("stageAudit.getNewStageStartDate() - " + stageAudit.getNewStageStartDate());
					stageAuditStartDtList.add(format.format(stageAudit.getNewStageStartDate()));
				} else {
					stageAuditStartDtList.add("");
				}

				if(stageAudit.getNewStageEndDate() != null) {
					stageAuditEndDtList.add(format.format(stageAudit.getNewStageEndDate()));
				} else {
					stageAuditEndDtList.add("");
				}

				if(stageAudit != null && stageAudit.getStage() != null && stageAudit.getStage().equals(customer.getStage())) {

					customer.setStartTime(stageAudit.getNewStageStartDate());
					customer.setEndTime(stageAudit.getNewStageEndDate());

					System.out.println("Cust step start : " + customer.getStartTime() + "   " + customer.getEndTime());
				}
			}
		}

		ObjectMapper mapper = new ObjectMapper();

		Map<String, List<String>> stageAuditMap = new HashMap<String, List<String>>();

		stageAuditMap.put("stageId", stageAuditIdList);
		stageAuditMap.put("stageStart", stageAuditStartDtList);
		stageAuditMap.put("stageEnd", stageAuditEndDtList);

		System.out.println("stageAuditMap----------- " + stageAuditMap);

		String json = "";
		try {
			json = mapper.writeValueAsString(stageAuditMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//List<FileDetails> fileDetails = customerService.findAllUploadedFiles();

		modelAndView.addObject("custPhone", oldPhoneNumber);
		modelAndView.addObject("cellPhone", oldCellPhone);
		modelAndView.addObject("secPhone", secPhoneNumber);
		modelAndView.addObject("seccellPhone", secCellPhone);
		modelAndView.addObject("stepData", customer.getStage());
		modelAndView.addObject("salesLead", primarySalesLead);
		modelAndView.addObject("OPENMINDSME", OPENMINDSME);
		modelAndView.addObject("IBMSME", IBMSME);
		modelAndView.addObject("OTSUKASME", OTSUKASME);
		modelAndView.addObject("stepStartOldDate", customer.getStepStartDate());
		modelAndView.addObject("stageAudit", json);
		//modelAndView.addObject("fileListContainer", fileDetails);
		System.out.println("meetingListContainer size-----------------------------------" + customer.getMeetingList());
		modelAndView.addObject("meetingListContainer",customerService.findByMeetings(customer));
		System.out.println("Meeting list with flag: " + customerService.findByMeetings(customer));
		//modelAndView.addObject("meetingListContainer", customer.getMeetingList());
		System.out.println("****************DataCustomerController: createCustomerModelView() exit******************");


	}
	
	@RequestMapping(value = "/customer/{name}", method = RequestMethod.GET)
	public ModelAndView handleCustViewByName(@PathVariable("name") String name,
			HttpServletRequest req, HttpSession session) {				
		try {
			Customer customer = customerService.findByCustomerName(name);
		
			ModelAndView modelAndView = new ModelAndView("customerdata", "command", customer);

			createCustomerModelView(modelAndView, customer);
			return modelAndView;
		} catch (Exception e) {
			e.printStackTrace();
			//return null;
		}

		ModelAndView errorView = new ModelAndView("error");
		return errorView;


	}
	
	@RequestMapping(value = "/cust/{Id}", method = RequestMethod.GET)
	public ModelAndView handleCustomerView(@PathVariable("Id") String id,
			HttpServletRequest req, HttpSession session) {
		
		
		try {
			Customer customer = customerService.findByCustomerId(id);
			ModelAndView modelAndView = new ModelAndView("customerdata", "command", customer);
			
			createCustomerModelView(modelAndView, customer);

		return modelAndView;
		} catch(Exception e) {
			e.printStackTrace();
		}
		ModelAndView errorView = new ModelAndView("error");
		return errorView;

	}

	/*@RequestMapping(value = "/updateCustomerView/{Id}", method = RequestMethod.GET)
	// pass customer id and show update view
	public ModelAndView updateCustomerView(@PathVariable("Id") String id) {

		Customer customer = customerService.findByCustomerId(id);
		System.out
				.println("------------------- updateCustomerView -----------------------"
						+ customer.getAddress1());
		ModelAndView modelAndView = new ModelAndView("editcustomerdetails",
				"command", customer);

		System.out
				.println("--******************---------------- updateCustomerView -----------------------");
		return modelAndView;

	}*/

	@RequestMapping(value = "/submitupdatedcustomer", method = RequestMethod.POST)
	// pass customer and save it to DB
	public String updateCustomer(@ModelAttribute("SpringWeb") Customer customer,HttpServletRequest request, HttpSession session) {
		
		Customer customerOld = customerService.findByCustomerId(customer.getCustID().toString());
		String  phoneNo = customer.getPhone();
		System.out.println("CEll Phone Number::::::::::::::::"+customer.getCellPhone());
		if (phoneNo.matches("\\d{10}")){System.out.println("Valid Phone Number");}
        //validating phone number with -, . or spaces
        else if(phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) 
        {System.out.println("Valid Phone Number");}
        //validating phone number with extension length from 3 to 5
        else if(phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|(ext))\\d{3,5}")) 
        {System.out.println("Valid Phone Number:" + phoneNo);}
        //validating phone number where area code is in braces ()
        else if(phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}")) 
        {System.out.println("Valid Phone Number");}
        //return false if nothing matches the input
        else customer.setPhone(customerOld.getPhone());
		
		Date currentDate = Calendar.getInstance().getTime();
		customer.setLastUpdateDate(currentDate);
		
		System.out
				.println("----------------------customer being saved is:---------"
						+ customer.getCustID()
						+ " First name:"
						+ customer.getCustName()
						+ "Dt: " + customer.getStepStartDate());
		
		
		   // browser and os details
	    String  browserDetails  =   request.getHeader("User-Agent");
        String  userAgent       =   browserDetails;
        String  user            =   userAgent.toLowerCase();

        String os = "";
        String browser = "";

        System.out.println("User Agent for the request is===>"+browserDetails);
        
        //=================OS=======================
         if (userAgent.toLowerCase().indexOf("windows") >= 0 )
         {
             os = "Windows";
         } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
         {
             os = "Mac";
         } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
         {
             os = "Unix";
         } else if(userAgent.toLowerCase().indexOf("android") >= 0)
         {
             os = "Android";
         } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
         {
             os = "IPhone";
         }else{
             os = "UnKnown, More-Info: "+userAgent;
         }
         
         //===============Browser===========================
        if (user.contains("msie"))
        {
            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
        } else if (user.contains("safari") && user.contains("version"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
        } else if ( user.contains("opr") || user.contains("opera"))
        {
            if(user.contains("opera"))
                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
            else if(user.contains("opr"))
                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
        } else if (user.contains("chrome"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
        {
            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
            browser = "Netscape-?";

        } else if (user.contains("firefox"))
        {
            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
        } else if(user.contains("rv"))
        {
            browser="IE";
        } else
        {
            browser = "UnKnown, More-Info: "+userAgent;
        }
        System.out.println("Operating System======>"+os);
        System.out.println("Browser Name==========>"+browser);
        
              
      // IP details
        String ipAddress = request.getHeader("X-FORWARDED-FOR");  
        if (ipAddress == null) {  
     	   ipAddress = request.getRemoteAddr();  
        }
        
        
        CustomerAudit customerAudit = customerService.findCustomerAudit(customer);
       
        customerAudit.setCustomer(customer);
    	customerAudit.setBrowser(browser);
    	customerAudit.setOperatingSystem(os);
    	customerAudit.setDay(currentDate);
    	customerAudit.setIpAddress(ipAddress);
	
        try {
        	
        	boolean newStageAuditFlag = true;
        	
        	
        	List<CustomerStageAudit> stageAuditList = customerService.findCustomerStageAudit(customer);
    		if(stageAuditList != null && (!stageAuditList.isEmpty())) {
    			
    			for(CustomerStageAudit stageAudit : stageAuditList) {
    				if(stageAudit != null && stageAudit.getStage() != null && stageAudit.getStage().equals(customer.getStage())) {
    					
    					newStageAuditFlag = false;
    					stageAudit.setNewStageEndDate(customer.getEndTime()); 
    					stageAudit.setNewStageStartDate(customer.getStartTime());
    					    					
    					System.out.println("stageAudit-- " + stageAudit.getAuditId());
    						
    					customerService.updateCustomerStageAudit(stageAudit);
    				}
    			}
    		}
        	
    		if(newStageAuditFlag) {
    			CustomerStageAudit customerStageAudit = new CustomerStageAudit();
    			customerStageAudit.setCustomer(customer);
    			customerStageAudit.setNewStageEndDate(customer.getEndTime()); 
    			customerStageAudit.setNewStageStartDate(customer.getStartTime());
    			customerStageAudit.setStage(customer.getStage()); 
    			customerService.saveCustomerStageAudit(customerStageAudit); 
    		}
        	 
    		customerService.updateCustomerAudit(customerAudit);
        	customerService.update(customer); 
        	
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	
        return "redirect:/cust/" + customer.getCustID() + "?data=1";

		/*ModelAndView modelAndView = new ModelAndView("customerdata", "command", customer);

		return modelAndView;*/

	}

	/*@RequestMapping(value="/addNewCustomer", method = RequestMethod.GET)
	public ModelAndView addNewCustomer(){
		
		ModelAndView modelAndView = new ModelAndView("addNewCustomer");
		return modelAndView;
	}*/

	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	// pass customer and save it to DB
	public ModelAndView addCustomer() {
		
			Customer newCust = new Customer();
			
//starts of list
			//ModelAndView modelAndView1 = new ModelAndView("addCustomer","command", newCust);
			List<Customer> custList = customerService.findAllCustomers();
    		List<Customer> uniqueCustList = new ArrayList<Customer>();
		
    		if(custList != null  && (!custList.isEmpty())) {
    			for(Customer currObj : custList) {
    				if(!uniqueCustList.contains(currObj)) {
    					uniqueCustList.add(currObj);
    				}
    			}
    		}
		/*Customer cust1 = new Customer();
    		modelAndView1 = new ModelAndView("custselect","command", cust1);    	
    		modelAndView1.addObject("customerList", uniqueCustList);
    		//end of list
*/    		
    		
    		
    		
    		/*System.out.println("****************************customer usique list********************************");
    		System.out.println(uniqueCustList);
    		System.out.println("****************************customer usique list********************************");*/
			
			Map<String, String> statesMap = State.getStates();
			newCust.setStates(statesMap);
			
			List<Lov> primarySalesLead = null;
			List<Lov> OPENMINDSME = null;
			List<Lov> IBMSME = null;
			List<Lov> OTSUKASME = null;
			
			primarySalesLead =  utilityService.getListByLovTypeAndName(ApplicationConstant.PRIMARY_SALES_LEAD, ApplicationConstant.SALES_LEAD_NAME);
			OPENMINDSME =  utilityService.getListByLovTypeAndName(ApplicationConstant.OPENMINDSME,ApplicationConstant.OPENMINDSME_NAME);
			IBMSME =  utilityService.getListByLovTypeAndName(ApplicationConstant.IBMSME,ApplicationConstant.IBMSME_NAME);
			OTSUKASME =  utilityService.getListByLovTypeAndName(ApplicationConstant.OTSUKASME,ApplicationConstant.OTSUKASME_NAME);

			ModelAndView modelAndView = new ModelAndView("addCustomer","command", newCust);
			
			modelAndView.addObject("salesLead", primarySalesLead);
			modelAndView.addObject("OPENMINDSME", OPENMINDSME);
			modelAndView.addObject("IBMSME", IBMSME);
			modelAndView.addObject("customerList", uniqueCustList);
			modelAndView.addObject("OTSUKASME", OTSUKASME);

			
			
		return modelAndView;

	}
	
	@RequestMapping(value = "/submitAddCustomer", method = RequestMethod.POST)
	// pass customer and save it to DB
	public String submitCustomer(
			@ModelAttribute("command") Customer customer,HttpServletRequest request) {
		
		Date currentDate = Calendar.getInstance().getTime();
		customer.setLastUpdateDate(currentDate);
		
		CustomerStageAudit customerStageAudit = new CustomerStageAudit();
		System.out.println("----------------------customer being saved is:---------"+customer+ "********"
						
						+ " First name:"
						+ customer.getFirstName());
		
		    // browser and os details
		    String  browserDetails  =   request.getHeader("User-Agent");
	        String  userAgent       =   browserDetails;
	        String  user            =   userAgent.toLowerCase();

	        String os = "";
	        String browser = "";

	        System.out.println("User Agent for the request is===>"+browserDetails);
	        
	        //=================OS=======================
	         if (userAgent.toLowerCase().indexOf("windows") >= 0 )
	         {
	             os = "Windows";
	         } else if(userAgent.toLowerCase().indexOf("mac") >= 0)
	         {
	             os = "Mac";
	         } else if(userAgent.toLowerCase().indexOf("x11") >= 0)
	         {
	             os = "Unix";
	         } else if(userAgent.toLowerCase().indexOf("android") >= 0)
	         {
	             os = "Android";
	         } else if(userAgent.toLowerCase().indexOf("iphone") >= 0)
	         {
	             os = "IPhone";
	         }else{
	             os = "UnKnown, More-Info: "+userAgent;
	         }
	         //===============Browser===========================
	         
	        if (user.contains("msie"))
	        {
	            String substring=userAgent.substring(userAgent.indexOf("MSIE")).split(";")[0];
	            browser=substring.split(" ")[0].replace("MSIE", "IE")+"-"+substring.split(" ")[1];
	        } else if (user.contains("safari") && user.contains("version"))
	        {
	            browser=(userAgent.substring(userAgent.indexOf("Safari")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
	        } else if ( user.contains("opr") || user.contains("opera"))
	        {
	            if(user.contains("opera"))
	                browser=(userAgent.substring(userAgent.indexOf("Opera")).split(" ")[0]).split("/")[0]+"-"+(userAgent.substring(userAgent.indexOf("Version")).split(" ")[0]).split("/")[1];
	            else if(user.contains("opr"))
	                browser=((userAgent.substring(userAgent.indexOf("OPR")).split(" ")[0]).replace("/", "-")).replace("OPR", "Opera");
	        } else if (user.contains("chrome"))
	        {
	            browser=(userAgent.substring(userAgent.indexOf("Chrome")).split(" ")[0]).replace("/", "-");
	        } else if ((user.indexOf("mozilla/7.0") > -1) || (user.indexOf("netscape6") != -1)  || (user.indexOf("mozilla/4.7") != -1) || (user.indexOf("mozilla/4.78") != -1) || (user.indexOf("mozilla/4.08") != -1) || (user.indexOf("mozilla/3") != -1) )
	        {
	            //browser=(userAgent.substring(userAgent.indexOf("MSIE")).split(" ")[0]).replace("/", "-");
	            browser = "Netscape-?";

	        } else if (user.contains("firefox"))
	        {
	            browser=(userAgent.substring(userAgent.indexOf("Firefox")).split(" ")[0]).replace("/", "-");
	        } else if(user.contains("rv"))
	        {
	            browser="IE";
	        } else
	        {
	            browser = "UnKnown, More-Info: "+userAgent;
	        }
	        System.out.println("Operating System======>"+os);
	        System.out.println("Browser Name==========>"+browser);
	        
	       
	        
	      // IP details
	        String ipAddress = request.getHeader("X-FORWARDED-FOR");  
	        if (ipAddress == null) {  
	     	   ipAddress = request.getRemoteAddr();  
	        }
		
		
		 
		try {
			customerService.saveNewCustomer(customer); 
				
			customerStageAudit.setCustomer(customer);
			customerStageAudit.setNewStageStartDate(customer.getStartTime()); 
			customerStageAudit.setNewStageEndDate(customer.getEndTime());
			customerStageAudit.setLastUpdateDate(currentDate);
	    	customerStageAudit.setStage(customer.getStage()); 
	    	customerService.saveCustomerStageAudit(customerStageAudit); 
	    	
	    	CustomerAudit customerAudit = new CustomerAudit();	
	    	customerAudit.setCustomer(customer);
	    	customerAudit.setBrowser(browser);
	    	customerAudit.setOperatingSystem(os);
	    	customerAudit.setDay(currentDate);
	    	customerAudit.setIpAddress(ipAddress);
	    	customerService.saveCustomerAudit(customerAudit); 	
		
		} catch (Exception e) {
			e.printStackTrace();
		}

        //return "redirect:/customersel?data=1";
        return "redirect:/customersel";

	}

	@RequestMapping(value = "/addMeeting", method = RequestMethod.POST)
	// pass customerid as request parameters and save it to DB and return it to
	// dataviewCustomer
	public String addMeeting(@ModelAttribute("SpringWeb") Meeting meeting,
			//@RequestParam(value = "image", required = false) MultipartFile image,
			BindingResult result
			) throws IOException { 
		
		System.out.println("DataCustomerController : addMeeting : *********************");
		
		String saveDirectory = null;
		String uploadedLocation = null;
		Customer customer = null;
		//String oldPhoneNumber= null;
		String appPath = servletContext.getRealPath("");
	    System.out.println("appPath = " + appPath);
	    
	    List<MultipartFile> uploadFileList = meeting.getFiles();
	    List<String> fileTypeList = meeting.getFileTypes();
	    
	    System.out.println("uploadFileList --------------------------- " + uploadFileList);
	    
	    //-----load configuration.properties file-------
	    
	    
	    //Properties props1 = System.getProperties();
	    //appPath = props1.getProperty("jboss.server.data.dir");
	    
	    Properties configProperties = new Properties();
		String propertyFile = "configurations.properties";

		if(CustomerService.class.getClassLoader().getResourceAsStream(propertyFile) == null){
			System.out.println("configurations file not loaded");
		}
		
		try {
			configProperties.load(CustomerService.class.getClassLoader().getResourceAsStream(propertyFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		appPath = (String) configProperties.get("upload.save.dir");
		
		//--------*************************--------
		Date currentDate = Calendar.getInstance().getTime();
	    
		try {
			System.out.println("------------ addMeeting -----------**************"+ meeting.getCustomer().getCustID());
			customer = customerService.findByCustomerId(String.valueOf(meeting.getCustomer().getCustID()));
			meeting.setFlag(0);
			meeting.setLastUpdateDate(currentDate);
			customerService.saveMeeting(meeting);
			//saveDirectory = appPath+utilityService.getByLovName(ApplicationConstant.FILE_UPLOAD_LOCATION).getLovValue();
			saveDirectory = appPath + File.separator;
			System.out.println("Save Directory------------"+saveDirectory);
			
			
			int fileIndex = 0;
			for(MultipartFile currFile : uploadFileList) {
				FileDetails fileDetails = new FileDetails();
				String origFileName = currFile.getOriginalFilename(); 
				
				System.out.println("add meeting - Upload file --- " + origFileName + ",  " + currFile.getName());
				
				String fileName = origFileName;
				if(fileName.lastIndexOf(".") > -1) {
					String ext = fileName.substring(fileName.lastIndexOf("."));
					fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_"+ RandomStringUtils.randomAlphanumeric(8) + ext;
				}
				
				
				File newFile = new File(saveDirectory + fileName);
				
				
				System.out.println("Context Path of root>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
								+ saveDirectory + fileName);
				uploadedLocation = saveDirectory + fileName;
				if(fileName.trim().length() > 0) {
					FileUtils.writeByteArrayToFile(newFile, currFile.getBytes());
					//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
					//Calendar cal = Calendar.getInstance();
					
					
					if(fileTypeList != null && fileTypeList.size() > fileIndex ) {
						fileDetails.setFileType(fileTypeList.get(fileIndex));
					}
					
					
					fileDetails.setDay(currentDate);
					
					
					if(origFileName != null && (!origFileName.isEmpty()) && origFileName.length() > 20) {
						origFileName = origFileName.substring(0,20);
					}
					
					fileDetails.setFileName(origFileName);
					fileDetails.setURL(uploadedLocation); 
					fileDetails.setMeeting(meeting);
					customerService.saveFileDetails(fileDetails);
					fileIndex += 1;
				}
			}
			
						
			customer = customerService.findByCustomerId(String.valueOf(meeting
					.getCustomer().getCustID()));
			  
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	/*	ModelAndView modelAndView = new ModelAndView("dataviewCustomer",
				"command", customer);
		modelAndView.addObject("meetingListContainer", customer);

		return modelAndView;*/
		//List<FileDetails> fileDetailsList = customerService.findAllUploadedFiles();
		//ModelAndView modelAndView = new ModelAndView("customerdata", "command", customer);
		//modelAndView.addObject("meetingListContainer", customer);
		//modelAndView.addObject("encyptedphnumber", encryptedPhoneNumber);
		//modelAndView.addObject("custPhone", oldPhoneNumber);
		//modelAndView.addObject("activeTab",1);
		//modelAndView.addObject("fileListContainer", fileDetailsList);
		//return modelAndView;

		return "redirect:/cust/" + customer.getCustID() + "?data=1&mflag=1";

	}

	@RequestMapping(value = "/addMeetingView/{Id}", method = RequestMethod.GET)
	// pass customerid as request parameters and save it to DB and return it to
	// dataviewCustomer
	public ModelAndView addMeetingView(@PathVariable("Id") String id) {

		Customer customer = customerService.findByCustomerId(id);
		System.out.println("<--- addMeetingView -- >" + id);
		Meeting meeting = new Meeting();
		meeting.setCustomer(customer);
		// model.addAttribute("customerId",id);
		meeting.getCustomer().setCustID(Integer.parseInt(id));
		ModelAndView modelAndView = new ModelAndView("addmeeting", "command",
				meeting);

		return modelAndView;

	}

	
	// pass customerid and meeting details as request parameters and save it to
	// DB and return it to dataviewCustomer
	@RequestMapping(value = "/updateMeeting", method = RequestMethod.GET)
	public ModelAndView updateMeetings(HttpServletRequest request) {
		
		System.out.println("Inside updateMeeting*********");

		try {
			String meetingId = request.getParameter("meetingId");
			String custId = request.getParameter("custId");

			Meeting meeting = customerService.findByMeetingId(meetingId);
			
			System.out.println("Inside updateMeeting*********" + meeting);
			
			//List<Meeting> meetings = new LinkedList<Meeting>();
			//meetings.add(meeting);
			ModelAndView modelAndView = new ModelAndView("updatemeetings","command", meeting);
			Integer fileCount = 0;
			if(meeting != null && meeting.getFileList() != null && (!meeting.getFileList().isEmpty())) {
				fileCount = meeting.getFileList().size();
			}
			
			List<String> fileRemList = new ArrayList<String>();
			
			for(int i=0; i < (7-fileCount); i++) {
				fileRemList.add("file" + i);
			}
			
			System.out.println("fileCount: " + fileCount + ", fileRemList: " + fileRemList.size());

			modelAndView.addObject("custId", custId );
			modelAndView.addObject("meetingId", meetingId);
			modelAndView.addObject("fileCount", fileCount);
			modelAndView.addObject("fileRemList", fileRemList);
			modelAndView.addObject("meeting", meeting);
			//modelAndView.addObject("meetingList", meetings);
			
			System.out.println("Inside updateMeeting********* return meeting view: ");
			
			return modelAndView;

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ModelAndView modelAndView = new ModelAndView("error");
		return modelAndView;

	}

	@RequestMapping(value = "/savemeetingupdates", method = RequestMethod.POST)
	public String editCustomerMeetings(@ModelAttribute("SpringWeb") Meeting meeting, BindingResult result) {
		Integer custId = 2;
		try {
			System.out.println("--------------------------------------meeting id:" + meeting.getMeetingId());

			Meeting oldMeeting = customerService.findByMeetingId(String.valueOf(meeting.getMeetingId()));

			oldMeeting.setAgenda(meeting.getAgenda());
			oldMeeting.setContactName(meeting.getContactName());
			oldMeeting.setDay(meeting.getDay());
			
			customerService.update(oldMeeting);
			
			custId = oldMeeting.getCustomer().getCustID();
			
			
			List<MultipartFile> uploadFileList = meeting.getFiles();
			List<String> fileTypeList = meeting.getFileTypes();
			
			//********load configuration.properties file**************
		   
			//Properties props1 = System.getProperties();
		    //String appPath = props1.getProperty("jboss.server.data.dir");
		    
		    
		    Properties configProperties = new Properties();
			String propertyFile = "configurations.properties";

			if(CustomerService.class.getClassLoader().getResourceAsStream(propertyFile) == null){
				System.out.println("configurations file not loaded");
			}
			
			try {
				configProperties.load(CustomerService.class.getClassLoader().getResourceAsStream(propertyFile));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String appPath = (String) configProperties.get("upload.save.dir");
			String saveDirectory = appPath + File.separator;
			
			//--------*************************--------
		    
		    
		    int fileIndex = 0;
			for(MultipartFile currFile : uploadFileList) {
				
				FileDetails fileDetails = new FileDetails();
				String origFileName = currFile.getOriginalFilename();
				if(origFileName == null || origFileName.isEmpty()) {
					continue;
				}
				String fileName = origFileName;
				
				System.out.println("Inside savemeetingupdates------------- fileName: " + fileName + ",  " + currFile + 
						"currFile.getName()---" + currFile.getName());
				
				String fileType = " ";
				if(fileTypeList != null && fileTypeList.size() > fileIndex ) {
					fileType = fileTypeList.get(fileIndex);
				}
				
				if(fileName.lastIndexOf(".") > -1) {
					String ext = fileName.substring(fileName.lastIndexOf("."));
					fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_"+ 
							RandomStringUtils.randomAlphanumeric(8) + ext;
				} else {
					fileName = RandomStringUtils.randomAlphanumeric(8);
				}
				
				System.out.println("Inside savemeetingupdates------------- fileName: " + fileName + ",  " + currFile);
				
				System.out.println("Context Path of root>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
						+ saveDirectory + fileName);
				
				
				
				File newFile = new File(saveDirectory + fileName);
				
				String uploadedLocation = saveDirectory + fileName;
				FileUtils.writeByteArrayToFile(newFile, currFile.getBytes());
				Date currentDate = Calendar.getInstance().getTime();
				
				
				
				
				fileDetails.setDay(currentDate);
				
				if(origFileName != null && (!origFileName.isEmpty()) && origFileName.length() > 20) {
					origFileName = origFileName.substring(0,20);
				}
				
				fileDetails.setFileType(fileType);
				fileDetails.setFileName(currFile.getOriginalFilename());
				fileDetails.setURL(uploadedLocation); 
				fileDetails.setMeeting(oldMeeting);
				customerService.saveFileDetails(fileDetails);
				fileIndex += 1;
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}


		return "redirect:/cust/" + custId + "?data=1&mflag=1";

	}
	
	//For deleting meeting
	@RequestMapping(value = "/removeMeeting", method = RequestMethod.GET)
	// pass meting as request parameters and save it to DB and return it to
	// dataviewCustomer
	public String removeMeeting(HttpServletRequest request) {

		Meeting meeting = null;		
		String custId = "";
		try {
			String meetingId = request.getParameter("meetingId");
			System.out.println("inside remove meeting " + meetingId);
			meeting = customerService.findByMeetingId(meetingId);
			System.out.println("Meeting---------" + meeting);
			if(meeting != null) {
				
				custId = String.valueOf(meeting.getCustomer().getCustID());
				
				System.out.println("Meeting---------" + custId);
				
				customerService.deleteMeeting(meeting);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/cust/" + custId + "?data=1&mflag=1";

	}
	
	
	@RequestMapping(value = "/downloadFile/{Id}", method = RequestMethod.GET)
	// pass customerid as request parameters and save it to DB and return it to
	// dataviewCustomer
	public void downloadFile(@PathVariable("Id") String id, HttpServletRequest req, HttpServletResponse response) {

		String fileUrl = null;
		FileDetails fileDetails = customerService.findByFileId(id);
		System.out.println("File Ddetails Id:::::::"+fileDetails.getFileName()+", URL------"+fileDetails.getURL()); 
		 // get absolute path of the application
        String appPath = servletContext.getRealPath("");
        System.out.println("appPath = " + appPath);
        
		try {
			//saveDirectory = utilityService.getByLovName(ApplicationConstant.FILE_UPLOAD_LOCATION).getLovValue();
			// construct the complete absolute path of the file
	        String fullPath = appPath + filePath;  
	        fileUrl = fileDetails.getURL();
	         
	        File downloadFile = new File(fileUrl);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	         
			
			System.out.println("---------------File upload directory is:"+ fileUrl);
			// get MIME type of the file
	        String mimeType = servletContext.getMimeType(fullPath);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        System.out.println("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"", fileDetails.getFileName());
	        response.setHeader(headerKey, headerValue);
	 
	 
			 // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	 
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Customer customer = customerService.findByCustomerId(id);
		System.out.println("<--- addMeetingView -- >" + id);
		Meeting meeting = new Meeting();
		meeting.setCustomer(customer);
		// model.addAttribute("customerId",id);
		meeting.getCustomer().setCustID(Integer.parseInt(id));
		ModelAndView modelAndView = new ModelAndView("addmeeting", "command",
				meeting);

		return modelAndView;*/

	}
	
	
	@RequestMapping(value = "/beehivecrm", method = RequestMethod.GET)
	// register user in session
	public String registerUser(HttpServletRequest request) {
		//ModelAndView modelAndView = new ModelAndView();
		
		return "redirect:/Register.jsp";

	}
	
		@RequestMapping(value = "/beehivecrm", method = RequestMethod.POST)
		// register user in session
		public String registerUser(HttpServletRequest request, HttpServletResponse response) {
			HttpSession session = request.getSession(true);
			String user = request.getParameter("user");
	        System.out.println("LoginServlet: doPost(): user= "+user);
            session.setAttribute("user", user);
			String redirect = new String();
			
			if (validateUser(request)) {
				redirect = "redirect:/customersel";
			} else {
				redirect =  "redirect:/Register.jsp";
			}
				
			//return "redirect:/cust/" + custId + "?data=1&mflag=1";
			return redirect;

		}
		
		@RequestMapping(value = "/authuser")
		public Boolean validateUser(HttpServletRequest request) {
			final String userID = "admin";
	    	HttpSession session = request.getSession(false);
	    	String user = (String)session.getAttribute("user");
	    	System.out.println("DataCustomerController: validateUser(): user= "+user);
	    	
	    	if (user != null && userID.equals(user)) {
	    		return true;
	    	}
	    	return false;
	    	
	    }
	

}