package com.lbs.vaadin.view;

import org.tepi.filtertable.FilterTable;
import org.vaadin.viritin.fields.MTable;
import org.vaadin.viritin.form.AbstractForm.SavedHandler;

import com.lbs.VaadinUI;
import com.lbs.model.CustomJobModel;
import com.lbs.model.Job;
import com.lbs.service.JobService;
import com.lbs.vaadin.form.JobForm;
import com.lbs.vaadin.form.UserSelect;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class JobView extends VerticalLayout implements View{

	private JobService jobService;
	private JobForm jobForm;
	private FilterTable filterTable;
	public JobView() {


		initLayout();
	}

	private void initLayout(){
		jobForm = new JobForm();
		jobService = VaadinUI.get().getJobService();
		removeAllComponents();


		Button newTeamButton = new Button("New Job");
		newTeamButton.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				jobForm.setEntity(new Job());
				jobForm.openInModalPopup();
			}
		});



		addComponent(newTeamButton);

		//filterTable

		jobForm.setSavedHandler(new SavedHandler<Job>() {

			@Override
			public void onSave(Job entity) {
				jobService.saveJob(entity);
				jobForm.getPopup().close();
			}
		});

		filterTable = new FilterTable();
		filterTable.addContainerProperty("name",String.class, null);
		filterTable.addContainerProperty("description", Label.class, null);
		filterTable.addContainerProperty("user", Button.class, null);
		filterTable.addContainerProperty("team", String.class, null);
		filterTable.addContainerProperty("userView", Button.class, null);

		filterTable.setWidth("100%");

		addComponent(filterTable);

		fillTable();
	}


	private void fillTable(){
		filterTable.removeAllItems();

		for(Job job : jobService.findAll()){
			Object id = filterTable.addItem();
			filterTable.getContainerProperty(id, "name").setValue(job.getJobName());
			Label label = new Label(job.getDescription().length()>19? job.getDescription().substring(0, 19):job.getDescription());
			filterTable.getContainerProperty(id, "description").setValue(label);

			label.setDescription(job.getDescription());
			Button userInfoButton = new Button(job.getUser().toString() + "-User Info");
			userInfoButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			userInfoButton.addClickListener(new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {

					Window window = new Window("User Info");
					window.setModal(true);
					window.center();
					VerticalLayout verticalLayout = new VerticalLayout();

					verticalLayout.setSpacing(true);
					verticalLayout.setMargin(true);
					UserSelect userSelect = new UserSelect("Change User");
					userSelect.setBeans(VaadinUI.get().getUserService().findAll());
					verticalLayout.addComponent(userSelect);

					Button changeUser = new Button("Change User");
					changeUser.addClickListener(new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {

							if(userSelect.getValue()!=null){
								job.setUser(userSelect.getValue());
								VaadinUI.get().getJobService().saveJob(job);
								fillTable();
								window.close();
							}
						}
					});
					
					verticalLayout.addComponent(changeUser);

					window.setContent(verticalLayout);
					VaadinUI.get().addWindow(window);
				}
			});

			filterTable.getContainerProperty(id, "user").setValue(userInfoButton);

			filterTable.getContainerProperty(id, "team").setValue(job.getUser().getTeam().getTeamname());

			Button viewButton = new Button("User Info View");
			viewButton.addClickListener(new Button.ClickListener() {

				@Override
				public void buttonClick(ClickEvent event) {
					VaadinUI.get().getNavigator().navigateTo("user/"+job.getUser().getId());
				}
			});

			viewButton.setStyleName(ValoTheme.BUTTON_LINK);
			filterTable.getContainerProperty(id, "userView").setValue(viewButton);
		}
	}

	@Override
	public void enter(ViewChangeEvent event) {

		if(event.getParameters().length()>0 && event.getParameters().contains("custom")){
			removeAllComponents();
			MTable<CustomJobModel> jobTable = new MTable<CustomJobModel>(CustomJobModel.class)
					.withProperties("jobName","description","teamname")
					.setBeans(jobService.findCustomJobModels());

			jobTable.setWidth("100%");
			addComponent(jobTable);
		}
		else{
			removeAllComponents();
			initLayout();
		}

	}

}
