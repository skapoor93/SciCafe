package springrest.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/************************************************************
 * Entity class for Title table describing a Title which is 
 * associated to a User. 
 * This class is used to store data for Titles
 * in database.
 * 
 * @author shivam
 *
 ***********************************************************/
@Entity
@Table(name = "USER_TITLE")
public class Title implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	Long id;
	
	@Column(name = "TITLE_NAME", nullable = false)
	String titleName;
	
	@OneToMany
	@JoinTable(name = "TITLE_OF_USER",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "title_id"))
	Set<User> users;

	/**
	 * 
	 */
	public Title() {
		users = new HashSet<User>();
	}

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
	 * @return the titleName
	 */
	public String getTitleName() {
		return titleName;
	}

	/**
	 * @param titleName the titleName to set
	 */
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	/**
	 * @return the users
	 */
	public Set<User> getUsers() {
		return users;
	}

	/**
	 * @param users the users to set
	 */
	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
