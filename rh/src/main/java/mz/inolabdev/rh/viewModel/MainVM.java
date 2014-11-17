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
import org.zkoss.bind.annotation.NotifyChange;
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

	// SideBar Menus

	private String initPage;

	private String hoursPage;

	private String recruitPage;

	private String perfomPage;

	private String leavePage;

	private String trainPage;

	private String recPage;

	private String aproovPage;

	private String morePage;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view)
			throws IOException {

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
		
		menuReset();
		setInitPage("active");
	}

	@Command
	@NotifyChange("*")
	public void home() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);
		
		menuReset();
		setInitPage("active");
	}

	@Command
	@NotifyChange("*")
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
		
		menuReset();
		setHoursPage("active");
	}

	@Command
	@NotifyChange("*")
	public void sideBarMore() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/more.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
		
		menuReset();
		setMorePage("active");
	}

	@Command
	@NotifyChange("*")
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
		
		menuReset();
		setRecruitPage("active");
	}

	@Command
	@NotifyChange("*")
	public void userProfile() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/user/profile.zul", target, map);

		links = new ArrayList<String>();
		links.add("Perfil");
		drawnBreadcrumb("fa fa-user", "Utilizador", links);
		
		menuReset();
		setInitPage("active");
	}

	@Command
	@NotifyChange("*")
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
		
		menuReset();
		setInitPage("active");
	}

	@Command
	@NotifyChange("*")
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
		
		menuReset();
		setHoursPage("active");
	}

	@Command
	@NotifyChange("*")
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
		
		menuReset();
		setLeavePage("active");
	}

	@Command
	@NotifyChange("*")
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
		
		menuReset();
		setLeavePage("active");
	}

	@Command
	@NotifyChange("*")
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
		
		menuReset();
		setLeavePage("active");
	}

	@Command
	@NotifyChange("*")
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
		
		menuReset();
		setLeavePage("active");
	}

	private void menuReset() {

		setInitPage("");
		setHoursPage("");
		setRecruitPage("");
		setRecPage("");
		setPerfomPage("");
		setLeavePage("");
		setTrainPage("");
		setAproovPage("");
		setMorePage("");
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

	public String getInitPage() {
		return initPage;
	}

	public void setInitPage(String initPage) {
		this.initPage = initPage;
	}

	public String getHoursPage() {
		return hoursPage;
	}

	public void setHoursPage(String hoursPage) {
		this.hoursPage = hoursPage;
	}

	public String getRecruitPage() {
		return recruitPage;
	}

	public void setRecruitPage(String recruitPage) {
		this.recruitPage = recruitPage;
	}

	public String getPerfomPage() {
		return perfomPage;
	}

	public void setPerfomPage(String perfomPage) {
		this.perfomPage = perfomPage;
	}

	public String getLeavePage() {
		return leavePage;
	}

	public void setLeavePage(String leavePage) {
		this.leavePage = leavePage;
	}

	public String getTrainPage() {
		return trainPage;
	}

	public void setTrainPage(String trainPage) {
		this.trainPage = trainPage;
	}

	public String getRecPage() {
		return recPage;
	}

	public void setRecPage(String recPage) {
		this.recPage = recPage;
	}

	public String getAproovPage() {
		return aproovPage;
	}

	public void setAproovPage(String aproovPage) {
		this.aproovPage = aproovPage;
	}

	public String getMorePage() {
		return morePage;
	}

	public void setMorePage(String morePage) {
		this.morePage = morePage;
	}
}
