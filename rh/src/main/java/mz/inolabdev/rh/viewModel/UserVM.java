package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Button;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UserVM extends AbstractViewModel {

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

	@Wire
	private Textbox txbUser;

	@Wire
	private Textbox pass;

	@Wire
	private Textbox senha;

	@Wire
	private Label lblUser;

	@Wire
	private Label lblSenha;

	@Wire
	private Label lblConf;

	@Wire
	private Button btnSave;

	private Set<String> erros = new HashSet<String>();

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

		adminUser = new User();

		stored = new ArrayList<Role>();
		chosen = new ArrayList<Role>();
		storedRoles = new HashSet<Role>();
		chosenRoles = new HashSet<Role>();

		stored = roleService.getAll();

		employeeList = employeeService.allWhereUserIdIsNull();

		adminUsers = userService.getAll();
	}

	@Command
	public void userList() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/user/index.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		drawnBreadcrumb("fa fa-user", "Utilizador", links);
	}

	@Command
	public void userNew() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/user/create.zul", target, map);

		links = new ArrayList<String>();
		links.add("Novo");
		drawnBreadcrumb("fa fa-user", "Utilizador", links);
	}

	@Command
	public void saveUser() {

		if (chosenRoles.size() <= 0) {
			Clients.showNotification(Labels.getLabel("role.cannot.be.saved"),
					"error", target, "before_center", -1);
		} else {

			adminUser.setRoles(chosenRoles);
			adminUser.setEnabled(true);
			
			userService.create(adminUser);

			Clients.showNotification(Labels.getLabel("saved.user"), "info",
					target, "before_center", -1);

			userList();
		}
	}

	@Command
	public void checkIfAvalaible() {

		String user = txbUser.getValue();

		if (user == null || user.trim().isEmpty()) {

			lblUser.setValue("Utilizador inválido.!");
			lblUser.setSclass("label label-danger");

			addErrors("invalidName");
		}

		else {
			User u = userService.find(user);

			if (u == null) {
				lblUser.setValue("Disponível");
				lblUser.setSclass("label label-success");

				clearErrors("invalidName");

			} else {
				lblUser.setValue("Utilizador já existente.!");
				lblUser.setSclass("label label-danger");

				addErrors("invalidName");
			}

		}
	}

	@Command
	public void checkConfirm() {

		String confirmation = pass.getValue();

		String password = senha.getValue();

		if (password == null || confirmation == null
				|| password.trim().isEmpty() || confirmation.trim().isEmpty()) {

			lblSenha.setValue("Senha Invalida");
			lblSenha.setSclass("label label-danger");

			addErrors("invalidPass");
		}

		else {

			if (password.equals(confirmation)) {

				clearErrors("invalidConf");
				clearErrors("invalidPass");
				
				lblConf.setValue("Senha válida");
				lblConf.setSclass("label label-success");
			}

			else {
				
				addErrors("invalidConf");
				
				lblConf.setValue("Confirmação deve ser igual a senha introduzida");
				lblConf.setSclass("label label-danger");

				addErrors("invalidPass");
			}
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

	private void clearErrors(String error) {

		erros.remove(error);

		if (erros.size() == 0) {
			btnSave.setVisible(true);
		}
	}

	private void addErrors(String error) {

		erros.add(error);

		btnSave.setVisible(false);
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

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Role> getChosen() {
		return chosen;
	}

	public void setChosen(List<Role> chosen) {
		this.chosen = chosen;
	}

	public List<Role> getStored() {
		return stored;
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

	public void setStored(List<Role> stored) {
		this.stored = stored;
	}
}
