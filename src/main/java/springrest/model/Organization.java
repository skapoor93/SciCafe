package springrest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/************************************************************
 * Entity class for Organization table,describing which 
 * organization is conducting an event. 
 * This class is used to store data for different organizations
 * in database.
 * 
 * @author shivam
 *
 ***********************************************************/
@Entity
@Table(name = "ORGANIZATION")
public class Organization implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String organizationName;

	@Column(name = "DESCRIPTION")
	private String organizationDescription;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return organizationName;
	}

	public void setName(String name) {
		this.organizationName = name;
	}

	public String getDescription() {
		return organizationDescription;
	}

	public void setDescription(String description) {
		this.organizationDescription = description;
	}
}
