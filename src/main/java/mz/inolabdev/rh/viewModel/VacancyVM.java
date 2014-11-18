package mz.inolabdev.rh.viewModel;

import java.util.HashMap;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.entity.SubUnit;
import mz.inolabdev.rh.entity.Vacancy;
import mz.inolabdev.rh.services.EmployeeService;
import mz.inolabdev.rh.services.JobPositionService;
import mz.inolabdev.rh.services.SubUnitService;
import mz.inolabdev.rh.services.VacancyService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class VacancyVM extends AbstractViewModel {

	private Vacancy vacancy;

	@WireVariable
	private VacancyService vacancyService;

	@WireVariable
	private JobPositionService jobPositionService;

	@WireVariable
	private SubUnitService subUnitService;

	@WireVariable
	EmployeeService employeeService;
	
	@Wire
	private Chosenbox choosenHiringMans;
	
	private ListModelList<JobPosition> jobPositions;
	private ListModelList<SubUnit> subUnits;
	private ListModelList<Employee> employeeList;
	private ListModelList<Vacancy> vacancies;

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
		vacancy = new Vacancy();
		setJobPositions(new ListModelList<JobPosition>(
				jobPositionService.getAll()));
		setSubUnits(new ListModelList<SubUnit>(subUnitService.getAll()));
		setEmployeeList(new ListModelList<Employee>(employeeService.getAll()));
		setVacancies(new ListModelList<Vacancy>(vacancyService.getAll()));
	}

	@Command
	@NotifyChange("vacancies")
	public void vacancyList() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/recruitment/vacancy/index.zul",
				target, map);
	}

	@Command
	public void newVacancy() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/recruitment/vacancy/create.zul",
				target, map);
	}
	
	@Command
	@NotifyChange({ "vacancy" })
	public void saveVacancy() {
		
		if (choosenHiringMans.getSelectedObjects().isEmpty()) {
			Clients.showNotification(
					"Please select one ore more Hiring Managers", "warning",
					choosenHiringMans, "before_center", -1);
		} else {
			for (Object object : choosenHiringMans.getSelectedObjects()) {
				vacancy.getHiringManagers().add((Employee) object);
				vacancyService.create(vacancy);
			}
		}
		vacancyList();
	}
	
	public void updateStatus(){
		
	}
	
	public Vacancy getVacancy() {
		return vacancy;
	}

	public void setVacancy(Vacancy vacancy) {
		this.vacancy = vacancy;
	}

	public ListModelList<JobPosition> getJobPositions() {
		return jobPositions;
	}

	public void setJobPositions(ListModelList<JobPosition> jobPositions) {
		this.jobPositions = jobPositions;
	}

	public ListModelList<SubUnit> getSubUnits() {
		return subUnits;
	}

	public void setSubUnits(ListModelList<SubUnit> subUnits) {
		this.subUnits = subUnits;
	}

	public ListModelList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ListModelList<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public ListModelList<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(ListModelList<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}
}
