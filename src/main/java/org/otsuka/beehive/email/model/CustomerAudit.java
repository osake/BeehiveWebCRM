package org.otsuka.beehive.email.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CUSTOMER_AUDIT", catalog = "test")
public class CustomerAudit {
	
	@NotNull(message="Enter upload date in correct format")
	 @DateTimeFormat(pattern = "MM/dd/yyyy")
	 private Date  day;
	private Integer custAuditId;
	
	private String browser;
	private String ipAddress;
	private String clientInfo;
	private Customer customer;
	private String operatingSystem;
	
	
	
	@Column(name="OPERATING_SYSTEM")
	public String getOperatingSystem() {
		return operatingSystem;
	}


	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUDIT_ID", unique = true, nullable = false)
	public Integer getCustAuditId() {
		return custAuditId;
	}
	
	
	public void setCustAuditId(Integer custAuditId) {
		this.custAuditId = custAuditId;
	}
	
	@Column(name = "UPDATE_DATE")
	public Date getDay() {
		  return day;
		 }

		 public void setDay(Date date) {
		  this.day = date;
		 }
	
	@Column(name = "BROWSER")
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	
	@Column(name = "IP_ADDRESS")
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	@Column(name = "CLIENT_INFO")
	public String getClientInfo() {
		return clientInfo;
	}
	public void setClientInfo(String clientInfo) {
		this.clientInfo = clientInfo;
	}
	
	@OneToOne
	@JoinColumn(name="CUST_ID")
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	

}
