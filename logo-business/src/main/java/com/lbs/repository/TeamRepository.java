package com.lbs.repository;

import org.springframework.data.repository.CrudRepository;

import com.lbs.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {
	
	Team findTeamByTeamname(String teamname);

}
