package com.lbs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.lbs.model.CustomJobModel;
import com.lbs.model.Job;

public interface JobRepository extends CrudRepository<Job, Long> {
	

	@Query("select new com.lbs.model.CustomJobModel(j.jobName,j.description,j.user.team.teamname) from Job j")
	List<CustomJobModel> findCustomJobModels();
}
