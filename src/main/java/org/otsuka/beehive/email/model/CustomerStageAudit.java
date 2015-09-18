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
@Table(name = "CUSTOMER_STAGE_AUDIT", catalog = "test")
public class CustomerStageAudit implements java.io.Serializable {
	
	
	private static final long serialVersionUID = -881162248331539762L;;
	
	
	
	private Integer auditId;
	private Date oldStageStartDate;
	private Date oldStageEndDate;
	private Date newStageStartDate;
	private Date newStageEndDate;
	private String stage;
	private Customer customer;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date lastUpdateDate; 
	
	private String lastUpdatedUserId;
	
	public CustomerStageAudit() {
		// TODO Auto-generated constructor stub
	}
	
	
	public CustomerStageAudit(String stage) {
		super();
		this.stage = stage;
	}
	 
	
	@Column(name = "OLD_STAGE_END_DATE")
	public Date getOldStageEndDate() {
		return oldStageEndDate;
	}
	public void setOldStageEndDate(Date oldStageEndDate) {
		this.oldStageEndDate = oldStageEndDate;
	}
	
	@Column(name = "NEW_STAGE_START_DATE")
	public Date getNewStageStartDate() {
		return newStageStartDate;
	}
	public void setNewStageStartDate(Date newStageStartDate) {
		this.newStageStartDate = newStageStartDate;
	}
	
	@Column(name = "NEW_STAGE_END_DATE")
	public Date getNewStageEndDate() {
		return newStageEndDate;
	}
	public void setNewStageEndDate(Date newStageEndDate) {
		this.newStageEndDate = newStageEndDate;
	}
	
	@Column(name = "STAGE")
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUDIT_ID", unique = true, nullable = false)
	public Integer getAuditId() {
		return auditId;
	}
	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}
	
	@Column(name = "OLD_STAGE_START_DATE")
	public Date getOldStageStartDate() {
		return oldStageStartDate;
	}
	public void setOldStageStartDate(Date oldStageStartDate) {
		this.oldStageStartDate = oldStageStartDate;
	}
	
	@ManyToOne
	@JoinColumn(name="CUST_ID")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Column(name = "LAST_UPDATE_DATE")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date date) {
		this.lastUpdateDate = date;
	}
	
	@Column(name = "LAST_UPDATED_USER_ID")
	public String getLastUpdatedUserId() {
		return lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}
	

}
