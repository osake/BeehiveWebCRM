package org.otsuka.beehive.email.service;

import org.otsuka.beehive.email.model.EmailForm;

public interface EmailFormService {

	EmailForm findByEmailId(String id);
	
}
