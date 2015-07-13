package com.lbs.vaadin.view;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.fields.MValueChangeEvent;
import org.vaadin.viritin.fields.MValueChangeListener;
import org.vaadin.viritin.form.AbstractForm.SavedHandler;

import com.lbs.VaadinUI;
import com.lbs.model.Team;
import com.lbs.service.TeamService;
import com.lbs.vaadin.form.TeamForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class TeamView extends VerticalLayout implements View{

	private TeamService teamService;
	private TeamForm teamForm;
	public TeamView() {
	
		
		initLayout();
	}
	
	private void initLayout(){
		teamForm = new TeamForm();
		teamService = VaadinUI.get().getTeamService();
		removeAllComponents();
		
		
		// test label
//		Label label = new Label("Team View works");
//		addComponent(label);
		
		Button newTeamButton = new Button("New Team");
		newTeamButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				teamForm.setEntity(new Team());
				teamForm.openInModalPopup();
			}
		});
		
		
		
		addComponent(newTeamButton);
		
		MTable<Team> teamTable = new MTable<Team>(Team.class).
				withProperties("teamname");
		teamTable.setWidth("100%");
		addComponent(teamTable);
		teamTable.setBeans(teamService.findAll());
		
		teamForm.setSavedHandler(new SavedHandler<Team>() {

			@Override
			public void onSave(Team entity) {
				teamService.saveTeam(entity);
				teamTable.setBeans(teamService.findAll());
				teamForm.getPopup().close();
			}
		});
		
		teamTable.addMValueChangeListener(new MValueChangeListener<Team>() {
			
			@Override
			public void valueChange(MValueChangeEvent<Team> event) {
				teamForm.setEntity(event.getValue());
				teamForm.openInModalPopup();
			}
		});
		
		// sağ click
//		teamTable.addActionHandler(actionHandler);
		
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		initLayout();
	}

}
