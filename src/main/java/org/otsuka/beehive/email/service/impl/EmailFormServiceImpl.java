package org.otsuka.beehive.email.service.impl;


import org.otsuka.beehive.email.dao.EmailFormDao;
import org.otsuka.beehive.email.model.EmailForm;
import org.otsuka.beehive.email.service.EmailFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("emailService")
public class EmailFormServiceImpl implements EmailFormService {

	@Autowired
	EmailFormDao emailDao;
	
	public void setEmailDao(EmailFormDao emailDao) {
		this.emailDao = emailDao;
	}
	
	
	public EmailForm findByEmailId(String id) {
		System.out.println("inside email form dao's service method");
		return emailDao.findByEmailformId(id);
	}

}
