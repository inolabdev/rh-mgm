package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
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
public class RoleViewModel extends AbstractViewModel {

	@Wire("#mainInclude")
	private Include mainInclude;

	private Role role;

	private List<Role> roles;

	@WireVariable
	private RoleService roleService;

	@WireVariable
	private PermissionService permissionService;

	@Wire("#roleList")
	private Div roleList;

	@Wire("#roleNew")
	private Div roleNew;

	private Set<Permission> storedPermissions;
	private List<Permission> stored;

	private Set<Permission> chosenPermissions;
	private List<Permission> chosen;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		role = new Role();

		stored = new ArrayList<Permission>();
		chosen = new ArrayList<Permission>();
		storedPermissions = new HashSet<Permission>();
		chosenPermissions = new HashSet<Permission>();

		stored = permissionService.getAll();

		reload();
	}

	@Command
	@NotifyChange("mainInclude")
	public void roleList() {

		reload();

		if (roleList != null & roleNew != null) {
			roleList.setVisible(true);
			roleNew.setVisible(false);
		} else
			mainInclude.setSrc("views/role/index.zul");
	}

	@Command
	public void roleNew() {

		role = new Role();

		roleList.setVisible(false);
		roleNew.setVisible(true);
	}

	@Command
	public void saveRole() {

		if (chosenPermissions.size() <= 0) {
			Clients.showNotification(Labels.getLabel("role.cannot.be.saved"),
					"error", null, "top_right", 3000);
		} else {

			role.setPermissions(chosenPermissions);
			roleService.create(role);

			log("Registou novo papel: " + role.getRolename());

			Clients.showNotification(Labels.getLabel("saved.role"));

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

	private List<Role> reload() {

		if (roles == null)
			roles = new ArrayList<Role>();

		roles = roleService.getAll();

		return roles;
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

	public Set<Permission> getChosenPermissions() {
		return chosenPermissions;
	}

	public void setChosenPermissions(Set<Permission> chosenPermissions) {
		this.chosenPermissions = chosenPermissions;
	}

	public List<Permission> getStored() {
		return stored;
	}

	public void setStored(List<Permission> stored) {
		this.stored = stored;
	}

	public List<Permission> getChosen() {
		return chosen;
	}

	public void setChosen(List<Permission> chosen) {
		this.chosen = chosen;
	}
}
