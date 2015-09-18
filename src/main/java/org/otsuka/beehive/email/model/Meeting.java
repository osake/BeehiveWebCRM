package org.otsuka.beehive.email.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="MEETINGS", catalog="test")
public class Meeting {
	
	private Integer meetingId;


	/**
	 * Customer associated with this meeting
	 */

	private Customer customer;
	

    @NotEmpty(message="Meeting agenda cannot be blank.")
	private String agenda;
	
	@NotEmpty(message="Meeting contact person name cannot be blank.")
	private String contactName;
	

	@NotNull(message="Enter meeting date in correct format")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date day;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date lastUpdateDate; 
	
	private String lastUpdatedUserId;
	
	private Integer flag;

	@Valid
	private Set<FileDetails> fileList = new HashSet<FileDetails>();

	@OneToMany(targetEntity = FileDetails.class, mappedBy = "meeting",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@OrderBy("fileType")
	public Set<FileDetails> getFileList() {
		return fileList;
	}
	
	public void setFileList(Set<FileDetails> fileList) {
		this.fileList = fileList;
	}

	private List<MultipartFile> files;

	private List<String> fileTypes;

	

	/**
	 * Zero argument constructor
	 */
	public Meeting() {
		
	}
	
	/**
	 * @param agenda
	 * @param contactName
	 * @param date
	 */
	public Meeting(String agenda, String contactName, Date date) {
		super();
		this.agenda = agenda;
		this.contactName = contactName;
		this.day = date;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MEETING_ID", unique = true, nullable = false)
	public Integer getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(Integer meetingId) {
		this.meetingId = meetingId;
	}

	@ManyToOne
	@JoinColumn(name="CUST_ID")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "AGENDA")
	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}
	
	@Column(name = "CONTACT_NAME")
	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	@Column(name = "MEETING_DATE")
	public Date getDay() {
		return day;
	}

	public void setDay(Date date) {
		this.day = date;
	}
	@Column(name = "LAST_UPDATE_DATE")
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date date) {
		this.lastUpdateDate = date;
	}
	
	@Transient
	public List<MultipartFile> getFiles() {
		return files;
	}
	
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	
	@Transient
	public List<String> getFileTypes() {
		return fileTypes;
	}
	
	public void setFileTypes(List<String> fileTypes) {
		this.fileTypes = fileTypes;
	}
	
	public boolean equals(Object other) {
		if(this == other) {return true;}
		if(!(other instanceof Meeting)) {return false;}
		final Meeting meeting = (Meeting)other;
		if((meeting.getMeetingId() == null) || (this.getMeetingId() == null) 
				|| (meeting.getMeetingId() != this.getMeetingId())) {return false;}
		
		return true;		
	}
	
	public int hashCode() {
		if(this.getMeetingId() == null) {
			return 29;
		}
        int result;
        result = this.getMeetingId().hashCode();
        result = 29 * result;
        return result;
    }
	@Column(name = "FLAG")
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	@Column(name = "LAST_UPDATED_USER_ID")
	public String getLastUpdatedUserId() {
		return lastUpdatedUserId;
	}

	public void setLastUpdatedUserId(String lastUpdatedUserId) {
		this.lastUpdatedUserId = lastUpdatedUserId;
	}

	
	

}
