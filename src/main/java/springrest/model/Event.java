package springrest.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/************************************************************
 * Entity class for Events table,describing an event which is 
 * posted by a user. 
 * This class is used to store data for the events
 * in database.
 * 
 * @author shivam
 *
 ***********************************************************/
@Entity
@Table(name = "EVENTS")
public class Event implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(nullable = false, name = "NAME")
	private String eventName;

	@Column(name = "DESCRIPTION")
	private String eventDescription;

	@Column(name = "START_TIME")
	private Date startTime;

	@Column(name = "END_TIME")
	private Date endTime;

	@Column(name = "LOCATION")
	private String location;

	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "SUBMITTED_BY")
	private User submittedBy;

	@ManyToMany
	@JoinTable(name = "EVENT_TAGS",
	joinColumns = @JoinColumn(name = "event_id"),
	inverseJoinColumns = @JoinColumn(name = "tag_id"))
	Set<Tags> tags;

	@Column(name = "POSTED")
	boolean posted = false;
	
	@ManyToMany
	@JoinTable(name = "ATTENDEDBY",
	joinColumns = @JoinColumn(name = "event_id"),
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<User> usersAttended;

	/**
	 * 
	 */
	public Event() {
		tags = new HashSet<>();
		usersAttended = new HashSet<>();
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
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the eventDescription
	 */
	public String getEventDescription() {
		return eventDescription;
	}

	/**
	 * @param eventDescription the eventDescription to set
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the submittedBy
	 */
	public User getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * @param submittedBy the postedBy to set
	 */
	public void setSumittedBy(User submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * @return the tags
	 */
	public Set<Tags> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(Set<Tags> tags) {
		this.tags = tags;
	}

	/**
	 * @return the posted
	 */
	public boolean isPosted() {
		return posted;
	}

	/**
	 * @param posted the posted to set
	 */
	public void setPosted(boolean posted) {
		this.posted = posted;
	}

	/**
	 * @param submittedBy the submittedBy to set
	 */
	public void setSubmittedBy(User submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * @return the usersAttended
	 */
	public Set<User> getUsersAttended() {
		return usersAttended;
	}

	/**
	 * @param usersAttended the usersAttended to set
	 */
	public void setUsersAttended(Set<User> usersAttended) {
		this.usersAttended = usersAttended;
	}
}
