package mz.inolabdev.rh.viewModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mz.inolabdev.rh.entity.Candidate;
import mz.inolabdev.rh.entity.Cellphone;
import mz.inolabdev.rh.entity.ContactPoint;
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
import org.zkoss.bind.Form;
import org.zkoss.bind.SimpleForm;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zk.ui.Component;
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

	@WireVariable
	private EmailService emailService;

	@WireVariable
	private CellPhoneService cellPhoneService;

	@WireVariable
	private VacancyService vacancyService;

	@WireVariable
	DocumentService documentService;

	@Wire("#filePreview")
	private Iframe iFrame;

	@Wire("#iframePoUp")
	Popup popup;

	Form candidateForm = new SimpleForm();

	private String currentFileName;
	private String filePath;
	private Document document;
	private Candidate candidate;
	private ContactPoint contacPoint;
	private boolean fileuploaded = false;
	private int selectedIndex;
	private Vacancy pickedVacancy;
	// private Cellphone cellPhone;

	private ListModelList<Vacancy> vacancies = new ListModelList<Vacancy>(0);
	private List<Candidate> candidates = new ArrayList<Candidate>();
	private ListModelList<Email> emails = new ListModelList<Email>(0);
	private List<Cellphone> cellPhones = new ArrayList<Cellphone>();
	private ListModelList<Document> documents = new ListModelList<Document>(0);

	private ListModelList<Cellphone> cellPModelList = new ListModelList<Cellphone>(
			0);

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Init
	public void init() {

		setCurrentFileName("No file chosen");
		candidate = new Candidate();
		document = new Document();
		emails = new ListModelList<Email>(emailService.getAll());
		cellPModelList = new ListModelList<Cellphone>(cellPhoneService.getAll());
		documents = new ListModelList<Document>(documentService.getAll());
		reload();
	}

	private List<Candidate> reload() {

		if (candidates == null)
			candidates = new ArrayList<Candidate>();
		candidates = candidateService.getAll();
		return candidates;
	}

	@Command("onUploadFile")
	@NotifyChange({ "fileuploaded", "currentFileName" })
	public Document onUploadFile(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx)
			throws IOException {
		document = new Document();
		FileManager fileManager = new FileManager();
		document = fileManager.upload(ctx);
		currentFileName = document.getFileName();
		setFileuploaded(true);
		Clients.showNotification("Sucessfuly Uploaded");
		return document;
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

	public List<Email> getEmails() {
		return emails;
	}

	public void setEmails(ListModelList<Email> emails) {
		this.emails = emails;
	}

	public List<Cellphone> getCellPhones() {
		return cellPhones;
	}

	public void setCellPhones(List<Cellphone> cellPhones) {
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

	public Form getCandidateForm() {
		return candidateForm;
	}

	public void setCandidateForm(Form candidateForm) {
		this.candidateForm = candidateForm;
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}

	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public ListModelList<Cellphone> getCellPModelList() {
		return cellPModelList;
	}

	public void setCellPModelList(ListModelList<Cellphone> cellPModelList) {
		this.cellPModelList = cellPModelList;
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

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public String getCurrentFileName() {
		return currentFileName;
	}

	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}

	public ListModelList<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(ListModelList<Document> documents) {
		this.documents = documents;
	}

}
