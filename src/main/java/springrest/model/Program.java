package springrest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/************************************************************
 * Entity class for Programs table,describing different program 
 * affiliations to which a user is associated. 
 * This class is used to store data for different programs to 
 * which a user is associated in database.
 * 
 * @author shivam
 *
 ***********************************************************/
@Entity
@Table(name = "PROGRAMS")
public class Program implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String programName;
	
	@Column(name = "FULL_NAME")
	private String programFullName;

	@Column(name = "DESCRIPTION")
	private String programDescription;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the programName
	 */
	public String getProgramName() {
		return programName;
	}

	/**
	 * @param programName the programName to set
	 */
	public void setProgramName(String programName) {
		this.programName = programName;
	}

	/**
	 * @return the programDescription
	 */
	public String getProgramDescription() {
		return programDescription;
	}

	/**
	 * @param programDescription the programDescription to set
	 */
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	/**
	 * @return the programFullName
	 */
	public String getProgramFullName() {
		return programFullName;
	}

	/**
	 * @param programFullName the programFullName to set
	 */
	public void setProgramFullName(String programFullName) {
		this.programFullName = programFullName;
	}
}
