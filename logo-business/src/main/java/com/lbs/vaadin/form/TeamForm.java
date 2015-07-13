package com.lbs.vaadin.form;

import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MFormLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.lbs.model.Team;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class TeamForm extends AbstractForm<Team>{

	private TextField teamname = new TextField("Team name");
	public TeamForm() {
	}
	
	@Override
	protected Component createContent() {
		return new MVerticalLayout(new MFormLayout(teamname,createButtonLayout()));
	}
	
	private HorizontalLayout createButtonLayout(){
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button saveButton = new Button("Save");
		saveButton.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				// !!  super.save
				save(event);
			}
		});
		
		horizontalLayout.addComponent(saveButton);
		
		return horizontalLayout;
	}
	
	

}
