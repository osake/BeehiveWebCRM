package org.otsuka.beehive.email.model;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

//import org.hibernate.validator.constraints.Email;
//import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CRM_CUSTOMER", catalog = "test")
public class Customer implements java.io.Serializable {

	/**
	 * Generate Sereial Version UID
	 */
	private static final long serialVersionUID = -881163358331539762L;
	private Integer custID;
	private String custCode;
	//@NotEmpty(message="Customer name cannot be left blank.")
	private String custName;
	//@Email(message="A valid email id of customer is required.")
	private String custEmail;
	private String custAttachmentLoc;
	
	private String firstName;
	private String lastName;
	private String title;
	private String prefix;
	private String secondaryPrefix;
	private String titleSecondary;
	@Column(name="SECONDARY_TITLE")
	public String getTitleSecondary() {
		return titleSecondary;
	}

	public void setTitleSecondary(String titleSecondary) {
		this.titleSecondary = titleSecondary;
	}

	//@NotEmpty(message="address cannot be left blank.")
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zip;
	private String country;
	private String stage;
	//@NotEmpty(message="Phone field cannot be left blank.")
	private String phone;
	private String secphone;
	private String seccelphone;
	
	private String accType;
	
	private String contractVal; 
	
	private String size;
	
	private String notes;
	
	private String segment;
	
	private Map<String, String> states;
	
	private String webLink;
	
	private String primaryLead;
	private String otsukaSme;
	private String openMindsSme;	
	private String ibmSme;
	private String hmaSme;
	private String priority;
	

	private String priority2;
	private Integer overallPopulation;
	private Integer smiPopulation;
	//private String enrollees;
	private String financeModel;
	private String cellPhone;
	/*private String homePhone;*/
	private int salesExecutiveId;
	private String customerAttachmentLocation;
	
	
	
	private String secondaryFirstName;
	private String secondaryLastName;
	private String secondaryContactTitle;
	//private String secondaryContactTelephone;
	private String secondaryContactEmail;
	
/*	private String tertiaryFirstName;
	private String tertiaryLastName;
	private String tertiaryContactTitle;
	private String tertiaryContactTelephone;
	

	private String tertiaryContactEmail;*/
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startTime;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endTime;
	
	private String duration;
	private String customerType;
	private String segmentation;
	private String expectedContractValue;
	private String actualContractValue;
	private String totalContractValue;
	private String contractDurationInYears;
	private Date contractDate;
	private String pointOfContact;
	private String contactNotes;
	private String customerRevenue;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date lastUpdateDate; 
	
	private String lastUpdatedUserId; 
	
	
	@Column(name="DURATION")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Column(name="CUSTOMER_TYPE")
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Column(name="SEGMENTATION")
	public String getSegmentation() {
		return segmentation;
	}

	public void setSegmentation(String segmentation) {
		this.segmentation = segmentation;
	}

	@Column(name="EXPECTED_CONTRACT_VALUE")
	public String getExpectedContractValue() {
		return expectedContractValue;
	}

	public void setExpectedContractValue(String expectedContractValue) {
		this.expectedContractValue = expectedContractValue;
	}

	@Column(name="ACTUAL_CONTRACT_VALUE")
	public String getActualContractValue() {
		return actualContractValue;
	}

	public void setActualContractValue(String actualContractValue) {
		this.actualContractValue = actualContractValue;
	}

	@Column(name="TOTAL_CONTRACT_VALUE")
	public String getTotalContractValue() {
		return totalContractValue;
	}

	public void setTotalContractValue(String totalContractValue) {
		this.totalContractValue = totalContractValue;
	}

	@Column(name="CONTRACT_DURATION_IN_YRS")
	public String getContractDurationInYears() {
		return contractDurationInYears;
	}

	public void setContractDurationInYears(String contractDurationInYears) {
		this.contractDurationInYears = contractDurationInYears;
	}
	
	
	@Column(name="CONTRACT_DATE")
	public Date getContractDate() {
		return contractDate;
	}

	public void setContractDate(Date contractDate) {
		this.contractDate = contractDate;
	}

