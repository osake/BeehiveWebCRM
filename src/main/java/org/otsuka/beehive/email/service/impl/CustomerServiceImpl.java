package org.otsuka.beehive.email.service.impl;

import java.util.List;

import org.otsuka.beehive.email.dao.CustomerDao;
import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.CustomerAudit;
import org.otsuka.beehive.email.model.CustomerStageAudit;
import org.otsuka.beehive.email.model.FileDetails;
import org.otsuka.beehive.email.model.Meeting;
import org.otsuka.beehive.email.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("customerService")
@Transactional

public class CustomerServiceImpl implements CustomerService{
	
	@Autowired 
	CustomerDao customerDao;
	
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void saveCustomerAudit(CustomerAudit customerAudit){
		customerDao.saveCustomerAudit(customerAudit);
	}
	
	public void save(Customer customer){
		customerDao.saveCustomer(customer);
	}
	
		
	public void update(Customer customer){
		customerDao.updateCustomer(customer);
	}
	
	public void updateCustomerAudit(CustomerAudit customerAudit){
		customerDao.updateCustomerAudit(customerAudit);
	}
	
	public void delete(Customer customer){
		customerDao.deleteCustomerByCode(customer.getCustCode());
	}
	
	public Customer findByCustomerCode(String customerCode){
		return customerDao.findByCustomerCode(customerCode);
	}
	
	public Customer findByCustomerName(String custName) {
		return customerDao.findByCustomerName(custName);
	}
	
	
	
	public Customer findByCustomerId(String customerId){
		return customerDao.findByCustomerId(customerId);
	}
	
	public CustomerAudit findCustomerAudit(Customer customer){
		return customerDao.findCustomerAudit(customer);
	}


	public List<Customer> findAllCustomers() {
		
		return customerDao.findAllCustomers();
	}
	

	public void saveMeeting(Meeting meeting) {
	customerDao.saveMeeting(meeting);
	}

	public Meeting findByMeetingId(String meetingId) {
		// TODO Auto-generated method stub
		return customerDao.findByMeetingId(meetingId);
	}


	public void deleteMeeting(Meeting meeting) {
		customerDao.deleteMeeting(meeting); 
	}


	public void update(Meeting meeting) {
		customerDao.updateMeeting(meeting);
		
	}
	
	public void saveFileDetails(FileDetails fileDetails) {
		customerDao.saveFileDetails(fileDetails);
			
		}
	
	public FileDetails findByFileId(String fileId) {
		// TODO Auto-generated method stub
		return customerDao.findByFileId(fileId);
	}
	
	public List<FileDetails> findAllUploadedFiles() {		
		return customerDao.findAllUploadedFiles();
	}

	public void deleteFileDetails(FileDetails fileDetails) {
	customerDao.deleteFileDetails(fileDetails); 
	}

	public void saveNewCustomer(Customer customer){
	customerDao.saveNewCustomer(customer);
	}

	public List<Meeting> findByMeetings(Customer customer) {
	// TODO Auto-generated method stub
	return customerDao.findByMeetings(customer);
	}

	public void saveCustomerStageAudit(CustomerStageAudit customerStageAudit) {
		// TODO Auto-generated method stub
		customerDao.saveCustomerStageAudit(customerStageAudit);
	}

	public void updateCustomerStageAudit(CustomerStageAudit customerStageAudit) {
		// TODO Auto-generated method stub
		customerDao.updateCustomerStageAudit(customerStageAudit);
	}

	public void deleteCustomerStageAudit(CustomerStageAudit customerStageAudit) {
		// TODO Auto-generated method stub
		customerDao.deleteCustomerStageAudit(customerStageAudit); 
	}
	
	public List<CustomerStageAudit> findCustomerStageAudit(Customer customer) {
		return customerDao.findCustomerStageAudit(customer);
	}



}
