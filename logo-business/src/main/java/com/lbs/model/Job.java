package com.lbs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Job {

	
	@Id
	@GeneratedValue
	private Long id;
	
	private String jobName;
	
	@Column(length=1000)
	private String description;
	
	
	private Date startDate;
	
	private Date endDate;
	
	@ManyToOne
	private User user;
	
	
	public Job() {
		super();
	}
	
	public Job(Long id, String jobName, String description, Date startDate, Date endDate, User user) {
		super();
		this.id = id;
		this.jobName = jobName;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
