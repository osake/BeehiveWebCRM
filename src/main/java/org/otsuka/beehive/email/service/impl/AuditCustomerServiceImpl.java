package org.otsuka.beehive.email.service.impl;

import org.otsuka.beehive.email.dao.AuditCustomerDao;
import org.otsuka.beehive.email.model.AuditCustomer;
import org.otsuka.beehive.email.service.AuditCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("auditCustomerService")
@Transactional

public class AuditCustomerServiceImpl implements AuditCustomerService{
	
	@Autowired
	AuditCustomerDao customerDao;
	
	public void setAuditCustomerDao(AuditCustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void save(AuditCustomer customer){ 
		System.out.println("<-- AuditCustomer save called -- >");
		customerDao.saveAuditCustomer(customer);
	}
	
}
