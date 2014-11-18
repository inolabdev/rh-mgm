package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.Department;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.IdentityDocumentType;
import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.services.DepartamentService;
import mz.inolabdev.rh.services.EmployeeService;
import mz.inolabdev.rh.services.IdentityDocumentTypeService;
import mz.inolabdev.rh.services.JobPositionService;
import mz.inolabdev.rh.services.LogService;

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
public class EmployeeViewModel {

	@Wire("#mainInclude")
	private Include mainInclude;

	@Wire("#empList")
	private Div empList;

	@Wire("#empNew")
	private Div empNew;

	@WireVariable
	private EmployeeService employeeService;

	@WireVariable
	private IdentityDocumentTypeService identityDocumentTypeService;

	@WireVariable
	private LogService logService;

	@WireVariable
	private DepartamentService departamentService;

	@WireVariable
	private JobPositionService jobPositionService;

	private List<Employee> emps;

	private List<Department> deps;

	private List<JobPosition> jobs;

	private List<IdentityDocumentType> docTypes;

	private Employee emp;

	private Department dep;
	
	private JobPosition job;
	
	private IdentityDocumentType doc;
	
	

	private IdentityDocumentType docType;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		setEmp(new Employee());

		setDeps(departamentService.getAll());

		setJobs(jobPositionService.getAll());

		setEmps(employeeService.getAll());
		
		setDocTypes(identityDocumentTypeService.getAll());

		reload();

	}

	@Command
	@NotifyChange("mainInclude")
	public void employeeList() {
		mainInclude.setSrc("views/employee/index.zul");
	}

	@Command
	@NotifyChange("emps")
	public void empList() {

		reload();

		if (empList != null & empNew != null) {
			empList.setVisible(true);
			empList.setVisible(false);
		} else
			mainInclude.setSrc("views/employee/index.zul");
	}

	@Command
	public void empNew() {

		emp = new Employee();
		emp.setDepartment(dep);

		empList.setVisible(false);
		empNew.setVisible(true);
	}

	@Command
	@NotifyChange("emps")
	public void saveEmp() {
		emp.setDepartment(dep);
		emp.setJob_position(job);
		emp.setType(doc);

		employeeService.create(emp);

	}

	private List<Employee> reload() {

		if (emps == null)
			emps = new ArrayList<Employee>();
		if (emps.size() != 0)
			emps = employeeService.getAll();

		return emps;
	}

	public Div getEmpList() {
		return this.empList;
	}

	public void setEmpList(Div empList) {
		this.empList = empList;
	}

	public Div getEmpNew() {
		return this.empNew;
	}

	public void setEmpNew(Div empNew) {
		this.empNew = empNew;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public List<Employee> getEmps() {
		return emps;
	}

	public void setEmps(List<Employee> emps) {
		this.emps = emps;
	}

	public List<Department> getDeps() {
		return deps;
	}

	public void setDeps(List<Department> deps) {
		this.deps = deps;
	}

	public List<JobPosition> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobPosition> jobs) {
		this.jobs = jobs;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public IdentityDocumentType getDocType() {
		return docType;
	}

	public void setDocType(IdentityDocumentType docType) {
		this.docType = docType;
	}

	public List<IdentityDocumentType> getDocTypes() {
		return docTypes;
	}

	public void setDocTypes(List<IdentityDocumentType> docTypes) {
		this.docTypes = docTypes;
	}

	public JobPosition getJob() {
		return job;
	}

	public void setJob(JobPosition job) {
		this.job = job;
	}

	public IdentityDocumentType getDoc() {
		return doc;
	}

	public void setDoc(IdentityDocumentType doc) {
		this.doc = doc;
	}

}