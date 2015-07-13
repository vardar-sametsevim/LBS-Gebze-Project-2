package com.lbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lbs.model.Team;
import com.lbs.repository.TeamRepository;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
//	@Transactional
	public boolean isTeamExist(String teamname){
		if(teamRepository.findTeamByTeamname(teamname)==null)
			return false;
		return true;
	}
	
	public List<Team> findAll(){
		return (List<Team>) teamRepository.findAll();
	}
	
//	@Async
	public void saveTeam(Team team){
		teamRepository.save(team);
	}
}
