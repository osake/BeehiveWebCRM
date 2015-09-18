package org.otsuka.beehive.email.model;



import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "AUDIT_CRM_CUSTOMER", catalog = "test")
public class AuditCustomer implements java.io.Serializable {

	/**
	 * Generate Sereial Version UID
	 */
	private static final long serialVersionUID = -881163358331539762L;
	
	private Integer auditId;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "AUDIT_ID", unique = true, nullable = false)
	public Integer getAuditId() {
		return auditId;
	}

	public void setAuditId(Integer auditId) {
		this.auditId = auditId;
	}



	private Integer custID;
	//private String custCode;
	private String custName;
	private String custEmail;
	//private String custAttachmentLoc;
	
	private String firstName;
	private String lastName;
	private String title;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String stage;


	public AuditCustomer() {
	}

	public AuditCustomer(Integer custID, String custCode, String custName,
			String custEmail, String custAttachmentLoc) {
		super();
		this.custID = custID;
		//this.custCode = custCode;
		this.custName = custName;
		this.custEmail = custEmail;
		//this.custAttachmentLoc = custAttachmentLoc;
	}

	// CUST_ID,CUST_CODE,
	
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CUST_ID",  nullable = false)
	public Integer getCustID() {
		return custID;
	}

	public void setCustID(Integer custID) {
		this.custID = custID;
	}

/*	@Column(name = "CUST_CODE")
	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}*/

	// CUST_NAME, CUST_EMAIL, CUST_ATTACHMENT_LOC
	@Column(name = "CUST_NAME")
	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	@Column(name = "PRIMARY_EMAIL")
	public String getCustEmail() {
		return custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	// CUST_ATTACHMENT_LOC

/*	@Column(name = "CUST_ATTACHMENT_LOC")
	public String getCustAttachmentLoc() {
		return custAttachmentLoc;
	}

	public void setCustAttachmentLoc(String custAttachmentLoc) {
		this.custAttachmentLoc = custAttachmentLoc;
	}*/
	
	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ADDRESS_1")
	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	@Column(name = "ADDRESS_2")
	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	@Column(name = "CITY")
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "STATE")
	public String getState() {
		return state;
	}

	
	public void setState(String state) {
		this.state = state;
	}

	@Column(name = "ZIP_CODE")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Column(name = "COUNTRY")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Column(name = "STAGE")
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	


	@Override
	public String toString() {
		return "Customer [custID=" + custID 
				+ ", custName=" + custName + ", custEmail=" + custEmail + "]";
	}



}