package org.otsuka.beehive.common;

import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
    	ApplicationContext appContext = 
    		new ClassPathXmlApplicationContext("../WEB_INF/spring-web-servlet.xml");
	
    	CustomerService customerBo = (CustomerService)appContext.getBean("customerBo");
    	
    	/** insert **/
    	Customer customer = new Customer();
    	customer.setCustCode("7668");
    	customer.setCustName("HAIO");
    	//customerBo.save(customer);
    	
    	/** select **/
    	Customer customer2 = customerBo.findByCustomerCode("7668");
    	System.out.println(customer2);
    	
    	/** update **/
    	customer2.setCustName("HAIO-1");
    	//customerBo.update(customer2);
    	
    	/** delete **/
    	//customerBo.delete(customer2);
    	
    	System.out.println("Done");
    }
}
