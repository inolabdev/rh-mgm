package mz.inolabdev.rh.viewModel;

import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.IdentityDocumentType;
import mz.inolabdev.rh.services.IdentityDocumentTypeService;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class IdentityDocumentTypeViewModel extends AbstractViewModel{

	@Wire("#mainInclude")
	private Include mainInclude;

	@Wire("#DocTypeList")
	private Div DocTypeList;

	@Wire("#DocTypeNew")
	private Div DocTypeNew;

	private IdentityDocumentType type;

	private List<IdentityDocumentType> types;

	@WireVariable
	private IdentityDocumentTypeService identityDocumentTypeService;

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		type = new IdentityDocumentType();

		reload();
	}

	@Command
	@NotifyChange("types")
	public void DocTypeList() {

		reload();

		if (DocTypeList != null & DocTypeNew != null) {
			DocTypeList.setVisible(true);
			DocTypeNew.setVisible(false);
		} else
			mainInclude.setSrc("views/identityDocumentType/index.zul");
	}

	@Command
	public void DocTypeNew() {

		type = new IdentityDocumentType();

		DocTypeList.setVisible(false);
		DocTypeNew.setVisible(true);
	}

	@Command
	@NotifyChange("types")
	public void saveDocType() {

		identityDocumentTypeService.create(type);
		log("");

		DocTypeList();
	}

	private List<IdentityDocumentType> reload() {

		if (types == null)
			types = new ArrayList<IdentityDocumentType>();

		types = identityDocumentTypeService.getAll();

		return types;
	}

	public IdentityDocumentType getType() {
		return this.type;
	}

	public void setType(IdentityDocumentType type) {
		this.type = type;
	}

	public List<IdentityDocumentType> getTypes() {
		return this.types;
	}

	public void setTypes(List<IdentityDocumentType> types) {
		this.types = types;
	}

	public Div getDocTypeList() {
		return this.DocTypeList;
	}

	public void setDocTypeList(Div docTypeList) {
		this.DocTypeList = docTypeList;
	}

	public Div getDocTypeNew() {
		return this.DocTypeNew;
	}

	public void setDocTypeNew(Div docTypeNew) {
		this.DocTypeNew = docTypeNew;
	}

}
