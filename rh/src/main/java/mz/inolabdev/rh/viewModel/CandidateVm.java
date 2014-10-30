package mz.inolabdev.rh.viewModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mz.inolabdev.rh.entity.Candidate;
import mz.inolabdev.rh.entity.Cellphone;
import mz.inolabdev.rh.entity.Document;
import mz.inolabdev.rh.entity.Email;
import mz.inolabdev.rh.entity.Vacancy;
import mz.inolabdev.rh.services.CandidateService;
import mz.inolabdev.rh.services.CellPhoneService;
import mz.inolabdev.rh.services.ContactPointService;
import mz.inolabdev.rh.services.DocumentService;
import mz.inolabdev.rh.services.EmailService;
import mz.inolabdev.rh.services.VacancyService;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Div;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Popup;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CandidateVm extends AbstractViewModel {

	// dependencies
	@WireVariable
	private CandidateService candidateService;

	@WireVariable
	private ContactPointService contactPointService;

	@WireVariable
	private EmailService emailService;

	@WireVariable
	private CellPhoneService cellPhoneService;

	@WireVariable
	private VacancyService vacancyService;

	@WireVariable
	DocumentService documentService;

	// components
	private Div target;

	@Wire("#mainInclude")
	private Include mainInclude;

	@Wire("#candidateList")
	private Div candidateList;

	@Wire("#newCandidate")
	private Div newCandidate;

	@Wire("#filePreview")
	private Iframe iFrame;

	@Wire("#iframePoUp")
	Popup popup;
	// end of components

	// simple attrs
	private String currentFileName;
	private Document document;
	private Candidate candidate;
	private Vacancy pickedVacancy;

	private ListModelList<Vacancy> vacancies;
	private List<Candidate> candidates;
	private ListModelList<Email> emails;
	private ListModelList<Document> documents;
	private ListModelList<Cellphone> cellPModelList;

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

		setCurrentFileName("No file chosen");
		candidate = new Candidate();
		document = new Document();
		emails = new ListModelList<Email>(emailService.getAll());
		cellPModelList = new ListModelList<Cellphone>(cellPhoneService.getAll());
		documents = new ListModelList<Document>(documentService.getAll());
		candidates = new ListModelList<Candidate>(candidateService.getAll());
	}

	@Command
	@NotifyChange("candidates")
	public void candidateList() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/recruitment/candidate/index.zul",
				target, map);
	}

	@Command
	@NotifyChange({ "candidates", "pickedVacancy" })
	public void saveCandidate() {

		for (Email email : emails.getSelection()) {
			candidate.addEmail(email);
		}

		candidate.setCellPhones(cellPModelList.getSelection());
		documentService.create(document);
		Set<Document> resumes = new HashSet<Document>(0);
		resumes.add(document);
		candidate.setDocuments(resumes);
		candidate.getVacancies().add(pickedVacancy);
		candidateService.create(candidate);
		candidateList();
	}

	@Command("newCellPhone")
	@NotifyChange("cellPModelList")
	public void newCellPhone(@BindingParam("cellPhone") String cellPhone) {

		Cellphone cp = cellPhoneService.findByValue(cellPhone);
		if (cp != null) {
			cellPModelList.add(cp);
			cellPModelList.addToSelection(cp);
		} else {
			cp = new Cellphone();
			cp.setValue(cellPhone);
			cp.setType("cellPhone");
			cellPhoneService.create(cp);
			cellPModelList.add(cp);
			cellPModelList.addToSelection(cp);
		}
	}

	@Command("newEmail")
	@NotifyChange("emails")
	public void newEmail(@BindingParam("email") String email) {

		Email eMail = emailService.findByValue(email);
		if (eMail != null) {
			emails.add(eMail);
			emails.addToSelection(eMail);
		} else {
			eMail = new Email();
			eMail.setValue(email);
			eMail.setType("email");
			emailService.create(eMail);
			emails.add(eMail);
			emails.addToSelection(eMail);
		}
	}

	@Command("onUploadFile")
	@NotifyChange({ "fileuploaded", "currentFileName" })
	public Document onUploadFile(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx)
			throws IOException {

		document = new Document();
		FileManager fileManager = new FileManager();
		document = fileManager.upload(ctx);
		// after fixing upload button this will be removed
		currentFileName = document.getFileName();
		Clients.showNotification("Sucessfuly Uploaded");

		return document;
	}

	@Command
	public void preview(@BindingParam("file_id") Long id,
			@BindingParam("target") Component targetComponent) {

		Document resume = documentService.find(id);
		FileManager fileManager = new FileManager();
		iFrame.setContent(fileManager.byteToFile("resume",
				resume.getFileType(), resume.getContent()));
		popup.open(targetComponent, "end_before");
	}

	@Command
	public void download(@BindingParam("file_id") Long id) {

		Document resume = documentService.find(id);
		FileManager fileManager = new FileManager();
		Filedownload.save(fileManager.byteToFile("resume",
				resume.getFileType(), resume.getContent()));
	}

	@Command
	public void newCandidate() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Executions.createComponents("views/recruitment/candidate/create.zul",
				target, map);
	}

	public String getCurrentFileName() {
		return currentFileName;
	}

	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}

	public List<Candidate> getCandidates() {
		return candidates;
	}

	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	public ListModelList<Email> getEmails() {
		return emails;
	}

	public void setEmails(ListModelList<Email> emails) {
		this.emails = emails;
	}

	public ListModelList<Cellphone> getCellPModelList() {
		return cellPModelList;
	}

	public void setCellPModelList(ListModelList<Cellphone> cellPModelList) {
		this.cellPModelList = cellPModelList;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public ListModelList<Vacancy> getVacancies() {
		return vacancies;
	}

	public void setVacancies(ListModelList<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}

	public Vacancy getPickedVacancy() {
		return pickedVacancy;
	}

	public void setPickedVacancy(Vacancy pickedVacancy) {
		this.pickedVacancy = pickedVacancy;
	}

	public ListModelList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ListModelList<Document> documents) {
		this.documents = documents;
	}

}
