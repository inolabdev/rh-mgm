package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.Department;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.IdentityDocumentType;
import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.DepartamentService;
import mz.inolabdev.rh.services.EmployeeService;
import mz.inolabdev.rh.services.IdentityDocumentTypeService;
import mz.inolabdev.rh.services.JobPositionService;
import mz.inolabdev.rh.services.UserService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class EmployeeVM extends AbstractViewModel {

	private Div target;

	private List<Employee> emps;

	private List<Department> deps;

	private List<JobPosition> jobs;

	private List<IdentityDocumentType> docTypes;
	
	private List<User> users;

	private Employee emp;

	private Department dep;

	private JobPosition job;

	private IdentityDocumentType doc;

	@WireVariable
	private EmployeeService employeeService;

	@WireVariable
	private IdentityDocumentTypeService identityDocumentTypeService;

	@WireVariable
	private DepartamentService departamentService;

	@WireVariable
	private JobPositionService jobPositionService;
	
	@WireVariable
	private UserService userService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("target") Div target,
			@ExecutionArgParam("breadcrumb") Ol ol) {

		Selectors.wireComponents(view, this, false);

		this.target = target;
		this.ol = ol;
	}	

	@Init
	public void init() {

		// *** Initialize ***//
		emps = employeeService.getAll();
				
		emp = new Employee();

		docTypes = identityDocumentTypeService.getAll();

		jobs = jobPositionService.getAll();

		deps = departamentService.getAll();
		
		users = userService.getAll();

	}

	@Command
	public void empList() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/employee/index.zul", target, map);

		links = new ArrayList<String>();
		links.add("Employeee");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void empNew() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/employee/create.zul", target, map);

		links = new ArrayList<String>();
		links.add("Employees");
		links.add("Novo");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void saveEmp() {

		employeeService.create(emp);

		Clients.showNotification("Funcionario registado com sucesso!", "info",
				target, "before_center", -1);
		empList();
	}

	public List<Employee> getEmps() {
		return emps;
	}

	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}

	public Employee getEmp() {
		return this.emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Department getDep() {
		return this.dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public JobPosition getJob() {
		return this.job;
	}

	public void setJob(JobPosition job) {
		this.job = job;
	}

	public IdentityDocumentType getDoc() {
		return this.doc;
	}

	public void setDoc(IdentityDocumentType doc) {
		this.doc = doc;
	}

	public List<Department> getDeps() {
		return this.deps;
	}

	public void setDeps(List<Department> deps) {
		this.deps = deps;
	}

	public List<JobPosition> getJobs() {
		return this.jobs;
	}

	public void setJobs(List<JobPosition> jobs) {
		this.jobs = jobs;
	}

	public List<IdentityDocumentType> getDocTypes() {
		return this.docTypes;
	}

	public void setDocTypes(List<IdentityDocumentType> docTypes) {
		this.docTypes = docTypes;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
