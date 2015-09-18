package org.otsuka.beehive.email.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LOV_TYPE", catalog = "test")
public class LovType { 

	private Integer typeId;

	private String typeName;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateAdded;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date dateUpdated;

	/**
	 * Zero argument constructor
	 */
	public LovType() {

	}

	/**
	 * @param agenda
	 * @param contactName
	 * @param date
	 */
	public LovType(String typeName, Date dateAdded, Date dateUpdated) {
		super();
		this.typeName = typeName;
		this.dateAdded = dateAdded;
		this.dateUpdated = dateUpdated;
	}

	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TYPE_ID", unique = true, nullable = false)
	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "TYPE_NAME")
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "DATE_ADDED")
	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	@Column(name = "DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

}
