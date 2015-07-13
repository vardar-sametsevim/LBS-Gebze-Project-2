package com.lbs.vaadin.view;

import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.form.AbstractForm.SavedHandler;

import com.lbs.VaadinUI;
import com.lbs.model.User;
import com.lbs.service.UserService;
import com.lbs.vaadin.form.UserForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.VerticalLayout;

public class UserView extends VerticalLayout implements View{

	private UserForm userForm;
	private UserService userService;
	public UserView() {
	
		initLayout();
	}
	
	private void initLayout(){
		userService = VaadinUI.get().getUserService();
		userForm = new UserForm();
		removeAllComponents();
		// test label
//		Label label = new Label("User View works");
//		addComponent(label);
		
		Button newUser = new Button("New User");
		newUser.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {

				userForm.setEntity(new User());
				userForm.openInModalPopup();
			}
		});
		addComponent(newUser);
		
		 MTable<User> userTable = 
				 new MTable<User>(User.class).
				 withProperties("firstname","lastname"
						 ,"birthdate","team");
		 userTable.setWidth("100%");
		 userTable.setBeans(userService.findAll());
		 addComponent(userTable);
		 
		 
		 userForm.setSavedHandler(new SavedHandler<User>() {

			@Override
			public void onSave(User entity) {

				userService.saveUser(entity);
				userForm.getPopup().close();
				userTable.setBeans(userService.findAll());
			}
		});
		 
	}
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		if(event.getParameters().length()>0){
			Long userId = Long.parseLong(event.getParameters());
			User user = userService.findUser(userId);
			if(user!=null){
				VerticalLayout verticalLayout = new VerticalLayout();
				Label label = new Label(user.getFirstname() + user.getLastname());
				label.setStyleName(ValoTheme.LABEL_H1);
				Label label2 = new Label(user.getTeam().toString());
				label2.setStyleName(ValoTheme.LABEL_H1);
				verticalLayout.addComponent(label);
				verticalLayout.addComponent(label2);
				addComponent(verticalLayout);
			}
			else{
				VerticalLayout verticalLayout = new VerticalLayout();
				Label label = new Label("User not found");
				label.setStyleName(ValoTheme.LABEL_FAILURE);
				verticalLayout.addComponent(label);
				addComponent(verticalLayout);
			}
		}
		else{
			initLayout();
		}
		
	}

}
