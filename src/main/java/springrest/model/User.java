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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/************************************************************
 * Entity class for Users table describing data for Users.
 *  
 * This class is used to store data for different Users
 * in database.
 * 
 * @author shivam
 *
 ***********************************************************/
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true, name = "USERNAME")
	private String username;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(nullable = false, name = "PASSWORD")
	private String password;

	@Column(nullable = false, name = "FIRST_NAME")
	private String firstName;

	@Column(nullable = false, name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL")
	private String email;

	@Column(nullable = false, name = "ENABLED")
	private boolean enabled = true;

	@ManyToMany
	@JoinTable(name = "authorities",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "role_id"))
	Set<Role> roles;

	@OneToMany
	@JoinTable(name = "USER_ORGANIZATION",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "organization_id"))
	Set<Organization> organizationalUnit;

	@ManyToMany
	@JoinTable(name = "PROGRAM_AFFILIATIONS",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "program_id"))
	Set<Program> programAffiliations;

	public User()
	{
		roles = new HashSet<Role>();
		programAffiliations = new HashSet<Program>();
		organizationalUnit = new HashSet<Organization>();
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername( String username )
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public boolean isEnabled()
	{
		return enabled;
	}

	public void setEnabled( boolean enabled )
	{
		this.enabled = enabled;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles( Set<Role> roles )
	{
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the programAffiliations
	 */
	public Set<Program> getProgramAffiliations() {
		return programAffiliations;
	}

	/**
	 * @param programAffiliations the programAffiliations to set
	 */
	public void setProgramAffiliations(Set<Program> programAffiliations) {
		this.programAffiliations = programAffiliations;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the organizationalUnit
	 */
	public Set<Organization> getOrganizationalUnit() {
		return organizationalUnit;
	}

	/**
	 * @param organizationalUnit the organizationalUnit to set
	 */
	public void setOrganizationalUnit(Set<Organization> organizationalUnit) {
		this.organizationalUnit = organizationalUnit;
	}
}