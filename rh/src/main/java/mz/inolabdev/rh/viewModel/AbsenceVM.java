package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.License;
import mz.inolabdev.rh.entity.LicenseType;
import mz.inolabdev.rh.entity.Reason;
import mz.inolabdev.rh.entity.User;
import mz.inolabdev.rh.services.LicenceService;
import mz.inolabdev.rh.services.LicenceTypeService;
import mz.inolabdev.rh.services.ReasonService;
import mz.inolabdev.rh.services.UserService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
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
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class AbsenceVM extends AbstractViewModel {

	private Div target;

	private License license;

	private List<LicenseType> types;

	private List<Integer> years;

	private List<Reason> reasons;

	private List<License> licences;

	@WireVariable
	private LicenceTypeService licenceTypeService;

	@WireVariable
	private ReasonService reasonService;

	@WireVariable
	private LicenceService licenceService;

	@WireVariable
	private UserService userService;
	
	@Wire
	private Div divCancelation;

	private User user;

	private List<License> pendingLicences;

	private Window window;

	private License cancelation;

	@AfterCompose
	public void initSetup(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("target") Div target,
			@ExecutionArgParam("breadcrumb") Ol ol) {

		Selectors.wireComponents(view, this, false);

		this.setTarget(target);
		this.ol = ol;
	}

	@Init
	public void init() {

		// *** Initialize ***//
		license = new License();
		types = licenceTypeService.getAll();

		years = new ArrayList<Integer>();
		years.add(Calendar.getInstance().get(Calendar.YEAR));

		reasons = reasonService.getAll();

		licences = licenceService.getAll();

		String userName = Executions.getCurrent().getUserPrincipal().getName();

		setUser(userService.find(userName));

		pendingLicences = licenceService.findByStatus("Pendente");

	}

	@Command
	public void requestAbsense() {

		if (license.getLicense_type() == null) {
			Clients.showNotification("Selecione um tipo de licença", "warning",
					target, "before_center", -1);
		}

		else if (license.getReason() == null) {
			Clients.showNotification("Selecione uma motivação", "warning",
					target, "before_center", -1);
		}

		else if (license.getYear() == null) {
			Clients.showNotification("Selecione o ano", "warning", target,
					"before_center", -1);
		}

		else {
			license.setStatus("Pendente");
			license.setEmployee(user.getUserProfile());

			licenceService.create(license);

			licences = licenceService.getAll();

			final HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("target", target);
			map.put("breadcrumb", ol);
			target.getChildren().clear();
			Executions.createComponents("views/absence/absence_status.zul",
					target, map);

			links = new ArrayList<String>();
			links.add("Licença");
			links.add("Status");
			drawnBreadcrumb("fa fa-plane", "Gestão de Ausências", links);

			Clients.showNotification("Licença requisitada com sucesso!",
					"info", target, "before_center", -1);
		}
	}

	@Command
	public void cancelModal(@BindingParam("licence") License licence) {
		
		cancelation = licenceService.find(licence.getId());
		
		window = (Window) Executions.createComponents(
				"/views/absence/confirm_cancel.zul", divCancelation, null);
		window.doModal();
	}

	@Command
    public void cancelRequest() {
		
		cancelation.setStatus("Cancelado");
		licenceService.update(cancelation);
		
		licences = licenceService.getAll();

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/absence/absence_status.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Licença");
		links.add("Status");
		drawnBreadcrumb("fa fa-plane", "Gestão de Ausências", links);

		Clients.showNotification("Licença cancelada com sucesso!",
				"info", target, "before_center", -1);
		
        window.detach();
    }

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public Div getTarget() {
		return target;
	}

	public void setTarget(Div target) {
		this.target = target;
	}

	public List<LicenseType> getTypes() {
		return types;
	}

	public void setTypes(List<LicenseType> types) {
		this.types = types;
	}

	public List<Integer> getYears() {
		return years;
	}

	public void setYears(List<Integer> years) {
		this.years = years;
	}

	public List<Reason> getReasons() {
		return reasons;
	}

	public void setReasons(List<Reason> reasons) {
		this.reasons = reasons;
	}

	public List<License> getLicences() {
		return licences;
	}

	public void setLicences(List<License> licences) {
		this.licences = licences;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<License> getPendingLicences() {
		return pendingLicences;
	}

	public void setPendingLicences(List<License> pendingLicences) {
		this.pendingLicences = pendingLicences;
	}
}
