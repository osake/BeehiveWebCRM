package org.otsuka.beehive.email.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.otsuka.beehive.email.dao.EmailFormDao;
import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.EmailForm;
import org.springframework.stereotype.Repository;

@Repository("emailformDao")
public class EmailFormDaoImpl extends AbstractDao implements EmailFormDao {

	public void saveEmailForm(EmailForm eform) {
		// TODO Auto-generated method stub

	}

	public void deleteEmailFormByCode(String emailformCode) {
		// TODO Auto-generated method stub

	}

	public EmailForm findByEmailformId(String emailId) {
		Criteria criteria = getSession().createCriteria(EmailForm.class);
        criteria.add(Restrictions.eq("emailID",emailId));
        return (EmailForm) criteria.uniqueResult();
	}

}
