package mz.inolabdev.rh.viewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Ol;
import org.zkoss.zhtml.Table;
import org.zkoss.zhtml.Td;
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
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

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

		week = generateWeek();
		drawnTimesheetTable();
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

	@Command
	public void saveTimisheet() {

		int size = tblTimesheet.getChildren().size();
		Project pro;

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
					}

				} else if (j == 1) {

					Listbox selectedAct = (Listbox) td.getChildren().get(0);

					if (selectedAct.getSelectedItem() == null)
						Clients.showNotification(
								"Selecione uma actividade antes de submeter",
								"warning", tblTimesheet, "before_center", -1);

					else {
						Activity act = selectedAct.getSelectedItem().getValue();
					}

				} else if (j > 1) {

					Map<String, Integer> actDayHour = new HashMap<String, Integer>();

					Intbox ibx = (Intbox) td.getChildren().get(0);

					actDayHour.put(week.get(j - 2), ibx.getValue());
				}

			}

		}

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
	public void clearFiels() {

		int size = tblTimesheet.getChildren().size();

		for (int i = 1; i < size; i++) {

			Component cp = null;
			
			try {
				
				cp = tblTimesheet.getChildren().get(i);

			} catch (IndexOutOfBoundsException iob) {
				
				cp = tblTimesheet.getChildren().get(i-1);

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
		lbxPro.setModel(new ListModelList<Project>(projectService.getAll()));
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
