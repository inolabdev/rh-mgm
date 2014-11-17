package mz.inolabdev.rh.viewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.Project;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.EmployeeService;
import mz.inolabdev.rh.services.ImageService;
import mz.inolabdev.rh.services.ProjectService;
import mz.inolabdev.rh.services.RoleService;
import mz.inolabdev.rh.services.UserService;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
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
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
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

	@WireVariable
	private ImageService imageService;

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

	@Wire
	private Image imgProfile;
	
	@WireVariable
	private ProjectService projectService;

	private User user;

	private String newPass;

	private String confNewPass;

	private Set<String> erros = new HashSet<String>();

	private User loggedUser;

	private mz.inolabdev.rh.entity.Image profileImg;
	
	private List<Project> projects = new ArrayList<Project>();

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("target") Div target,
			@ExecutionArgParam("breadcrumb") Ol ol) throws IOException {

		Selectors.wireComponents(view, this, false);

		this.target = target;
		this.ol = ol;

		loggedUser = userService.find(Executions.getCurrent()
				.getUserPrincipal().getName());

		if (imgProfile != null) {

			mz.inolabdev.rh.entity.Image image = loggedUser.getUserProfile()
					.getImageProfile();

			if (image == null)

				imgProfile.setSrc("img/default_image.png");

			else {

				org.zkoss.image.Image img = new AImage(image.getFileName(),
						image.getContent());

				imgProfile.setContent(img);
			}
		}

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

		loggedUser = userService.find(Executions.getCurrent()
				.getUserPrincipal().getName());
		
		setProjects(projectService.projectsByEmployee(loggedUser.getUserProfile()));
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

	@Command
	public void updatePass() {

	}

	@Command
	public void upLoadImage(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx)
			throws IOException {

		profileImg = new mz.inolabdev.rh.entity.Image();
		FileManager fileManager = new FileManager();
		profileImg = fileManager.uploadImage(ctx);

		imgProfile.setSrc(null);

		org.zkoss.image.Image img = new AImage(profileImg.getFileName(),
				profileImg.getContent());

		imgProfile.setContent(img);
	}

	@Command
	public void savePicture() {

		if (profileImg == null) {
			Clients.showNotification("Selecione uma imagem primeiro!",
					"warning", imgProfile, "before_center", -1);
		} else {

			Employee current = loggedUser.getUserProfile();

			if (current.getImageProfile() != null) {

				imageService.update(current.getImageProfile());
				Clients.showNotification("Imagem actualizada com sucesso!",
						"info", imgProfile, "before_center", -1);
			}

			else {
				profileImg.setHolder(current);
				imageService.create(profileImg);

				current.setImageProfile(profileImg);
				
				System.out.println(current.getImageProfile());
				
				employeeService.update(current);

				Clients.showNotification("Imagem gravada com sucesso!",
						"info", imgProfile, "before_center", -1);
			}

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfNewPass() {
		return confNewPass;
	}

	public void setConfNewPass(String confNewPass) {
		this.confNewPass = confNewPass;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
}
