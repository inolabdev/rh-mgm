package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.Log;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.LogService;
import mz.inolabdev.rh.services.UserService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PagesFlowsViewModel extends AbstractViewModel {

	private String page;

	private String activeHome;
	private String activeMore;
	private String activeRecruitment;
	private String activeTimesheet;

	@WireVariable
	private LogService logService;

	@WireVariable
	private UserService userService;

	private List<Log> logs;

	private User adminUser;

	private String novaSenha;

	private String confirmacaoNovaSenha;

	private String senhaActual;
	
	private List<String> links;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		logs = logService.getAll();
		home();
	}

	@Command
	@NotifyChange({ "page", "activeHome" })
	public void home() {
		resetMenu();
		setActiveMore("active");
		setPage("dashboard.zul");
		
		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);
	}

	@Command
	@NotifyChange({ "page", "activeMore", "breadcrumb" })
	public void sideBarMore() {
		resetMenu();
		setActiveMore("active");
		setPage("/views/more.zul");

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	@NotifyChange({ "page", "activeRecruitment" })
	public void sideBarRecruitment() {
		resetMenu();
		setActiveRecruitment(activeRecruitment);
		setPage("/views/recruitment/recruitment.zul");

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-group", "Recrutamento", links);
	}

	@Command
	@NotifyChange({ "page" })
	public void userProfile() {
		resetMenu();
		setPage("/views/user/profile.zul");

		links = new ArrayList<String>();
		links.add("Perfil");
		drawnBreadcrumb("fa fa-user", "Usuário", links);
	}

	@Command
	@NotifyChange({ "page" })
	public void changePassword() {

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();

		adminUser = userService.find(auth.getName());
		senhaActual = adminUser.getPassword();
		adminUser.setPassword("");

		resetMenu();
		setPage("/views/user/change_password.zul");

		links = new ArrayList<String>();
		links.add("Mudar a senha");
		drawnBreadcrumb("fa fa-user", "Usuário", links);
	}

	@Command
	@NotifyChange({ "page", "activeTimesheet" })
	public void timesheet() {
		resetMenu();
		setActiveTimesheet(activeTimesheet);
		setPage("/views/times/timesheet/index.zul");
		
		links = new ArrayList<String>();
		links.add("TimeSheets");
		links.add("Inicio");
		
		drawnBreadcrumb("fa fa-desktop", "Horas", links);

	}

	@Command
	@NotifyChange({ "page", "activeTimesheet" })
	public void projects() {
		resetMenu();
		setActiveTimesheet(activeTimesheet);
		setPage("/views/times/projects/index.zul");

		links = new ArrayList<String>();
		links.add("Projectos");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-desktop", "Horas", links);
	}

	@Command
	@NotifyChange({ "page", "activeHome" })
	public void saveChanges() {
		if (novaSenha == null || confirmacaoNovaSenha == null) {
			Messagebox.show(Labels.getLabel("user.reset.password.empty"),
					"Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			if (novaSenha.trim().length() < 8) {
				Messagebox.show(Labels.getLabel("user.reset.password.length"),
						"Error", Messagebox.OK, Messagebox.ERROR);
			} else {
				if (!novaSenha.equals(confirmacaoNovaSenha)) {
					Messagebox
							.show(Labels
									.getLabel("user.reset.password.confirmation"),
									"Error", Messagebox.OK, Messagebox.ERROR);
				} else {
					if (novaSenha.equals(senhaActual)) {
						Messagebox.show(Labels
								.getLabel("user.reset.password.same.pass"),
								"Error", Messagebox.OK, Messagebox.ERROR);
					} else {
						if (!adminUser.getPassword().equals(senhaActual)) {
							Messagebox.show(
									Labels.getLabel("user.password.failed"),
									"Error", Messagebox.OK, Messagebox.ERROR);
						} else {
							adminUser.setPassword(novaSenha);
							userService.update(adminUser);
							Messagebox.show(
									Labels.getLabel("user.changed.password"),
									"Error", Messagebox.OK,
									Messagebox.INFORMATION);
							reset();
							home();
						}
					}
				}
			}
		}
	}

	private void resetMenu() {
		setActiveHome("");
		setActiveMore("");
		setActiveRecruitment("");
		setActiveTimesheet("");
	}

	private void reset() {
		setNovaSenha("");
		setConfirmacaoNovaSenha("");
		adminUser = new User();
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getActiveHome() {
		return activeHome;
	}

	public void setActiveHome(String activeHome) {
		this.activeHome = activeHome;
	}

	public String getActiveMore() {
		return activeMore;
	}

	public void setActiveMore(String activeMore) {
		this.activeMore = activeMore;
	}

	public List<Log> getLogs() {
		return logs;
	}

	public void setLogs(List<Log> logs) {
		this.logs = logs;
	}

	public String getActiveRecruitment() {
		return activeRecruitment;
	}

	public void setActiveRecruitment(String activeRecruitment) {
		this.activeRecruitment = activeRecruitment;
	}

	public User getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(User adminUser) {
		this.adminUser = adminUser;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoNovaSenha() {
		return confirmacaoNovaSenha;
	}

	public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha) {
		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
	}

	public String getActiveTimesheet() {
		return activeTimesheet;
	}

	public void setActiveTimesheet(String activeTimesheet) {
		this.activeTimesheet = activeTimesheet;
	}

}
