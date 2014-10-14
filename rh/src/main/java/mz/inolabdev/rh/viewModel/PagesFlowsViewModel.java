package mz.inolabdev.rh.viewModel;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PagesFlowsViewModel {

	private String page;

	private String activeHome;
	private String activeMore;

	@Init
	public void init() {
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

	private void resetMenu() {
		setActiveHome("");
		setActiveMore("");
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

}