	//@Column(name="END_TIME")
	@Transient
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	//@Column(name="START_TIME")
	@Transient
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	
	
	
	/*@Column(name="TERTIARY_CONTACT_FIRST_NAME")
	public String getTertiaryFirstName() {
		return tertiaryFirstName;
	}

	public void setTertiaryFirstName(String tertiaryFirstName) {
		this.tertiaryFirstName = tertiaryFirstName;
	}

	@Column(name="TERTIARY_CONTACT_LAST_NAME")
	public String getTertiaryLastName() {
		return tertiaryLastName;
	}

	public void setTertiaryLastName(String tertiaryLastName) {
		this.tertiaryLastName = tertiaryLastName;
	}
    
	@Column(name="TERTIARY_CONTACT_TITLE")
	public String getTertiaryContactTitle() {
		return tertiaryContactTitle;
	}

	public void setTertiaryContactTitle(String tertiaryContactTitle) {
		this.tertiaryContactTitle = tertiaryContactTitle;
	}
	
	@Column(name="TERTIARY_CONTACT_TELEPHONE")
	public String getTertiaryContactTelephone() {
		return tertiaryContactTelephone;
	}

	public void setTertiaryContactTelephone(String tertiaryContactTelephone) {
		this.tertiaryContactTelephone = tertiaryContactTelephone;
	}

	@Column(name="TERTIARY_CONTACT_EMAIL")
	public String getTertiaryContactEmail() {
		return tertiaryContactEmail;
	}

	public void setTertiaryContactEmail(String tertiaryContactEmail) {
		this.tertiaryContactEmail = tertiaryContactEmail;
	}

	*/
	
	
	
	
	@Column(name="SECONDARY_CONTACT_EMAIL")
	public String getSecondaryContactEmail() {
		return secondaryContactEmail;
	}

	public void setSecondaryContactEmail(String secondaryContactEmail) {
		this.secondaryContactEmail = secondaryContactEmail;
	}

	/*@Column(name="SECONDARY_CONTACT_TELEPHONE")
	public String getSecondaryContactTelephone() {
		return secondaryContactTelephone;
	}

	public void setSecondaryContactTelephone(String secondaryContactTelephone) {
		this.secondaryContactTelephone = secondaryContactTelephone;
	}*/

	@Column(name="SECONDARY_CONTACT_TITLE")
	public String getSecondaryContactTitle() {
		return secondaryContactTitle;
	}

	public void setSecondaryContactTitle(String secondaryContactTitle) {
		this.secondaryContactTitle = secondaryContactTitle;
	}

	@Column(name="SECONDARY_CONTACT_LAST_NAME")
	public String getSecondaryLastName() {
		return secondaryLastName;
	}

	public void setSecondaryLastName(String secondaryLastName) {
		this.secondaryLastName = secondaryLastName;
	}

	@Column(name="SECONDARY_CONTACT_FIRST_NAME")
	public String getSecondaryFirstName() {
		return secondaryFirstName;
	}

	public void setSecondaryFirstName(String secondaryFirstName) {
		this.secondaryFirstName = secondaryFirstName;
	}
	
	@Transient
	public String getCustomerAttachmentLocation() {
		return customerAttachmentLocation;
	}

	public void setCustomerAttachmentLocation(String customerAttachmentLocation) {
		this.customerAttachmentLocation = customerAttachmentLocation;
	}

	/*@Column(name="SALES_EXEC_ID")*/
	@Transient
	public int getSalesExecutiveId() {
		return salesExecutiveId;
	}

	public void setSalesExecutiveId(int salesExecutiveId) {
		this.salesExecutiveId = salesExecutiveId;
	}


	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date stepStartDate;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date stepEndDate;
	
	
	@Valid
	private Set<Meeting> meetingList = new TreeSet<Meeting>();

	@OneToMany(targetEntity = Meeting.class, mappedBy = "customer",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Set<Meeting> getMeetingList() {
		return meetingList;
	}

	public void setMeetingList(Set<Meeting> meetingList) {
		this.meetingList = meetingList;
	}

	public Customer() {
	}

	public Customer(Integer custID, String custCode, String custName,
			String custEmail, String custAttachmentLoc) {
		super();
		this.custID = custID;
		this.custCode = custCode;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custAttachmentLoc = custAttachmentLoc;
	}

	// CUST_ID,CUST_CODE,
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CUST_ID", unique = true, nullable = false)
	public Integer getCustID() {
		return custID;
	}

	public void setCustID(Integer custID) {
		this.custID = custID;
	}

	@Column(name = "CUST_CODE")
	public String getCustCode() {
		return custCode;
	}

	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}

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
	
	@Column(name = "CUST_ATTACHMENT_LOC")
	public String getCustAttachmentLoc() {
		return custAttachmentLoc;
	}

	public void setCustAttachmentLoc(String custAttachmentLoc) {
		this.custAttachmentLoc = custAttachmentLoc;
	}
	
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
	
	@Transient
	public Map<String, String> getStates() {
		return states;
	}

	public void setStates(Map<String, String> states) {
		this.states = states;
	}

	@Override
	public String toString() {
		return "Customer [custID=" + custID + ", custName=" + custName
				+ ", custEmail=" + custEmail + "]";
	}
	
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ACC_TYPE")
	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	@Column(name = "CONTRACT_VAL")
	public String getContractVal() {
		return contractVal;
	}

	public void setContractVal(String contractVal) {
		this.contractVal = contractVal;
	}
	
	@Column(name = "SIZE")
	public String getSize() {
		return size;
	}
	
	public void setSize(String size) {
		this.size = size;
	}
	
	@Column(name = "NOTES")
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	@Column(name = "SEGMENT")
	public String getSegment() {
		return segment;
	}
	
