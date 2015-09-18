package org.otsuka.beehive.email.service;

import java.util.List;

import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.CustomerAudit;
import org.otsuka.beehive.email.model.CustomerStageAudit;
import org.otsuka.beehive.email.model.FileDetails;
import org.otsuka.beehive.email.model.Meeting;


public interface CustomerService {
	
	void save(Customer customer);
	void saveCustomerAudit(CustomerAudit customerAudit);
	
	void update(Customer customer);
	void updateCustomerAudit(CustomerAudit customerAudit);
	
	void delete(Customer customer);
	
	Customer findByCustomerCode(String customerCode);
	
	Customer findByCustomerId(String customerCode);
	
	CustomerAudit findCustomerAudit(Customer customer);
	
	List<Customer> findAllCustomers();

	void saveMeeting(Meeting meeting);

	Meeting findByMeetingId(String meetingId);
	
	void deleteMeeting(Meeting meeting);
	
	void update(Meeting customer);
	
	void saveFileDetails(FileDetails fileDetails);
	
	FileDetails findByFileId(String fileId);
	
	List<FileDetails> findAllUploadedFiles();
	
	void deleteFileDetails(FileDetails fileDetails);
	
	void saveNewCustomer(Customer customer);
	
	public List<Meeting> findByMeetings(Customer customer);
	
    void saveCustomerStageAudit(CustomerStageAudit customerStageAudit);
	
	void updateCustomerStageAudit(CustomerStageAudit customerStageAudit);
	
	void deleteCustomerStageAudit(CustomerStageAudit customerStageAudit);
	
	public List<CustomerStageAudit> findCustomerStageAudit(Customer customer);
	
	public Customer findByCustomerName(String custName);

}
