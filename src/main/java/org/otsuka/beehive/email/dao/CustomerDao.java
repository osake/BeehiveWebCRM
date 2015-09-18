package org.otsuka.beehive.email.dao;

import java.util.List;

import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.CustomerAudit;
import org.otsuka.beehive.email.model.CustomerStageAudit;
import org.otsuka.beehive.email.model.FileDetails;
import org.otsuka.beehive.email.model.Meeting;


public interface CustomerDao {
 
	public abstract void saveCustomer(Customer customer);
	//For new Customer 
	public abstract void saveNewCustomer(Customer customer);
	
	public abstract void saveCustomerAudit(CustomerAudit customerAudit);
 
	public abstract List<Customer> findAllCustomers(); 
	 

	public abstract void deleteCustomerByCode(String custCode);

	public abstract Customer findByCustomerCode(String custCode);

	public abstract Customer findByCustomerName(String custName);

	public abstract void updateCustomer(Customer customer);
	
	public abstract void updateCustomerAudit(CustomerAudit customerAudit);
	
	public abstract Customer findByCustomerId(String custId);
	public abstract CustomerAudit findCustomerAudit(Customer customer);
	
	public abstract void saveMeeting(Meeting meeting);
	
	
	public abstract void deleteMeeting(Meeting meeting);
	
	public abstract Meeting findByMeetingId(String meetingId);
	public abstract void updateMeeting(Meeting meeting);
	
	public abstract void deleteFileDetails(FileDetails fileDetails);
	
	public abstract FileDetails findByFileId(String fileId);

	public abstract void saveFileDetails(FileDetails fileDetails);
	
	public abstract List<FileDetails> findAllUploadedFiles();
	public List<Meeting> findByMeetings(Customer customer);
	
	//This is for Customer Audit Stage Detail
	
	public abstract void saveCustomerStageAudit(CustomerStageAudit customerStageAudit);
	
	public abstract void deleteCustomerStageAudit(CustomerStageAudit customerStageAudit);
	
	public abstract void updateCustomerStageAudit(CustomerStageAudit customerStageAudit);
	
	public List<CustomerStageAudit> findCustomerStageAudit(Customer customer);
	

}