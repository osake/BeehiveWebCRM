package org.otsuka.beehive.email.dao.impl;

import org.otsuka.beehive.email.dao.AuditCustomerDao;
import org.otsuka.beehive.email.model.AuditCustomer;
import org.springframework.stereotype.Repository;

@Repository("auditCustomerDao")
public class AuditCustomerDaoImpl  extends AbstractDao implements AuditCustomerDao {
	
	
	public void saveAuditCustomer(AuditCustomer customer) {
		persist(customer);
		
	}

}
