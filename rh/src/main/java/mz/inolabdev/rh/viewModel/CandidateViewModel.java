package mz.inolabdev.rh.viewModel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mz.inolabdev.rh.entity.Candidate;
import mz.inolabdev.rh.entity.ContactPoint;
import mz.inolabdev.rh.entity.Document;
import mz.inolabdev.rh.services.CandidateService;
import mz.inolabdev.rh.services.ContactPointService;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Form;
import org.zkoss.bind.SimpleForm;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CandidateViewModel {
	@Wire("#mainInclude")
	private Include mainInclude;

	@Wire("#candidateList")
	private Div candidateList;

	@Wire("#newCandidate")
	private Div newCandidate;

	@WireVariable
	private CandidateService candidateService;

	@WireVariable
	private ContactPointService contactPointService;

	Form candidateForm = new SimpleForm();

	private String filePath;
	private Document document;
	private Candidate candidate;
	private ContactPoint contacPoint;
	private boolean fileuploaded = false;

	private List<Candidate> candidates = new ArrayList<Candidate>();
	private ListModelList<ContactPoint> emails = new ListModelList<ContactPoint>(
			0);
	private ListModelList<Document> documents = new ListModelList<Document>(0);
	private List<ContactPoint> cellPhones = new ArrayList<ContactPoint>();

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		candidate = new Candidate();
		emails = new ListModelList<ContactPoint>(contactPointService.getAll());

		reload();
	}

	private List<Candidate> reload() {

		if (candidates == null)
			candidates = new ArrayList<Candidate>();

		candidates = candidateService.getAll();
		return candidates;
	}

	@Command
	@NotifyChange("fileuploaded")
	public Document onUploadFile(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx)
			throws IOException {

		UploadEvent upEvent = null;

		document = new Document();

		Object objUploadEvent = ctx.getTriggerEvent();

		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}

		if (upEvent != null) {

			Media media = upEvent.getMedia();
			filePath = Executions.getCurrent().getDesktop().getWebApp()
					.getRealPath("/");
			// String yearPath = "\\" + "PDFs";
			// filePath = filePath + yearPath;
			File baseDir = new File(filePath);
			if (!baseDir.exists()) {
				baseDir.mkdirs();
			}
			Files.copy(new File(filePath + media.getName()),
					media.getStreamData());

			document.setFileName(media.getName());
			document.setFileType(media.getContentType());
			document.setContent(media.getByteData());

			Messagebox.show("File Sucessfully uploaded in the path [ ."
					+ filePath + " ]");
			setFileuploaded(true);

			filePath = filePath + media.getName();

			return document;
		}

		return document;
	}

	@Command("newEmail")
	@NotifyChange("candidates")
	public void saveCandidate(@BindingParam("email") ContactPoint email) {

		// for (ContactPoint cp : emails) {
		// if (!cp.getValue().equals(email)) {
		// contactPointService.create(email);
		// emails.addToSelection(email);
		// } else {
		// emails.addToSelection(email);
		// }
		// }
		// candidate.setEmails(emails.getSelection());
		// try {
		// candidate.getDocuments().add(onUploadFile(null));
		// documents.addToSelection(onUploadFile(null));
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// candidateService.create(candidate);
		// candidateList();
	}

	@Command("newCellPhone")
	public void newCellPhone() {

	}

	@Command
	@NotifyChange("candidates")
	public void candidateList() {

		reload();

		if (candidateList != null & newCandidate != null) {
			candidateList.setVisible(true);
			newCandidate.setVisible(false);
		} else
			mainInclude.setSrc("views/recruitment/candidate/index.zul");

	}

	@Command
	public void newCandidate() {

		candidate = new Candidate();
		candidateList.setVisible(false);
		newCandidate.setVisible(true);
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public Div getNewCandidate() {
		return newCandidate;
	}

	public void setNewCandidate(Div newCandidate) {
		this.newCandidate = newCandidate;
	}

	public List<ContactPoint> getEmails() {
		return emails;
	}

	public void setEmails(ListModelList<ContactPoint> emails) {
		this.emails = emails;
	}

	public List<ContactPoint> getCellPhones() {
		return cellPhones;
	}

	public void setCellPhones(List<ContactPoint> cellPhones) {
		this.cellPhones = cellPhones;
	}

	public ContactPoint getContacPoint() {
		return contacPoint;
	}

	public void setContacPoint(ContactPoint contacPoint) {
		this.contacPoint = contacPoint;
	}

	public boolean isFileuploaded() {
		return fileuploaded;
	}

	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}

	public ListModelList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ListModelList<Document> documents) {
		this.documents = documents;
	}

	public Form getCandidateForm() {
		return candidateForm;
	}

	public void setCandidateForm(Form candidateForm) {
		this.candidateForm = candidateForm;
	}

}
