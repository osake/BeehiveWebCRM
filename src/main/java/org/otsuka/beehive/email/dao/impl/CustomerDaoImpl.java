package org.otsuka.beehive.email.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.otsuka.beehive.email.dao.CustomerDao;
import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.CustomerAudit;
import org.otsuka.beehive.email.model.CustomerStageAudit;
import org.otsuka.beehive.email.model.FileDetails;
import org.otsuka.beehive.email.model.Meeting;
import org.springframework.stereotype.Repository;

@Repository("customerDao") 
public class CustomerDaoImpl  extends AbstractDao implements CustomerDao {
	
	/* (non-Javadoc) 
	 * @see org.otsuka.beehive.email.dao.impl.CustomerDao#saveCustomer(org.otsuka.beehive.email.model.Customer)
	 */
	public void saveCustomer(Customer customer) {
		getSession().merge(customer);
       // persist(customer);
    }
	
	/* (non-Javadoc) 
	 * @see org.otsuka.beehive.email.dao.impl.CustomerDao#saveCustomer(org.otsuka.beehive.email.model.Customer)
	 */
	public void saveCustomerAudit(CustomerAudit customerAudit) {
		//getSession().merge(customer);
		 persist(customerAudit);
	}
	
	
	
	public void saveNewCustomer(Customer customer) {
		//getSession().merge(customer);
		 persist(customer);
	}
 
    /* (non-Javadoc)
	 * @see org.otsuka.beehive.email.dao.impl.CustomerDao#findAllCustomers()
	 */
    @SuppressWarnings("unchecked")
    public List<Customer> findAllCustomers() {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.addOrder(Order.asc("custName"));
        return (List<Customer>) criteria.list();
    }
 
 
 
    /* (non-Javadoc)
	 * @see org.otsuka.beehive.email.dao.impl.CustomerDao#deleteCustomerById(java.lang.String)
	 */
    public void deleteCustomerByCode(String custCode) {
        Query query = getSession().createSQLQuery("delete from Customer where custCode = :custCode");
        query.setString("custCode", custCode);
        query.executeUpdate();
    }
 
     
    /* (non-Javadoc)
	 * @see org.otsuka.beehive.email.dao.impl.CustomerDao#findByCustCode(java.lang.String)
	 */
    public Customer findByCustomerCode(String custCode){
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("custCode",custCode));
        return (Customer) criteria.uniqueResult();
    }
     
    public Customer findByCustomerName(String custName) {
        Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("custName",custName));
        return (Customer) criteria.uniqueResult();
    }
     
    /* (non-Javadoc)
	 * @see org.otsuka.beehive.email.dao.impl.CustomerDao#updateCustomer(org.otsuka.beehive.email.model.Customer)
	 */
    public void updateCustomer(Customer customer){
        getSession().saveOrUpdate(customer);
    }
    
    public void updateCustomerAudit(CustomerAudit customerAudit){
        getSession().update(customerAudit);
    }
    
    public Customer findByCustomerId(String custID) {
    	try{
    	Criteria criteria = getSession().createCriteria(Customer.class);
        criteria.add(Restrictions.eq("custID",Integer.parseInt(custID)));
        
        return (Customer) criteria.uniqueResult();
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("Exception:" + e.getLocalizedMessage());
    		return null;
    	}
    }
    
    
    public CustomerAudit findCustomerAudit(Customer customer) {
    	try{
    	Criteria criteria = getSession().createCriteria(CustomerAudit.class);
        criteria.add(Restrictions.eq("customer",customer));
        
        return (CustomerAudit) criteria.uniqueResult();
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("Exception:" + e.getLocalizedMessage());
    		return null;
    	}
    }

	public void saveMeeting(Meeting meeting) {
		 persist(meeting);
	}

	public Meeting findByMeetingId(String meetingId) {
		// TODO Auto-generated method stub
		try{
	    	Criteria criteria = getSession().createCriteria(Meeting.class);
	        criteria.add(Restrictions.eq("meetingId",Integer.parseInt(meetingId)));
	        criteria.add(Restrictions.eq("flag",0));
	        return (Meeting) criteria.uniqueResult();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		System.out.println("Exception:" + e.getLocalizedMessage());
	    		return null;
	    	}
	}
	
	public List<Meeting> findByMeetings(Customer customer) {
		
		try{
	    	Criteria criteria = getSession().createCriteria(Meeting.class);
	    	criteria.add(Restrictions.eq("customer",customer));
	        criteria.add(Restrictions.eq("flag",0));
	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        return   criteria.list();
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    		System.out.println("Exception:" + e.getLocalizedMessage());
	    		return null;
	    	}
	}
	
	public List<CustomerStageAudit> findCustomerStageAudit(Customer customer) {
		
		try{
	    	Criteria criteria = getSession().createCriteria(CustomerStageAudit.class);
	    	criteria.add(Restrictions.eq("customer",customer));
	        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
	        return criteria.list();
	    	} catch(Exception e) {
	    		e.printStackTrace();
	    		System.out.println("Exception:" + e.getLocalizedMessage());
	    		return null;
	    	}
		
	}

	public void deleteMeeting(Meeting meeting) {
		//delete(meeting);
		meeting.setFlag(1);
		getSession().update(meeting);
		
	}
	
	public void updateMeeting(Meeting meeting){
        getSession().update(meeting);
    }
	
	public FileDetails findByFileId(String fileId) {
		// TODO Auto-generated method stub
		try{
	    	Criteria criteria = getSession().createCriteria(FileDetails.class);
	        criteria.add(Restrictions.eq("fileId",Integer.parseInt(fileId)));
	        return (FileDetails) criteria.uniqueResult();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		System.out.println("Exception:" + e.getLocalizedMessage());
	    		return null;
	    	}
	}
	
	public void saveFileDetails(FileDetails fileDetails) {
		 persist(fileDetails);
		
	}
	
	public void deleteFileDetails(FileDetails fileDetails) {
		// TODO Auto-generated method stub\
		delete(fileDetails); 
		
	}
	
	 /* (non-Javadoc)
		 * @see org.otsuka.beehive.email.dao.impl.CustomerDao#findAllCustomers()
		 */
	    @SuppressWarnings("unchecked")
	    public List<FileDetails> findAllUploadedFiles() {
	        Criteria criteria = getSession().createCriteria(FileDetails.class);
	        return (List<FileDetails>) criteria.list();
	    }
	 
	    //This is for Customer Audit Stage Detail
	    public void saveCustomerStageAudit(CustomerStageAudit customerStageAudit) {
			 persist(customerStageAudit);
		}
	    
	    public void deleteCustomerStageAudit(CustomerStageAudit customerStageAudit) {
			// TODO Auto-generated method stub\
			delete(customerStageAudit); 
			
		}
	    
	    public void updateCustomerStageAudit(CustomerStageAudit customerStageAudit){
	        getSession().update(customerStageAudit);
	    }
	 

}
