package mz.inolabdev.rh.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import mz.inolabdev.rh.entity.Activity;
import mz.inolabdev.rh.entity.DateHours;
import mz.inolabdev.rh.entity.Project;
import mz.inolabdev.rh.entity.TimeSheet;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.ProjectService;
import mz.inolabdev.rh.services.TimesheetService;
import mz.inolabdev.rh.services.UserService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Label;
import org.zkoss.zhtml.Ol;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
import org.zkoss.zhtml.Text;
import org.zkoss.zhtml.Th;
import org.zkoss.zhtml.Tr;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class TimeSheetVM extends AbstractViewModel {

	private Locale ptBr = new Locale("pt", "BR");

	@Wire
	private Listbox lbxProjects;

	@Wire
	private Listbox lbxActivities;

	@Wire
	private Listbox lbxTimesheet;

	@Wire
	private Table tblTimesheet;

	@Wire
	private Div operationsGroup;

	@WireVariable
	private ProjectService projectService;

	@WireVariable
	private TimesheetService timesheetService;

	@WireVariable
	private UserService userService;

	private User currentUser;

	private List<String> week;

	private Button btnSubmit;

	private Button btnNewLine;

	private Button btnEdit;

	private Button btnClear;

	private List<Date> dates;

	private int weekNum;

	private TimeSheet subTimesheet;
	
	@Wire
	private Label lblStatus;
	
	@Wire
	private Div divTimesheet;

	private Window window;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("target") Div target,
			@ExecutionArgParam("breadcrumb") Ol ol) {

		Selectors.wireComponents(view, this, false);

		this.target = target;
		this.ol = ol;

		btnSubmit = (Button) operationsGroup.getChildren().get(0);
		btnNewLine = (Button) operationsGroup.getChildren().get(1);
		btnEdit = (Button) operationsGroup.getChildren().get(2);
		btnClear = (Button) operationsGroup.getChildren().get(3);

		dates = currentWeek();

		String name = Executions.getCurrent().getUserPrincipal().getName();
		currentUser = userService.find(name);

		weekNum = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);

		subTimesheet = timesheetService.findByWeekAndEmployee(weekNum,
				currentUser.getUserProfile());

		if (subTimesheet == null) {

			week = generateWeek();

			drawnTimesheetTable();

		}

		else {

			drawnCurrentTimesheetTable(subTimesheet);

			btnSubmit.setDisabled(true);
			btnNewLine.setDisabled(true);
			btnClear.setDisabled(true);
			btnEdit.setDisabled(false);

		}

	}

	@Init
	public void init() {

		if (subTimesheet == null) {

			week = generateWeek();
		}

	}

	@Command
	public void findProjectActibities(@BindingParam("project") Project pro) {

		Project project = projectService.find(pro.getId());

		lbxActivities.setModel(new ListModelList<Activity>(project
				.getActivities()));

	}
	
	@Command
    public void showModal() {
        window = (Window)Executions.createComponents(
                "/views/times/timesheet/confirm.zul", divTimesheet, null);
        window.doModal();
    }

	@Command
	public void saveTimisheet() {

		int size = tblTimesheet.getChildren().size();
		Project pro = null;
		Activity act = null;
		TimeSheet timeSheet = null;

		timeSheet = new TimeSheet();
		timeSheet.setEmployee(currentUser.getUserProfile());
		timeSheet.setStatus("Submetido");
		timeSheet.setWeek(Calendar.getInstance().get(Calendar.WEEK_OF_YEAR));

		for (int i = 1; i < size; i++) {

			Tr tr = (Tr) tblTimesheet.getChildren().get(i);

			int trTds = tr.getChildren().size();

			for (int j = 0; j < trTds; j++) {

				Td td = (Td) tr.getChildren().get(j);

				if (j == 0) {

					Listbox selectedProj = (Listbox) td.getChildren().get(0);

					if (selectedProj.getSelectedItem() == null)
						Clients.showNotification(
								"Selecione um projecto antes de submeter",
								"warning", tblTimesheet, "before_center", -1);

					else {
						pro = selectedProj.getSelectedItem().getValue();
						selectedProj.setDisabled(true);
						timeSheet.getProjects().add(pro);
					}

				} else if (j == 1) {

					Listbox selectedAct = (Listbox) td.getChildren().get(0);

					if (selectedAct.getSelectedItem() == null)
						Clients.showNotification(
								"Selecione uma actividade antes de submeter",
								"warning", tblTimesheet, "before_center", -1);

					else {
						act = selectedAct.getSelectedItem().getValue();
						selectedAct.setDisabled(true);
						timeSheet.getActivities().add(act);
					}

				} else if (j > 1) {

					Intbox ibx = (Intbox) td.getChildren().get(0);
					ibx.setDisabled(true);

					DateHours dh = new DateHours();
					dh.setDay(dates.get(j - 2));
					dh.setHours(ibx.getValue());

					timeSheet.getDateHours().put(
							weekNum + "" + pro.getId() + "" + act.getId() + ""
									+ (j - 2), dh);

				}
			}
		}

		timesheetService.create(timeSheet);
		window.detach();

		Clients.showNotification("Timesheet submetido com sucesso!", "info",
				tblTimesheet, "before_center", -1);

		btnNewLine.setDisabled(true);
		btnClear.setDisabled(true);
		btnEdit.setDisabled(false);

	}

	@Command
	public void newLine() {

		drawnTimesheetTable();
	}

	@Command
	public void removeLine() {

		tblTimesheet.getChildren().clear();
	}

	@Command
	public void editTimisheet() {

		btnSubmit.setLabel("Actualizar");
		btnSubmit.setDisabled(false);

		operationsGroup.invalidate();

		int size = tblTimesheet.getChildren().size();

		for (int i = 1; i < size; i++) {

			Tr tr = (Tr) tblTimesheet.getChildren().get(i);

			int trTds = tr.getChildren().size();

			for (int j = 0; j < trTds; j++) {

				Td td = (Td) tr.getChildren().get(j);

				if (j == 0) {

					Listbox selectedProj = (Listbox) td.getChildren().get(0);
					selectedProj.setDisabled(false);

				} else if (j == 1) {

					Listbox selectedAct = (Listbox) td.getChildren().get(0);
					selectedAct.setDisabled(false);

				} else if (j > 1) {

					Intbox ibx = (Intbox) td.getChildren().get(0);
					ibx.setDisabled(false);

				}
			}
		}
	}

	@Command
	public void clearFiels() {

		int size = tblTimesheet.getChildren().size();

		for (int i = 1; i < size; i++) {

			Component cp = null;

			try {

				cp = tblTimesheet.getChildren().get(i);

			} catch (IndexOutOfBoundsException iob) {

				cp = tblTimesheet.getChildren().get(i - 1);

			} finally {

				cp.getChildren().clear();
			}
		}

		drawnTimesheetTable();
	}

	@Command
	public void pending() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();

		links = new ArrayList<String>();
		links.add("Timesheets");
		links.add("Pendentes");
		drawnBreadcrumb("fa fa-desktop", "Horas", links);

		Executions.createComponents("views/times/timesheet/pending.zul",
				target, map);
	}

	@Command
	public void aprooved() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();

		links = new ArrayList<String>();
		links.add("Timesheets");
		links.add("Aprovados");
		drawnBreadcrumb("fa fa-desktop", "Horas", links);

		Executions.createComponents("views/times/timesheet/aprooved.zul",
				target, map);
	}

	@Command
	public void reprooved() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();

		links = new ArrayList<String>();
		links.add("Timesheets");
		links.add("Reprovados");
		drawnBreadcrumb("fa fa-desktop", "Horas", links);

		Executions.createComponents("views/times/timesheet/reprooved.zul",
				target, map);
	}

	@Command
	public void current() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();

		links = new ArrayList<String>();
		links.add("Timesheets");
		links.add("Actual");
		drawnBreadcrumb("fa fa-desktop", "Horas", links);

		Executions.createComponents("views/times/timesheet/index.zul", target,
				map);
	}

	private void drawnTimesheetTable() {

		Tr tr = new Tr();
		tr.setParent(tblTimesheet);

		Td tdpro = new Td();
		tdpro.setParent(tr);

		final Listbox lbxPro = new Listbox();
		lbxPro.setModel(new ListModelList<Project>(projectService
				.projectsByEmployee(currentUser.getUserProfile())));
		lbxPro.setMold("select");
		lbxPro.setSclass("form-control inoLabFont chzn-select");
		lbxPro.setParent(tdpro);

		Td tdact = new Td();
		tdact.setParent(tr);

		final Listbox lbxAct = new Listbox();
		lbxAct.setMold("select");
		lbxAct.setSclass("form-control inoLabFont");
		lbxAct.setParent(tdact);

		lbxPro.addEventListener(Events.ON_SELECT, new EventListener<Event>() {
			public void onEvent(Event event) {

				Project pro = lbxPro.getSelectedItem().getValue();
				lbxAct.setModel(new ListModelList<Activity>(pro.getActivities()));
			}
		});

		for (int i = 0; i < 7; i++) {

			Td tdHi = new Td();
			tdHi.setParent(tr);

			Intbox ibx = new Intbox();
			ibx.setParent(tdHi);
			ibx.setSclass("form-control inoLabFont");
			ibx.setValue(0);
			ibx.setConstraint("no empty: Não pode ser vazio!");
			ibx.setMaxlength(1);
		}

		target.invalidate();
	}

	private void drawnCurrentTimesheetTable(TimeSheet timesheet) {

		SimpleDateFormat sdf = new SimpleDateFormat("EEE dd", ptBr);

		tblTimesheet.getChildren().clear();

		Tr tr = new Tr();
		tr.setParent(tblTimesheet);

		Th th01 = new Th();
		th01.setStyle("width: 28%");
		th01.setParent(tr);

		Text txt01 = new Text("Nome do Projecto");
		txt01.setParent(th01);

		Th th02 = new Th();
		th02.setStyle("width: 23%");
		th02.setParent(tr);

		Text txt02 = new Text("Actividade");
		txt02.setParent(th02);

		Hashtable<String, DateHours> var = timesheet.getDateHours();

		List<Activity> acts = timesheet.getActivities();

		List<Project> projs = new LinkedList<Project>();

		for (int i = 0; i < acts.size(); i++) {

			projs.add(acts.get(i).getProject());
		}

		for (int i = 0; i < 7; i++) {

			String day;

			Th th03 = new Th();
			th03.setStyle("width: 7%");
			th03.setParent(tr);

			day = sdf.format(var.get(
					timesheet.getWeek() + "" + projs.get(0).getId() + ""
							+ acts.get(0).getId() + "" + i).getDay());

			Text txt03 = new Text(day);
			txt03.setParent(th03);
		}

		int size = acts.size();

		for (int i = 0; i < size; i++) {

			Tr tr01 = new Tr();
			tr01.setParent(tblTimesheet);

			Td td01 = new Td();
			td01.setParent(tr01);

			final Listbox lbxP = new Listbox();
			lbxP.setModel(new ListModelList<Project>(projectService
					.projectsByEmployee(currentUser.getUserProfile())));
			lbxP.setMold("select");

			for (int j = 0; j < lbxP.getModel().getSize(); j++) {

				if (((Project) lbxP.getModel().getElementAt(j)).getId().equals(
						projs.get(i).getId())) {

					lbxP.setSelectedIndex(j);
					break;

				}
			}

			lbxP.setSclass("form-control inoLabFont chzn-select");
			lbxP.setDisabled(true);
			lbxP.setParent(td01);

			Td tdact = new Td();
			tdact.setParent(tr01);

			final Listbox lbxAct = new Listbox();
			lbxAct.setModel(new ListModelList<Activity>(projs.get(i)
					.getActivities()));
			lbxAct.setMold("select");

			for (int j = 0; j < lbxAct.getModel().getSize(); j++) {

				if (((Activity) lbxAct.getModel().getElementAt(j)).getId()
						.equals(acts.get(i).getId())) {

					lbxAct.setSelectedIndex(j);
					break;

				}
			}

			lbxAct.setSclass("form-control inoLabFont");
			lbxAct.setDisabled(true);
			lbxAct.setParent(tdact);

			lbxP.addEventListener(Events.ON_SELECT, new EventListener<Event>() {
				public void onEvent(Event event) {

					Project pro = lbxP.getSelectedItem().getValue();
					lbxAct.setModel(new ListModelList<Activity>(pro
							.getActivities()));
				}
			});

			for (int j = 0; j < 7; j++) {

				Td tdHi = new Td();
				tdHi.setParent(tr01);

				Intbox ibx = new Intbox();
				ibx.setParent(tdHi);
				ibx.setSclass("form-control inoLabFont");

				ibx.setValue(var.get(
						weekNum + "" + acts.get(i).getProject().getId() + ""
								+ acts.get(i).getId() + "" + i).getHours());

				ibx.setMaxlength(1);
				ibx.setDisabled(true);
			}
		}

		tblTimesheet.invalidate();
		lblStatus.getChildren().clear();
		Text text = new Text(timesheet.getStatus());
		text.setParent(lblStatus);
		lblStatus.invalidate();
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

	private List<Date> currentWeek() {

		Calendar cal = Calendar.getInstance();

		List<Date> days = new ArrayList<Date>();
		int delta = -cal.get(GregorianCalendar.DAY_OF_WEEK) + 2;
		cal.add(Calendar.DAY_OF_MONTH, delta);
		for (int i = 0; i < 7; i++) {
			days.add(cal.getTime());
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

	public TimeSheet getSubTimesheet() {
		return subTimesheet;
	}

	public void setSubTimesheet(TimeSheet subTimesheet) {
		this.subTimesheet = subTimesheet;
	}

}
