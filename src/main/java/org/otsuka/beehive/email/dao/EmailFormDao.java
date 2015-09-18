package org.otsuka.beehive.email.dao;

import java.util.List;

import org.otsuka.beehive.email.model.EmailForm;

public interface EmailFormDao {

	public abstract void saveEmailForm(EmailForm eform);

	public abstract void deleteEmailFormByCode(String emailformCode);

	public EmailForm findByEmailformId(String emailId);
}