	public void setSegment(String segment) {
		this.segment = segment;
	}

	@Column(name = "WEB_LINK")
	public String getWebLink() {
		return webLink;
	}

	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	@Column(name = "PRIMARY_LEAD")
	public String getPrimaryLead() {
		return primaryLead;
	}

	public void setPrimaryLead(String primaryLead) {
		this.primaryLead = primaryLead;
	}

	@Column(name = "OTSUKA_SME")
	public String getOtsukaSme() {
		return otsukaSme;
	}

	public void setOtsukaSme(String otsukaSme) {
		this.otsukaSme = otsukaSme;
	}

	@Column(name = "OPEN_MINDS_SME")
	public String getOpenMindsSme() {
		return openMindsSme;
	}

	public void setOpenMindsSme(String openMindsSme) {
		this.openMindsSme = openMindsSme;
	}

	@Column(name = "IBM_SME")
	public String getIbmSme() {
		return ibmSme;
	}

	public void setIbmSme(String ibmSme) {
		this.ibmSme = ibmSme;
	}

	@Column(name = "HMA_SME")
	public String getHmaSme() {
		return hmaSme;
	}

	
	public void setHmaSme(String hmaSme) {
		this.hmaSme = hmaSme;
	}

	@Column(name = "PRIORITY_TIER1")
	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	@Column(name = "PRIORITY_TIER2")
	public String getPriority2() {
		return priority2;
	}
	
	public void setPriority2(String priority2) {
		this.priority2 = priority2;
	}
	
	/*@Column(name = "ENROLLEES")
	public String getEnrollees() {
		return enrollees;
	}
	
	public void setEnrollees(String enrollees) {
		this.enrollees = enrollees;
	}*/

	@Transient
	public Date getStepStartDate() {
		return stepStartDate;
	}

	public void setStepStartDate(Date stepStartDate) {
		this.stepStartDate = stepStartDate;
	}

	@Transient
	public Date getStepEndDate() {
		return stepEndDate;
	}

	public void setStepEndDate(Date stepEndDate) {
		this.stepEndDate = stepEndDate;
	}

	
	
	@Column(name = "ENROLLEES")
	public Integer getOverallPopulation() {
		return overallPopulation;
	}

	public void setOverallPopulation(Integer overallPopulation) {
		this.overallPopulation = overallPopulation;
	}

	
	@Column(name = "SMI_POPULATIONS")
	public Integer getSmiPopulation() {
		return smiPopulation;
	}

	public void setSmiPopulation(Integer smiPopulation) {
		this.smiPopulation = smiPopulation;
	}
	
	@Column(name = "FIN_MODEL")
	public String getFinanceModel() {
		return financeModel;
	}
	
	public void setFinanceModel(String financeModel) {
		this.financeModel = financeModel;
	}
	
	@Column(name = "CELL_PHONE")
	public String getCellPhone() {
		return cellPhone;
	}
	
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	
	@Column(name = "POC_FIELD")
	public String getPointOfContact() {
		return pointOfContact;
	}
	
	public void setPointOfContact(String pointOfContact) {
		this.pointOfContact = pointOfContact;
	}
	
	@Column(name = "CONTACT_PREFIX")
	public String getPrefix() {
		return prefix;
	}
	
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Column(name = "SEC_CONTACT_PREFIX")
	public String getSecondaryPrefix() {
		return secondaryPrefix;
	}
	
	public void setSecondaryPrefix(String secondaryPrefix) {
		this.secondaryPrefix = secondaryPrefix;
	}
	
	@Column(name = "CONTACT_NOTES")
	public String getContactNotes() {
		return contactNotes;
	}
	
	public void setContactNotes(String contactNotes) {
		this.contactNotes = contactNotes;
	}
	
	@Column(name = "CUSTOMER_REVENUE")
	public String getCustomerRevenue() {
		return customerRevenue;
	}
	
	public void setCustomerRevenue(String customerRevenue) {
		this.customerRevenue = customerRevenue;
	}

	@Column(name = "SECONDARY_CONTACT_TELEPHONE")
	public String getSecphone() {
		return secphone;
	}

	public void setSecphone(String secphone) {
		this.secphone = secphone;
	}

	@Column(name = "SECONDARY_CONTACT_CELLPHONE")
	public String getSeccelphone() {
		return seccelphone;
	}

	public void setSeccelphone(String seccelphone) {
		this.seccelphone = seccelphone;
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
	
	public boolean equals(Object other) {
		if(this == other) {return true;}
		if(!(other instanceof Customer)) {return false;}
		final Customer cust = (Customer)other;
		if((cust.getCustID() == null) || (this.getCustID() == null) 
				|| (cust.getCustID() != this.getCustID())) {return false;}
		
		return true;	
	}
	
	public int hashCode() {
		if(this.getCustID() == null) {
			return 29;
		}
        int result;
        result = this.getCustID().hashCode();
        result = 29 * result;
        return result;
    }
	
	
}