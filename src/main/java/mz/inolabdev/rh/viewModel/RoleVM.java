package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.services.PermissionService;
import mz.inolabdev.rh.services.RoleService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
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
public class RoleVM extends AbstractViewModel{

	private Role role;

	private List<Role> roles;

	@WireVariable
	private RoleService roleService;

	@WireVariable
	private PermissionService permissionService;

	private Set<Permission> storedPermissions;
	private List<Permission> stored;

	private Set<Permission> chosenPermissions;
	private List<Permission> chosen;

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

		stored = new ArrayList<Permission>();
		chosen = new ArrayList<Permission>();
		storedPermissions = new HashSet<Permission>();
		chosenPermissions = new HashSet<Permission>();

		roles = roleService.getAll();
		stored = permissionService.getAll();
		role = new Role();
	}

	@Command
	public void roleList() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/role/index.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Papeis");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void roleNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/role/create.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Papeis");
		links.add("Novo");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void saveRole() {

		if (chosenPermissions.size() <= 0) {

			Clients.showNotification(Labels.getLabel("role.cannot.be.saved"),
					"error", target, "before_center", -1);
		}

		else {

			role.setPermissions(chosenPermissions);
			roleService.create(role);

			Clients.showNotification(Labels.getLabel("saved.role"), "info",
					target, "before_center", -1);

			roleList();
		}
	}

	@Command
	@NotifyChange({ "stored", "chosen", "storedPermissions",
			"chosenPermissions" })
	public void selectPermission() {
		if (chosenPermissions != null && chosenPermissions.size() > 0) {
			stored.addAll(storedPermissions);
			chosen.removeAll(chosenPermissions);
			stored.addAll(chosenPermissions);
			chosenPermissions.clear();
		}
	}

	@Command
	@NotifyChange({ "stored", "chosen", "storedPermissions",
			"chosenPermissions" })
	public void deselectPermition() {
		if (storedPermissions != null && storedPermissions.size() > 0) {
			chosen.addAll(storedPermissions);
			stored.removeAll(storedPermissions);
			chosenPermissions.addAll(storedPermissions);
			storedPermissions.clear();
		}

	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Set<Permission> getStoredPermissions() {
		return storedPermissions;
	}

	public void setStoredPermissions(Set<Permission> storedPermissions) {
		this.storedPermissions = storedPermissions;
	}

	public List<Permission> getStored() {
		return stored;
	}

	public void setStored(List<Permission> stored) {
		this.stored = stored;
	}

	public Set<Permission> getChosenPermissions() {
		return chosenPermissions;
	}

	public void setChosenPermissions(Set<Permission> chosenPermissions) {
		this.chosenPermissions = chosenPermissions;
	}

	public List<Permission> getChosen() {
		return chosen;
	}

	public void setChosen(List<Permission> chosen) {
		this.chosen = chosen;
	}
}
