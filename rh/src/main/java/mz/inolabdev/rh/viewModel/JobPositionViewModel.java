package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.services.JobPositionService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class JobPositionViewModel extends AbstractViewModel {

	@Wire("#mainInclude")
	private Include mainInclude;

	@Wire("#jobPositionList")
	private Div jobPositionList;

	@Wire("#jobPositionNew")
	private Div jobPositionNew;

	private JobPosition job;

	List<JobPosition> jobs;

	@WireVariable
	private JobPositionService jobPositionService;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		job = new JobPosition();

		reload();
	}

	@Command
	@NotifyChange("jobs")
	public void jobPositionList() {

		reload();

		if (jobPositionList != null & jobPositionNew != null) {
			jobPositionList.setVisible(true);
			jobPositionNew.setVisible(false);
		} else
			mainInclude.setSrc("views/jobPosition/index.zul");
	}

	@Command
	public void jobPositionNew() {

		job = new JobPosition();

		jobPositionList.setVisible(false);
		jobPositionNew.setVisible(true);
	}

	@Command
	@NotifyChange("jobs")
	public void saveJob() {

		jobPositionService.create(job);

		log("Registou novo cargo: " + job.getType());
		jobPositionList();
	}

	private List<JobPosition> reload() {

		if (jobs == null)
			jobs = new ArrayList<JobPosition>();

		jobs = jobPositionService.getAll();

		return jobs;
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

}
