package mz.inolabdev.rh.viewModel;

import java.util.List;

import mz.inolabdev.rh.entity.Log;
import mz.inolabdev.rh.services.LogService;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PagesFlowsViewModel extends AbstractViewModel {

	private String page;

	private String activeHome;
	private String activeMore;
	private String activeRecruitment;

	@WireVariable
	private LogService logService;

	private List<Log> logs;

	@Init
	public void init() {

		logs = logService.getAll();
		setCURRENT_PAGE_TITLE("Pagina Inicial");
		setCURRENT_PAGE_ACTION("Inicio");
		setActiveHome("active");
		setPage("dashboard.zul");
	}

	@Command
	@NotifyChange({ "page", "activeHome" })
	public void home() {
		resetMenu();
		setActiveMore("active");
		setPage("dashboard.zul");
	}

	@Command
	@NotifyChange({ "page", "activeMore" })
	public void sideBarMore() {
		resetMenu();
		setActiveMore("active");
		setPage("/views/more.zul");
	}

	@Command
	@NotifyChange({ "page", "activeRecruitment" })
	public void sideBarRecruitment() {
		resetMenu();
		setActiveRecruitment(activeRecruitment);
		setPage("/views/recruitment/recruitment.zul");

	}

	private void resetMenu() {
		setActiveHome("");
		setActiveMore("");
		setActiveRecruitment("");
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

}
