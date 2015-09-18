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
//import javax.validation.Valid;
import javax.validation.constraints.NotNull;

//import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="FILE_DETAILS", catalog="test")
public class FileDetails {
	
	private Integer fileId;


	/**
	 * Meeting associated with this Uploaded File
	 */

	private Meeting meeting;
	

    @NotEmpty(message="URL  cannot be blank.")
	private String URL;
	
	@NotEmpty(message="File name cannot be blank.")
	private String fileName;
	

	@NotNull(message="Enter meeting date in correct format")
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date  day;

	private String fileType; 

	

	/**
	 * Zero argument constructor
	 */
	public FileDetails() {
		
	}
	
	/**
	 * @param agenda
	 * @param contactName
	 * @param date
	 */
	public FileDetails(String URL, String fileName, Date date) {
		super();
		this.URL = URL;
		this.fileName = fileName;
		this.day = date;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "FILE_ID", unique = true, nullable = false)
	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}

	@ManyToOne
	@JoinColumn(name="MEETING_ID")
	public Meeting getMeeting() {
		return meeting;
	}

	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}

	@Column(name = "URL")
	public String getURL() {
		return URL;
	}

	public void setURL(String URL) {
		this.URL = URL;
	}
	
	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name = "UPLOAD_DATE")
	public Date getDay() {
		return day;
	}

	public void setDay(Date date) {
		this.day = date;
	}
	
	@Column(name = "FILE_TYPE")
	public String getFileType() {
		return fileType;
	}
	
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public boolean equals(Object other) {
		if(this == other) {return true;}
		if(!(other instanceof FileDetails)) {return false;}
		final FileDetails fd = (FileDetails)other;
		if((fd.getFileId() == null) || (this.getFileId() == null) || (fd.getFileId() != this.getFileId())) {return false;}
		
		return true;		
	}
	
	public int hashCode() {
		if(this.getFileId() == null) {
			return 29;
		}
        int result;
        result = this.getFileId().hashCode();
        result = 29 * result;
        return result;
    }
	
	

}
