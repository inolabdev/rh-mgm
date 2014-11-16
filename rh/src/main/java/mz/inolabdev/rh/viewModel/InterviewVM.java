package mz.inolabdev.rh.viewModel;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Interview;
import mz.inolabdev.rh.services.EmployeeService;
import mz.inolabdev.rh.services.InterviewService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModelList;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class InterviewVM extends AbstractViewModel {

	private Interview interview;

	private Employee interviewer;

	@WireVariable
	private EmployeeService employeeService;

	@WireVariable
	private InterviewService interviewService;

	@Wire
	private Chosenbox choosenHiringMans;

	private ListModelList<Employee> employeeList;
	private ListModelList<Employee> selectedEmpList;

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
		interview = new Interview();
		interviewer = new Employee();
		employeeList = new ListModelList<Employee>(employeeService.getAll());
		selectedEmpList = new ListModelList<Employee>();
	}

	@Command
	@NotifyChange({ "interview", "selectedEmpList" })
	public void saveInterview() {

		if (choosenHiringMans.getSelectedObjects().isEmpty()) {
			Clients.showNotification(
					"Please select one ore more Hiring Managers", "warning",
					choosenHiringMans, "before_center", -1);
		} else {
			for (Object object : choosenHiringMans.getSelectedObjects()) {
				interview.getInterviewers().add((Employee) object);
				interviewService.create(interview);
			}
		}
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public ListModelList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ListModelList<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public Employee getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(Employee interviewer) {
		this.interviewer = interviewer;
	}

	public ListModelList<Employee> getSelectedEmpList() {
		return selectedEmpList;
	}

	public void setSelectedEmpList(ListModelList<Employee> selectedEmpList) {
		this.selectedEmpList = selectedEmpList;
	}
}
