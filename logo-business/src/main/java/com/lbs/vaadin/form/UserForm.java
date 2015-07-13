package com.lbs.vaadin.form;

import org.vaadin.viritin.form.AbstractForm;
import org.vaadin.viritin.layouts.MVerticalLayout;

import com.lbs.model.User;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Button.ClickEvent;

public class UserForm extends AbstractForm<User>{

	
	private TextField firstname = new TextField("First name");
	private TextField lastname = new TextField("Last name");
	private DateField birthdDate = new DateField("BirthDate");
	private TeamSelect team = new TeamSelect("Team");
	
	public UserForm() {
		
	}
	
	@Override
	protected Component createContent() {
		return new MVerticalLayout
				(new FormLayout
						(firstname,lastname,birthdDate,
								team,createButtonLayout()));
	}
	
	private HorizontalLayout createButtonLayout(){
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button saveButon = new Button("Save");
		saveButon.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {

				save(event);
			}
		});
		horizontalLayout.addComponent(saveButon);
		return horizontalLayout;
	}
	
	

}
