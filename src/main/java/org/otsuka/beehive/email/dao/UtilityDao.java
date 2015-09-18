package org.otsuka.beehive.email.dao;

import java.util.List;

import org.otsuka.beehive.email.model.Customer;
import org.otsuka.beehive.email.model.Lov;
import org.otsuka.beehive.email.model.LovType;
 
public interface UtilityDao {

	public abstract List<LovType> findAllLovType();

	public abstract List<Lov> findAllLov();

	public abstract LovType findByLovTypeId(String lovTypeId); 

	public abstract LovType findByLovTypeName(String lovTypeName);

	public abstract Lov findByLovName(String lovName);

	public abstract Lov findByLovId(String lovId);

	public abstract Lov findByLovTypeAndName(String lovTypeName, String lovName);
	
	public abstract List<Lov> findListByLovTypeAndName(String lovTypeName, String lovName);

}
