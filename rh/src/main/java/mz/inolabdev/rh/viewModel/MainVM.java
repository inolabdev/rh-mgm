package mz.inolabdev.rh.viewModel;

import java.io.IOException;
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
import org.zkoss.image.AImage;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;

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

	@Wire
	private Image imgPfl;

	@Wire("#sidebar #imgPflSide")
	private Image imgPflSide;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view) throws IOException {

		final HashMap<String, Object> map = new HashMap<String, Object>();

		Selectors.wireComponents(view, this, false);

		target.getChildren().clear();

		map.put("target", target);
		map.put("breadcrumb", ol);
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);
		
		if (target != null) {

			mz.inolabdev.rh.entity.Image image = user.getUserProfile()
					.getImageProfile();

			if (image == null) {

				imgPfl.setSrc("img/default_image.png");
				imgPflSide.setSrc("img/default_image.png");
			}

			else {

				org.zkoss.image.Image img = new AImage(image.getFileName(),
						image.getContent());

				imgPfl.setContent(img);
				imgPflSide.setContent(img);
			}
			
			imgPflSide.invalidate();
		}

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
		drawnBreadcrumb("fa fa-retweet", "Recrutamento", links);
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

	@Command
	public void requestAbsence() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/absence/absence_request.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Licença");
		links.add("Nova");
		drawnBreadcrumb("fa fa-plane", "Gestão de Ausências", links);
	}

	@Command
	public void cancelAbsence() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/absence/cancel_absence.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Licença");
		links.add("Cancelar");
		drawnBreadcrumb("fa fa-plane", "Gestão de Ausências", links);
	}

	@Command
	public void absencesStatus() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/absence/absence_status.zul", target,
				map);

		links = new ArrayList<String>();
		links.add("Licença");
		links.add("Status");
		drawnBreadcrumb("fa fa-plane", "Gestão de Ausências", links);
	}

	@Command
	public void holidaysCalendar() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/absence/holidays_calendar.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Calendário de Feriados");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-plane", "Gestão de Ausências", links);
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
