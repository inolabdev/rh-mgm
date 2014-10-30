package mz.inolabdev.rh.viewModel;

import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.Activity;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Project;
import mz.inolabdev.rh.services.ActivityService;
import mz.inolabdev.rh.services.EmployeeService;
import mz.inolabdev.rh.services.ProjectService;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProjectVM extends AbstractViewModel {

	private Div target;

	private List<Project> projects;

	private List<Employee> customers;

	@WireVariable
	private ProjectService projectService;

	@WireVariable
	private EmployeeService employeeService;

	private Project project;

	@Wire("#chsCustomers")
	private Chosenbox chsCustomers;

	@Wire("#chsAdmins")
	private Chosenbox chsAdmins;

	private Activity activity;

	@WireVariable
	private ActivityService activityService;

	private ListModelList<Activity> activities;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("target") Div target,
			@ExecutionArgParam("breadcrumb") Ol ol) {

		Selectors.wireComponents(view, this, false);

		this.target = target;
		this.ol = ol;

		activities = new ListModelList<Activity>();
	}

	@Init
	public void init() {

		projects = projectService.getAll();
		customers = employeeService.getAll();
		setProject(new Project());
		activity = new Activity();
	}

	@Command
	public void projectList() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/times/projects/index.zul", target,
				map);
	}

	@Command
	public void projectNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/times/projects/create.zul", target,
				map);
	}

	@Command
	@NotifyChange({ "project" })
	public void saveProject() {

		if (chsCustomers.getSelectedObjects().isEmpty()
				|| chsAdmins.getSelectedObjects().isEmpty())

			Clients.showNotification(
					"Selecione um ou mais colaboradores ou administradores!",
					"warning", target, "before_center", -1);

		else {
			for (Object obj : chsCustomers.getSelectedObjects())
				project.getCustomers().add((Employee) obj);

			for (Object obj : chsAdmins.getSelectedObjects())
				project.getAdmins().add((Employee) obj);

			projectService.create(project);

			Clients.showNotification("Projecto registado com sucesso!", "info",
					target, "before_center", -1);

			projectShow(project.getId());
		}
	}

	@Command
	@NotifyChange({ "project" })
	public void projectShow(@BindingParam("projId") Long id) {

		project = projectService.find(id);

		activities.addAll(project.getActivities());

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("project", project);
		map.put("activities", activities);
		target.getChildren().clear();
		Executions.createComponents("views/times/projects/show.zul", target,
				map);
	}

	@NotifyChange("*")
	@Command
	public void saveActivity(@BindingParam("project") Project pro,
			@BindingParam("list") final Listbox listbox) {

		activity.setProject(pro);
		activityService.create(activity);

		Clients.showNotification(
				"Actividade associada ao projecto: " + pro.getName(), "info",
				target, "before_center", -1);
		
		setActivity(new Activity());
		
		Project current = projectService.find(pro.getId());

		listbox.setModel(new ListModelList<Activity>(current.getActivities()));
		
		BindUtils.postNotifyChange(null, null, ProjectVM.this, "*");
	}

	@NotifyChange("*")
	@Command
	public void deleteActivity(@BindingParam("activity") Activity activity,
			@BindingParam("list") final Listbox listbox,
			@BindingParam("project") final Project pro) {

		if (activity == null)
			Clients.showNotification("Selecione a actividade a ser eliminada!",
					"warning", target, "before_center", -1);
		else {

			final Activity act = activityService.find(activity.getId());

			String str = "Deseja eliminar a actividade: " + act.getName() + "?";

			Messagebox.show(str, "Confirmação", Messagebox.OK
					| Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener<Event>() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == Messagebox.OK) {

								activityService.delete(act.getId());

								Project current = projectService.find(pro.getId());
								listbox.setModel(new ListModelList<Activity>(current.getActivities()));

								Clients.showNotification("Actividade eliminada com sucesso!", "info", target, "before_center", -1);
								BindUtils.postNotifyChange(null, null, ProjectVM.this, "*");
							}
						}
					});
			
		}
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Employee> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Employee> customers) {
		this.customers = customers;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ListModelList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ListModelList<Activity> activities) {
		this.activities = activities;
	}
}
