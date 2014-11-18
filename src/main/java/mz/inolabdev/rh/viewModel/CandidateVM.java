package mz.inolabdev.rh.viewModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import mz.inolabdev.rh.entity.Candidate;
import mz.inolabdev.rh.entity.Cellphone;
import mz.inolabdev.rh.entity.Comment;
import mz.inolabdev.rh.entity.Document;
import mz.inolabdev.rh.entity.Email;
import mz.inolabdev.rh.entity.Vacancy;
import mz.inolabdev.rh.services.CandidateService;
import mz.inolabdev.rh.services.CellPhoneService;
import mz.inolabdev.rh.services.ContactPointService;
import mz.inolabdev.rh.services.DocumentService;
import mz.inolabdev.rh.services.EmailService;
import mz.inolabdev.rh.services.VacancyService;
import mz.inolabdev.rh.util.DateFormat;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Filedownload;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Window;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CandidateVM extends AbstractViewModel {

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

	private Menupopup menupopup;

	@Wire("#showId")
	private Window showWindow;

	@Wire("#show_buttons_acts")
	private Div divActs;

	@Wire("#buttonActs")
	private Button button;

	@Wire("#mainInclude")
	private Include mainInclude;

	@Wire("#candidateList")
	private Div candidateList;

	@Wire("#newCandidate")
	private Div newCandidate;

	@Wire(".document_col")
	private Div dcCol;

	private String menupopupId;

	private Window window;

	@Wire
	private Div newInterview;
	// end of components

	private DateFormat dateFormat;
	// simple attrs
	private String divId;
	private String currentFileName;
	private Document document;
	private Comment comment;
	private Candidate candidate;
	private Candidate selectedCandidate;
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
		dateFormat = new DateFormat();
		candidate = new Candidate();
		document = new Document();
		comment = new Comment();
		emails = new ListModelList<Email>(emailService.getAll());
		cellPModelList = new ListModelList<Cellphone>(cellPhoneService.getAll());
		documents = new ListModelList<Document>(documentService.getAll());
		candidates = new ListModelList<Candidate>(candidateService.getAll());
		vacancies = new ListModelList<Vacancy>(vacancyService.getAll());
	}

	@Command
	@NotifyChange("candidates")
	public void candidateList() {

		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		Clients.showNotification(
				"Point the cursor to the Resume name to preview", "info",
				dcCol, "end_center", 4000);
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

	@Command("preview")
	public void preview(@BindingParam("file_id") Long id,
			@BindingParam("target") Component targetComponent) {

		divId = "document_col";
		Iframe iFrame = new Iframe();
		Popup popup = new Popup();

		Random random = new Random();
		Document resume = documentService.find(id);
		FileManager fileManager = new FileManager();

		dcCol.setAttribute("position", "end_before");
		dcCol.setAttribute("style", "cursor:pointer");
		dcCol.setPopup(popup);

		if (documentService.getAll().size() > 1) {
			iFrame.setId("i_preview_" + random.nextLong());
			popup.setId("p_preview_" + random.nextLong());

			iFrame.setContent(fileManager.byteToFile("resume",
					resume.getFileType(), resume.getContent()));

			divId = divId + id;
			dcCol.setId(divId);

			iFrame.setParent(popup);
			popup.setParent(dcCol);

			popup.open(targetComponent, "end_before");

		} else {

			iFrame.setContent(fileManager.byteToFile("resume",
					resume.getFileType(), resume.getContent()));

			dcCol.setId(divId);
			iFrame.setParent(popup);
			popup.setParent(dcCol);

			popup.open(targetComponent, "end_before");
		}
	}

	@Command
	public void download(@BindingParam("file_id") Long id) {

		Document resume = documentService.find(id);
		FileManager fileManager = new FileManager();
		Filedownload.save(fileManager.byteToFile("resume",
				resume.getFileType(), resume.getContent()));
	}

	@NotifyChange("*")
	@Command
	public void selectActions(@BindingParam("popup") Menupopup popup,
			@BindingParam("candidate") Candidate candidate) {

		this.menupopup = popup;

		Menuitem action1 = new Menuitem();
		Menuitem action2 = new Menuitem();
		Menuitem action3 = new Menuitem();

		if (candidate.getStatus() == null) {

			action1.setLabel("schedule an Interview");

			action1.addEventListener(Events.ON_CLICK,
					new EventListener<Event>() {
						public void onEvent(Event event) {
							showModal();
						}
					});

			action2.setLabel("Reject");
			action2.addEventListener(Events.ON_CLICK,
					new EventListener<Event>() {
						public void onEvent(Event event) {
							// reject();
						}
					});

			action1.setParent(menupopup);
			action2.setParent(menupopup);

		} else if (candidate.getStatus() == "interview Scheluded") {
			action1.setLabel("mark interview passed");
			action1.addEventListener(Events.ON_CLICK,
					new EventListener<Event>() {
						public void onEvent(Event event) {

						}
					});
			action2.setLabel("mark interview failed");
			action2.addEventListener(Events.ON_CLICK,
					new EventListener<Event>() {
						public void onEvent(Event event) {
							// schedueleInterview();
						}
					});

			action3.setLabel("Reject");
			action3.addEventListener(Events.ON_CLICK,
					new EventListener<Event>() {
						public void onEvent(Event event) {
							// schedueleInterview();
						}
					});

			action1.setParent(menupopup);
			action2.setParent(menupopup);
			action3.setParent(menupopup);

		} else if (candidate.getStatus() == "interview passed") {
			action1.setLabel("schedule an Interview");
			action2.setLabel("offer Job");
			action3.setLabel("Reject");
			action1.setParent(menupopup);
			action2.setParent(menupopup);
			action3.setParent(menupopup);

		} else if (candidate.getStatus() == "interview failed") {
			action1.setLabel("Reject");
			action1.setParent(menupopup);
		}

		menupopup.invalidate();

		BindUtils.postNotifyChange(null, null, CandidateVM.this, "*");
	}

	public void showModal() {

		window = (Window) Executions.createComponents(
				"/views/recruitment/candidate/new_interview.zul", target, null);
		window.doModal();
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

	@Command
	@NotifyChange({ "candidate" })
	public void ShowCandidate(@BindingParam("candId") Long id,
			@ContextParam(ContextType.VIEW) Component view) {

		candidate = candidateService.find(id);
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("candidate", candidate);
		target.getChildren().clear();
		Executions.createComponents("views/recruitment/candidate/show.zul",
				target, map);
	}

	@Command
	@NotifyChange({ "selectedCandidate" })
	public void UpdateCandidate(@BindingParam("candId") Long id,
			@ContextParam(ContextType.VIEW) Component view) {

		selectedCandidate = candidateService.find(id);
		
		//Messagebox.show(selectedCandidate.fullName());
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		map.put("selectedCandidate", selectedCandidate);
		target.getChildren().clear();
		Executions.createComponents("views/recruitment/candidate/edit.zul",
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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getMenupopupId() {
		return menupopupId;
	}

	public void setMenupopupId(String menupopupId) {
		this.menupopupId = menupopupId;
	}

	public Candidate getSelectedCandidate() {
		return selectedCandidate;
	}

	public void setSelectedCandidate(Candidate selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
	}

}
