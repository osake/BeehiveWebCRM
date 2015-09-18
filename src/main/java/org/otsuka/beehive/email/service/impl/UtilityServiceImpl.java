package org.otsuka.beehive.email.service.impl;

import java.util.List;

import org.otsuka.beehive.email.dao.UtilityDao;
import org.otsuka.beehive.email.model.Lov; 
import org.otsuka.beehive.email.model.LovType;
import org.otsuka.beehive.email.service.UtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("utilityService")
@Transactional
public class UtilityServiceImpl implements UtilityService{

	@Autowired
	UtilityDao utilityDao;

	public List<LovType> getAllLovType() {
		// TODO Auto-generated method stub
		return utilityDao.findAllLovType();
	}

	public List<Lov> getAllLov() {
		// TODO Auto-generated method stub
		return utilityDao.findAllLov();
	}

	public LovType getByLovTypeId(String lovTypeId) {
		// TODO Auto-generated method stub
		return utilityDao.findByLovTypeId(lovTypeId);
	}

	public LovType getByLovTypeName(String lovTypeName) {
		// TODO Auto-generated method stub
		return utilityDao.findByLovTypeName(lovTypeName);
	}

	public Lov getByLovName(String lovName) {
		// TODO Auto-generated method stub
		return utilityDao.findByLovName(lovName); 
	}

	public Lov getByLovId(String lovId) {
		// TODO Auto-generated method stub
		return utilityDao.findByLovId(lovId); 
	}

	public Lov getByLovTypeAndName(String lovTypeName, String lovName) {
		// TODO Auto-generated method stub
		return utilityDao.findByLovTypeAndName(lovTypeName, lovName); 
	}
	public List<Lov> getListByLovTypeAndName(String lovTypeName, String lovName) {
		// TODO Auto-generated method stub
		return utilityDao.findListByLovTypeAndName(lovTypeName, lovName); 
	}
	 
 

	
}
