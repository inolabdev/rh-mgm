package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.services.PermissionService;

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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class PermitionViewModel extends AbstractViewModel{

	@Wire("#mainInclude")
	private Include mainInclude;
	
	@Wire("#permissionList")
	private Div permissionList;

	@Wire("#permissionNew")
	private Div permissionNew;
	
	private Permission perm;
	
	private List<Permission> perms;
	
	@WireVariable
	private PermissionService permissionService;
	
	@AfterCompose
    public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
        Selectors.wireComponents(view, this, false);
    }

	@Init
	public void init() {
		
		perm = new Permission();
		
		reload();
	}

	@Command
	@NotifyChange("perms")
	public void permissionList() {

		reload();

		if (permissionList != null & permissionNew != null) {
			permissionList.setVisible(true);
			permissionNew.setVisible(false);
		} else
			mainInclude.setSrc("views/permission/index.zul");
	}

	@Command
	public void permissionNew() {

		perm = new Permission();

		permissionList.setVisible(false);
		permissionNew.setVisible(true);
	}
	
	@Command
	@NotifyChange("perms")
	public void savePerm() {

		permissionService.create(perm);

		log("Registou nova permissão: " + perm.getPermissionname());
		
		Clients.showNotification(Labels.getLabel("saved.permission"));
		
		permissionList();
	}
	
	private List<Permission> reload() {

		if (perms == null)
			perms = new ArrayList<Permission>();

		perms = permissionService.getAll();

		return perms;
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
