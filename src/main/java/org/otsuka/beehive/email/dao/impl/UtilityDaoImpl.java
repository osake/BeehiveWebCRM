package org.otsuka.beehive.email.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.otsuka.beehive.email.dao.UtilityDao;
import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.Lov;
import org.otsuka.beehive.email.model.LovType;
import org.springframework.stereotype.Repository;

@Repository("utilityDao")
public class UtilityDaoImpl extends AbstractDao  implements UtilityDao{

	
	@SuppressWarnings("unchecked")
	public List<LovType> findAllLovType() {
		// TODO Auto-generated method stub 
		 Criteria criteria = getSession().createCriteria(LovType.class);
	        return (List<LovType>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Lov> findAllLov() {
		// TODO Auto-generated method stub
		 Criteria criteria = getSession().createCriteria(Lov.class);
	        return (List<Lov>) criteria.list();
	}
	
	public LovType findByLovTypeId(String lovTypeId) {
		// TODO Auto-generated method stub
		try{
	    	Criteria criteria = getSession().createCriteria(LovType.class);
	        criteria.add(Restrictions.eq("ID",Integer.parseInt(lovTypeId)));
	        return (LovType) criteria.uniqueResult();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		System.out.println("Exception:" + e.getLocalizedMessage());
	    		return null;
	    	}
	}

	public LovType findByLovTypeName(String lovTypeName) {
		// TODO Auto-generated method stub 
		try{
		Criteria criteria = getSession().createCriteria(LovType.class);
        criteria.add(Restrictions.eq("typeName",lovTypeName));
        return (LovType) criteria.uniqueResult();
    	}catch(Exception e){
    		e.printStackTrace();
    		System.out.println("Exception:" + e.getLocalizedMessage());
    		return null;
    	}
	}

	public Lov findByLovName(String lovName) {
		// TODO Auto-generated method stub
		try{
			Criteria criteria = getSession().createCriteria(Lov.class);
	        criteria.add(Restrictions.eq("lovName",lovName));
	        return (Lov) criteria.uniqueResult();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		System.out.println("Exception:" + e.getLocalizedMessage());
	    		return null;
	    	}
	}

	public Lov findByLovId(String lovId) {
		try{
	    	Criteria criteria = getSession().createCriteria(Lov.class);
	        criteria.add(Restrictions.eq("ID",Integer.parseInt(lovId)));
	        return (Lov) criteria.uniqueResult();
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		System.out.println("Exception:" + e.getLocalizedMessage());
	    		return null;
	    	}
	}

	public Lov findByLovTypeAndName(String lovTypeName, String lovName) {
		// TODO Auto-generated method stub
		return null;
	}
	public List<Lov> findListByLovTypeAndName(String lovTypeName, String lovName) {
		Criteria cr = getSession().createCriteria(Lov.class);
		cr.add(Restrictions.eq("lovName", lovName));
		List results = cr.list();
		return results;
	}
	

	

}
