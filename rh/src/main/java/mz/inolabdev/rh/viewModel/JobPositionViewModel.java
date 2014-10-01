package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.JobPosition;

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
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.Messagebox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class JobPositionViewModel {

	@Wire("#mainInclude")
	private Include mainInclude;

	@Wire("#jobPositionList")
	private Div jobPositionList;

	@Wire("#jobPositionNew")
	private Div jobPositionNew;

	private JobPosition job;

	List<JobPosition> jobs;

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

		// Usar o service para gravar na base de dados
		// Depois, redicionar para index dos cargos
		// Ir buscar novamente a lista dos cargos para actualizar

		Messagebox.show(job.getDescription() + " - " + job.getType());
	}

	private List<JobPosition> reload() {

		if (jobs == null)
			jobs = new ArrayList<JobPosition>();

		jobs.clear();
		// No futuro ir buscar na base de dados e não instanciar como é feito
		// asseguir
		jobs = new ArrayList<JobPosition>();

		// Temporário(para testes)
		JobPosition j = new JobPosition();
		j.setType("nana");
		j.setDescription("nunu");

		JobPosition j2 = new JobPosition();
		j2.setType("nono");
		j2.setDescription("nini");

		jobs.add(j);
		jobs.add(j2);

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
