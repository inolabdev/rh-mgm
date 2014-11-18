package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.services.PermissionService;

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
public class PermissionVM extends AbstractViewModel {

	private Permission perm;

	private List<Permission> perms;

	@WireVariable
	private PermissionService permissionService;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("target") Div target,
			@ExecutionArgParam("breadcrumb") Ol ol) {

		Selectors.wireComponents(view, this, false);

		this.target = target;
		this.ol = ol;

		perm = new Permission();
	}

	@Init
	public void init() {

		perms = permissionService.getAll();
	}

	@Command
	public void permissionList() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/permission/index.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Permissões");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void permissionNew() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/permission/create.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Permissões");
		links.add("Nova");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}
	
	@Command
	public void savePerm() {

		permissionService.create(perm);
		
		Clients.showNotification(Labels.getLabel("saved.permission"), "info",
				target, "before_center", -1);
		
		permissionList();
	}

	public Permission getPerm() {
		return perm;
	}

	public void setPerm(Permission perm) {
		this.perm = perm;
	}

	public List<Permission> getPerms() {
		return perms;
	}

	public void setPerms(List<Permission> perms) {
		this.perms = perms;
	}

}
