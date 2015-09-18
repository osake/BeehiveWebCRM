package org.otsuka.beehive.email.service;

import java.util.List;

import org.otsuka.beehive.email.model.Lov; 
import org.otsuka.beehive.email.model.LovType;

public interface UtilityService {   
 
	//public List<Lov> getLovValuesForType(String type);

	//public Lov getLovById(String lovId);

	//public Lov getLovByName(String lovTypeName, String lovName);
	
	 
	public   List<LovType> getAllLovType();

	public   List<Lov> getAllLov();

	public   LovType getByLovTypeId(String lovTypeId);

	public   LovType getByLovTypeName(String lovTypeName);

	public   Lov getByLovName(String lovName);

	public   Lov getByLovId(String lovId);

	public   Lov getByLovTypeAndName(String lovTypeName, String lovName);
	
	public   List<Lov> getListByLovTypeAndName(String lovTypeName, String lovName);
}
