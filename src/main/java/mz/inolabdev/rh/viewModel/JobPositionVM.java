package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.services.JobPositionService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class JobPositionVM extends AbstractViewModel{

	private JobPosition job;

	private List<JobPosition> jobs;

	@WireVariable
	private JobPositionService jobPositionService;

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
		job = new JobPosition();
		jobs = jobPositionService.getAll();
	}

	@Command
	public void jobPositionList() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/jobPosition/index.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Cargos");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void jobPositionNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions
				.createComponents("views/jobPosition/create.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Cargos");
		links.add("Novo");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void saveJob() {

		jobPositionService.create(job);

		Clients.showNotification(Labels.getLabel("saved.job"), "info", target,
				"before_center", -1);

		jobPositionList();
	}

	public JobPosition getJob() {
		return job;
	}

	public void setJob(JobPosition job) {
		this.job = job;
	}

	public List<JobPosition> getJobs() {
		return jobs;
	}

	public void setJobs(List<JobPosition> jobs) {
		this.jobs = jobs;
	}
}
