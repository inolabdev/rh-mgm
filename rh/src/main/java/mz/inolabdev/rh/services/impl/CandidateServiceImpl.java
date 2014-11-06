package mz.inolabdev.rh.services.impl;

import mz.inolabdev.rh.entity.Candidate;
import mz.inolabdev.rh.entity.Cellphone;
import mz.inolabdev.rh.entity.Comment;
import mz.inolabdev.rh.entity.Document;
import mz.inolabdev.rh.entity.Email;
import mz.inolabdev.rh.services.CandidateService;
import mz.inolabdev.rh.services.CellPhoneService;
import mz.inolabdev.rh.services.CommentService;
import mz.inolabdev.rh.services.ContactPointService;
import mz.inolabdev.rh.services.DocumentService;
import mz.inolabdev.rh.services.EmailService;
import mz.inolabdev.rh.services.VacancyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.select.annotation.Wire;

@Service("candidateService")
public class CandidateServiceImpl extends GenericServiceImpl<Candidate>
		implements CandidateService {

	@Autowired
	private ContactPointService contactPointService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private CellPhoneService cellPhoneService;

	@Autowired
	private VacancyService vacancyService;

	@Autowired
	DocumentService documentService;

	@Autowired
	CommentService commentService;
	
	
	@Override
	public Candidate create(Candidate candidate) {
		
		Candidate cand = specificDao.create(candidate);
		
		if (commentService.find(candidate.getComment().getId()) != null)
			commentService.update(candidate.getComment());
		else {
			commentService.create(candidate.getComment());
		}

		for (Email email : cand.getEmails()) {
			email.setHolder(cand);
			emailService.update(email);
		}

		for (Cellphone cellphone : cand.getCellPhones()) {
			cellphone.setHolder(cand);
			cellPhoneService.update(cellphone);
		}

		for (Document document : cand.getDocuments()) {
			document.setHolder(cand);
			documentService.update(document);
		}

		return cand;
	}
}
