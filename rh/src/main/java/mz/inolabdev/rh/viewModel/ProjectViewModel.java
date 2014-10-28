package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Project;
import mz.inolabdev.rh.services.EmployeeService;
import mz.inolabdev.rh.services.ProjectService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProjectViewModel extends AbstractViewModel {

	@Wire("#mainInclude")
	private Include mainInclude;

	private List<Project> projects;
	
	private List<Employee> customers;

	@WireVariable
	private ProjectService projectService;
	
	@WireVariable
	private EmployeeService employeeService;
	
	@Wire("#projectList")
	private Div projectList;

	@Wire("#projectNew")
	private Div projectNew;
	
	private Project project;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {
		
		customers = employeeService.getAll();
		setProject(new Project());
	}

	@Command
	@NotifyChange("mainInclude")
	public void projectList() {
		
		reload();

		if (projectList != null & projectNew != null) {
			projectList.setVisible(true);
			projectNew.setVisible(false);
		} else
			mainInclude.setSrc("/views/times/projects/index.zul");
	}
	
	@Command
	@NotifyChange("mainInclude")
	public void projectNew() {
		
		project = new Project();

		projectList.setVisible(false);
		projectNew.setVisible(true);
	}
	
	private List<Project> reload() {

		if (projects == null)
			projects = new ArrayList<Project>();

		projects = projectService.getAll();

		return projects;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public List<Employee> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Employee> customers) {
		this.customers = customers;
	}
}
