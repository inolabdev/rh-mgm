package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.Department;
import mz.inolabdev.rh.services.DepartamentService;

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
public class DepartamentVM extends AbstractViewModel{

	private Div target;

	private List<Department> deps;

	private Department dep;

	@WireVariable
	private DepartamentService departamentService;

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
		deps = departamentService.getAll();
		dep = new Department();
	}

	@Command
	public void departamentList() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/departament/index.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Departamentos");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void departamentNew() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions
				.createComponents("views/departament/create.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Departamentos");
		links.add("Novo");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void saveDep() {

		departamentService.create(dep);

		Clients.showNotification("Departamento registado com sucesso!", "info",
				target, "before_center", -1);
		departamentList();
	}

	public List<Department> getDeps() {
		return deps;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}
}
