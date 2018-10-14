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
 * Entity class for Department table describing Rewards which 
 * are associated to an Event. 
 * This class is used to store data for Rewards in database.
 * 
 * @author shivam
 *
 ***********************************************************/
@Entity
@Table(name = "REWARDS")
public class Reward implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;

	@Column(nullable = false, name = "DESCRIPTION")
	private String description;

	@ManyToOne
	@JoinTable(name = "REWARDS_BY_USER",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "reward_id"))
	private User providedBy;

	@ManyToOne
	@JoinTable(name = "REWARDS_BY_ORGANIZATION",
	joinColumns = @JoinColumn(name = "organization_id"),
	inverseJoinColumns = @JoinColumn(name = "reward_id"))
	private Organization rewardProvider;

	@Column(name = "APPROVED")
	private boolean approved;

	@ManyToMany
	@JoinTable(name = "QUALIFIED_EVENTS",
	joinColumns = @JoinColumn(name = "event_id"),
	inverseJoinColumns = @JoinColumn(name = "reward_id"))
	private Set<Event> qualifiedEvents;

	@ManyToMany
	@JoinTable(name = "REWARD_TAGS",
	joinColumns = @JoinColumn(name = "tag_id"),
	inverseJoinColumns = @JoinColumn(name = "reward_id"))
	private Set<Tags> rewardTags;

	@ManyToOne
	@JoinTable(name = "QUALIFIED_USERS",
	joinColumns = @JoinColumn(name = "user_id"),
	inverseJoinColumns = @JoinColumn(name = "reward_id"))
	private User qualifiedUsers;

	@Column(name = "START_TIME", nullable = false)
	private Date startTime;

	@Column(name = "END_TIME", nullable = false)
	private Date endTime;

	/**
	 * 
	 */
	public Reward() {
		qualifiedEvents = new HashSet<>();
		rewardTags = new HashSet<>();
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the providedBy
	 */
	public User getProvidedBy() {
		return providedBy;
	}

	/**
	 * @param providedBy the providedBy to set
	 */
	public void setProvidedBy(User providedBy) {
		this.providedBy = providedBy;
	}

	/**
	 * @return the approved
	 */
	public boolean isApproved() {
		return approved;
	}

	/**
	 * @param approved the approved to set
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	/**
	 * @return the rewardProvider
	 */
	public Organization getRewardProvider() {
		return rewardProvider;
	}

	/**
	 * @param rewardProvider the rewardProvider to set
	 */
	public void setRewardProvider(Organization rewardProvider) {
		this.rewardProvider = rewardProvider;
	}

	/**
	 * @return the qualifiedEvents
	 */
	public Set<Event> getQualifiedEvents() {
		return qualifiedEvents;
	}

	/**
	 * @param qualifiedEvents the qualifiedEvents to set
	 */
	public void setQualifiedEvents(Set<Event> qualifiedEvents) {
		this.qualifiedEvents = qualifiedEvents;
	}

	/**
	 * @return the rewardTags
	 */
	public Set<Tags> getRewardTags() {
		return rewardTags;
	}

	/**
	 * @param rewardTags the rewardTags to set
	 */
	public void setRewardTags(Set<Tags> rewardTags) {
		this.rewardTags = rewardTags;
	}

	/**
	 * @return the qualifiedUsers
	 */
	public User getQualifiedUsers() {
		return qualifiedUsers;
	}

	/**
	 * @param qualifiedUsers the qualifiedUsers to set
	 */
	public void setQualifiedUsers(User qualifiedUsers) {
		this.qualifiedUsers = qualifiedUsers;
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
}