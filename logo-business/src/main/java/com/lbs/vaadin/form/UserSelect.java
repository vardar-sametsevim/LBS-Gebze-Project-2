package com.lbs.vaadin.form;

import org.vaadin.viritin.fields.TypedSelect;

import com.lbs.VaadinUI;
import com.lbs.model.User;
import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;

public class UserSelect extends TypedSelect<User> {

	
	public UserSelect(String caption) {
		super(User.class);
		withSelectType(ComboBox.class);
		setCaption(caption);
	}
	
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		setOptions(VaadinUI.get().getUserService().findAll());
		super.setPropertyDataSource(newDataSource);
	}
	
	
	
	
}
