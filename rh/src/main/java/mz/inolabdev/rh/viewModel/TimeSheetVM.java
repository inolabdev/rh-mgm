package mz.inolabdev.rh.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import mz.inolabdev.rh.entity.Activity;
import mz.inolabdev.rh.entity.Project;
import mz.inolabdev.rh.services.ProjectService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TimeSheetVM extends AbstractViewModel {

	private Locale ptBr = new Locale("pt", "BR");

	@Wire
	private Listbox lbxProjects;

	@Wire
	private Listbox lbxActivities;

	@WireVariable
	private ProjectService projectService;

	private List<String> week;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("target") Div target,
			@ExecutionArgParam("breadcrumb") Ol ol) {

		Selectors.wireComponents(view, this, false);

		this.target = target;
		this.ol = ol;

		lbxProjects
				.setModel(new ListModelList<Project>(projectService.getAll()));
		
		week = generateWeek();
	}

	@Init
	public void init() {

		week = generateWeek();
	}

	@Command
	public void findProjectActibities(@BindingParam("project") Project pro) {

		Project project = projectService.find(pro.getId());

		lbxActivities.setModel(new ListModelList<Activity>(project
				.getActivities()));

	}

	private List<String> generateWeek() {

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd", ptBr);

		List<String> days = new ArrayList<String>();
		int delta = -cal.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		cal.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 7; i++) {
			days.add(sdf.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}

		return days;
	}

	public Listbox getLbxActivities() {
		return lbxActivities;
	}

	public void setLbxActivities(Listbox lbxActivities) {
		this.lbxActivities = lbxActivities;
	}

	public Listbox getLbxProjects() {
		return lbxProjects;
	}

	public void setLbxProjects(Listbox lbxProjects) {
		this.lbxProjects = lbxProjects;
	}

	public List<String> getWeek() {
		return week;
	}

	public void setWeek(List<String> week) {
		this.week = week;
	}
}
