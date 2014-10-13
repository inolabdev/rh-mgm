package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.Department;
import mz.inolabdev.rh.services.DepartamentService;

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
public class DepartamentViewModel {
	
	@Wire("#mainInclude")
	private Include mainInclude;
	
	@Wire("#depList")
	private Div depList;

	@Wire("#depNew")
	private Div depNew;
	
	@WireVariable
	private DepartamentService departamentService;
	
	private List<Department> deps;
	
	private Department dep;
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }

	@Init
	public void init() {
		
		setDep(new Department());
		reload();
	}

	@Command
	@NotifyChange("mainInclude")
	public void departamentList() {
		mainInclude.setSrc("views/departament/index.zul");
	}
	
	@Command
	@NotifyChange("deps")
	public void depList() {

		reload();

		if (depList != null & depNew != null) {
			depList.setVisible(true);
			depNew.setVisible(false);
		} else
			mainInclude.setSrc("views/departament/index.zul");
	}

	@Command
	public void depNew() {
		
		dep = new Department();
		
		depList.setVisible(false);
		depNew.setVisible(true);
	}
	
	private List<Department> reload() {

		if (deps == null)
			deps = new ArrayList<Department>();

		deps = departamentService.getAll();

		return deps;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public List<Department> getDeps() {
		return deps;
	}

	public void setDeps(List<Department> deps) {
		this.deps = deps;
	}
}
