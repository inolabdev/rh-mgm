package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.Log;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.LogService;
import mz.inolabdev.rh.services.UserService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MainVM extends AbstractViewModel {

	@Wire("#mainlayout")
	private Div target;

	@Wire("#breadcrumb")
	private Ol ol;

	@WireVariable
	private LogService logService;

	@WireVariable
	private UserService userService;

	private List<Log> logs;

	private User user;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) {

		final HashMap<String, Object> map = new HashMap<String, Object>();

		Selectors.wireComponents(view, this, false);

		target.getChildren().clear();

		map.put("target", target);
		map.put("breadcrumb", ol);
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);

	}

	@Init
	public void init() {

		// *** Initialize ***//
		logs = logService.getAll();

		String userName = Executions.getCurrent().getUserPrincipal().getName();

		user = userService.find(userName);
	}

	@Command
	public void home() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);
	}

	@Command
	public void projectList() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/times/projects/index.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Projectos");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-desktop", "Horas", links);
	}

	@Command
	public void sideBarMore() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/more.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void sideBarRecruitment() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/recruitment/recruitment.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-group", "Recrutamento", links);
	}

	@Command
	public void userProfile() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/user/profile.zul", target, map);

		links = new ArrayList<String>();
		links.add("Perfil");
		drawnBreadcrumb("fa fa-user", "Utilizador", links);
	}

	@Command
	public void changePassword() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/user/change_password.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Mudar a senha");
		drawnBreadcrumb("fa fa-user", "Utilizador", links);
	}

	@Command
	public void timesheet() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/times/timesheet/index.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Timesheets");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-desktop", "Horas", links);
	}

	public List<Log> getLogs() {
		return logs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
