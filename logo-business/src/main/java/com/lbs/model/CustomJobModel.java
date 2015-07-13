package com.lbs.model;

public class CustomJobModel {

	
	
	private String jobName;
	
	private String description;
	
	private String teamname;

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

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public CustomJobModel(String jobName, String description, String teamname) {
		super();
		this.jobName = jobName;
		this.description = description;
		this.teamname = teamname;
	}
	
	public CustomJobModel() {
		super();
	}
	
	
}
