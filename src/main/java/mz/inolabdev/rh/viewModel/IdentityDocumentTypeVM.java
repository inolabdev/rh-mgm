package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import mz.inolabdev.rh.entity.IdentityDocumentType;
import mz.inolabdev.rh.services.IdentityDocumentTypeService;

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
public class IdentityDocumentTypeVM extends AbstractViewModel {

	private Div target;

	private List<IdentityDocumentType> docTypes;

	private IdentityDocumentType docType;

	@WireVariable
	private IdentityDocumentTypeService identityDocumentTypeService;

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
		setDocTypes(identityDocumentTypeService.getAll());
		docType = new IdentityDocumentType();
	}

	@Command
	public void docTypeList() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/identityDocumentType/index.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Employeee");
		links.add("Inicio");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void docTypeNew() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/identityDocumentType/create.zul",
				target, map);

		links = new ArrayList<String>();
		links.add("Documentos De Identificacao");
		links.add("Novo");
		drawnBreadcrumb("fa fa-sort", "Mais", links);
	}

	@Command
	public void saveDocType() {

		identityDocumentTypeService.create(docType);

		Clients.showNotification("Funcionario registado com sucesso!", "info",
				target, "before_center", -1);
		docTypeList();
	}

	public List<IdentityDocumentType> getDocTypes() {
		return docTypes;
	}

	public void setDocTypes(List<IdentityDocumentType> docTypes) {
		this.docTypes = docTypes;
	}

	public IdentityDocumentType getDocType() {
		return this.docType;
	}

	public void setDocType(IdentityDocumentType docType) {
		this.docType = docType;
	}

}
