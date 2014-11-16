package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.LicenseType;
import mz.inolabdev.rh.services.LicenceTypeService;

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
public class LicenceTypeVM extends AbstractViewModel{

	private LicenseType licenseType;

	private List<LicenseType> licenseTypes;

	@WireVariable
	private LicenceTypeService licenceTypeService;

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
		licenseType = new LicenseType();
		licenseTypes = licenceTypeService.getAll();
	}

	@Command
	public void licenceTypeList() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/licenceType/index.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Tipos de Licença");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void licenceTypeNew() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions
				.createComponents("views/licenceType/create.zul", target, map);
		
		links = new ArrayList<String>();
		links.add("Tipos de Licenças");
		links.add("Novo");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void saveLicenceType() {

		licenceTypeService.create(licenseType);

		Clients.showNotification("Licença salva com sucesso!", "info", target,
				"before_center", -1);

		licenceTypeList();
	}

	public LicenseType getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}

	public List<LicenseType> getLicenseTypes() {
		return licenseTypes;
	}

	public void setLicenseTypes(List<LicenseType> licenseTypes) {
		this.licenseTypes = licenseTypes;
	}


}
