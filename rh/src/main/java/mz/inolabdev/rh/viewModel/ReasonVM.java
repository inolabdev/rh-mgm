package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.Reason;
import mz.inolabdev.rh.services.ReasonService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ReasonVM extends AbstractViewModel{

	private Reason reason;

	private List<Reason> reasons;

	@WireVariable
	private ReasonService reasonService;

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

		// *** Initialize ***//
		reason = new Reason();
		setReasons(reasonService.getAll());
	}

	@Command
	public void reasonList() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/reason/index.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Motivações");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void reasonNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions
				.createComponents("views/reason/create.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Motivações");
		links.add("Nova");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void saveReason() {

		reasonService.create(reason);

		Clients.showNotification("Motivação salva com sucesso!", "info", target,
				"before_center", -1);

		reasonList();
	}

	public List<Reason> getReasons() {
		return reasons;
	}

	public void setReasons(List<Reason> reasons) {
		this.reasons = reasons;
	}

	public Reason getReason() {
		return reason;
	}

	public void setReason(Reason reason) {
		this.reason = reason;
	}


}
