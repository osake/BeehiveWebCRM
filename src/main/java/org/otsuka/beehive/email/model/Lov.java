package org.otsuka.beehive.email.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="LOV", catalog="test")
public class Lov {

	
	private Integer lovId;
	
	/**
	 * LovType associated with this LOV
	 */

	private LovType lovType;
	
	private String lovName;
	
	private String lovValue;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date  dateAdded;

	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date  dateUpdated;

	/**
	 * Zero argument constructor
	 */
	public Lov() {

	}
	
	
	public Lov( String lovName, String lovValue, Date dateAdded,
			Date dateUpdated) {
		super();
		this.lovName = lovName;
		this.lovValue = lovValue;
		this.dateAdded = dateAdded;
		this.dateUpdated = dateUpdated;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOV_ID", unique = true, nullable = false)
	public Integer getLovId() {
		return lovId;
	}

	public void setLovId(Integer lovId) {
		this.lovId = lovId;
	}

	@ManyToOne
	@JoinColumn(name="TYPE_ID")
	public LovType getLovType() {
		return lovType;
	}

	public void setLovType(LovType lovType) {
		this.lovType = lovType;
	}

	@Column(name = "LOV_NAME")
	public String getLovName() {
		return lovName;
	}

	public void setLovName(String lovName) {
		this.lovName = lovName;
	}

	@Column(name = "LOV_VALUE")
	public String getLovValue() {
		return lovValue;
	}

	public void setLovValue(String lovValue) {
		this.lovValue = lovValue;
	}
	
	@Column(name = "DATE_ADDED")
	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	@Column(name = " DATE_UPDATED")
	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	
	
	
	
	
	
}
