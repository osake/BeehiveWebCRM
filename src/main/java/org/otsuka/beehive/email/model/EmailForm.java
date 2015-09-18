package org.otsuka.beehive.email.model;

import java.util.Map;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;



public class EmailForm {

	@NotEmpty(message="First name is a required field.")
	private String firstName;
	
	private String custId;

	@NotEmpty(message="Last name is required field.")
	private String lastName;
	
	@NotEmpty(message="Email is mandatory.")
	@Email(message="Please enter email in a valid format.")
	private String email;
	
	private String emailText;
	
	@NotEmpty(message="title cannot be left blank.")
	private String title;
	
	@NotEmpty(message="Address cannot be blank.")
	private String address;
	
	@NotEmpty(message="representative title is required.")
	private String repTitle;
	
	@NotEmpty(message="representative name is required.")
	private String repName;
	
	private String dateStr;
	
	private String docUrl;
	
	@NotEmpty(message="Please enter a valid pin code")
	private String zip;
	
	private String state;
    private Map countries;
	
	@NotEmpty(message="City cannot be null.")
	private String city;
	
	@NotEmpty(message="Country cannot be null.")
	private String country;
	
	private Map<String, State> states;
	
	private String subject;
	private String custName;
	
	
		
	public Map<String, State> getStates() {
		return states;
	}
	public void setStates(Map<String, State> states) {
		this.states = states;
	}

	public Map getCountries() {
		return countries;
	}
	public void setCountries(Map countries) {
		this.countries = countries;
	}


	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailText() {
		return emailText;
	}
	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRepTitle() {
		return repTitle;
	}
	public void setRepTitle(String repTitle) {
		this.repTitle = repTitle;
	}
	public String getRepName() {
		return repName;
	}
	public void setRepName(String repName) {
		this.repName = repName;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public String getDocUrl() {
		return docUrl;
	}
	public void setDocUrl(String docUrl) {
		this.docUrl = docUrl;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
		public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	
	
	
	
}
