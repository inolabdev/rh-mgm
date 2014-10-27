package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.EmployeeService;
import mz.inolabdev.rh.services.RoleService;
import mz.inolabdev.rh.services.UserService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AdminUserViewModel extends AbstractViewModel {

	@Wire("#mainInclude")
	private Include mainInclude;

	@Wire("#userList")
	private Div userList;

	@Wire("#userNew")
	private Div userNew;

	@Wire("#lblUser")
	private Label lblUser;

	@Wire("#divSave")
	private Div divSave;
	
	@Wire("#senha")
	private Textbox senha;
	
	@Wire("#pass")
	private Textbox pass;

	private User adminUser;

	private List<User> adminUsers;

	@WireVariable
	private UserService userService;

	@WireVariable
	private RoleService roleService;
	
	@WireVariable
	private EmployeeService employeeService;

	private Set<Role> storedRoles;
	private List<Role> stored;

	private Set<Role> chosenRoles;
	private List<Role> chosen;
	
	private List<Employee> employeeList;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		adminUser = new User();

		stored = new ArrayList<Role>();
		chosen = new ArrayList<Role>();
		storedRoles = new HashSet<Role>();
		chosenRoles = new HashSet<Role>();

		stored = roleService.getAll();
		
		employeeList = employeeService.allWhereUserIdIsNull();

		reload();
	}

	@Command
	@NotifyChange("mainInclude")
	public void userList() {

		if (userList != null & userNew != null) {
			userList.setVisible(true);
			userNew.setVisible(false);
		} else
			mainInclude.setSrc("views/user/index.zul");
	}

	@Command
	public void userNew() {

		adminUser = new User();

		userList.setVisible(false);
		userNew.setVisible(true);
	}

	@Command
	@NotifyChange("adminUsers")
	public void saveUser() {

		if (chosenRoles.size() <= 0) {
			Clients.showNotification(Labels.getLabel("role.cannot.be.saved"),
					"error", null, "top_right", 3000);
		} else {

			adminUser.setRoles(chosenRoles);
			adminUser.setEnabled(true);
			userService.create(adminUser);

			log("Registou novo usuário: " + adminUser.getUsername());

			Clients.showNotification(Labels.getLabel("saved.user"));

			userList();
		}
	}

	@Command
	@NotifyChange({ "stored", "chosen", "storedRoles", "chosenRoles" })
	public void selectRole() {
		if (chosenRoles != null && chosenRoles.size() > 0) {
			stored.addAll(storedRoles);
			chosen.removeAll(chosenRoles);
			stored.addAll(chosenRoles);
			chosenRoles.clear();
		}
	}

	@Command
	@NotifyChange({ "stored", "chosen", "storedRoles", "chosenRoles" })
	public void deselectRole() {
		if (storedRoles != null && storedRoles.size() > 0) {
			chosen.addAll(storedRoles);
			stored.removeAll(storedRoles);
			chosenRoles.addAll(storedRoles);
			storedRoles.clear();
		}
	}

	@Command
	public void checkIfAvalaible(@BindingParam("usuario") String usuario) {

		User tempUser = userService.find(usuario);

		if (tempUser == null) {
			lblUser.setValue("Disponível");
			lblUser.setSclass("label label-success");
			divSave.setVisible(true);
			pass.setDisabled(false);
			senha.setDisabled(false);
		} else {
			lblUser.setValue("Usuário ja cadastrado");
			lblUser.setSclass("label label-danger");
			divSave.setVisible(false);
			pass.setDisabled(true);
			senha.setDisabled(true);
		}
	}

	private void reload() {

		if (adminUsers == null)
			adminUsers = new ArrayList<User>();

		adminUsers = userService.getAll();

	}

	public User getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(User adminUser) {
		this.adminUser = adminUser;
	}

	public List<User> getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(List<User> adminUsers) {
		this.adminUsers = adminUsers;
	}

	public Set<Role> getStoredRoles() {
		return storedRoles;
	}

	public void setStoredRoles(Set<Role> storedRoles) {
		this.storedRoles = storedRoles;
	}

	public Set<Role> getChosenRoles() {
		return chosenRoles;
	}

	public void setChosenRoles(Set<Role> chosenRoles) {
		this.chosenRoles = chosenRoles;
	}

	public List<Role> getStored() {
		return stored;
	}

	public void setStored(List<Role> stored) {
		this.stored = stored;
	}

	public List<Role> getChosen() {
		return chosen;
	}

	public void setChosen(List<Role> chosen) {
		this.chosen = chosen;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
}
