package mz.inolabdev.rh.services;

import java.util.Calendar;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Candidate;
import mz.inolabdev.rh.entity.Department;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.IndividualType;
import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.entity.SubUnit;
import mz.inolabdev.rh.entity.Vacancy;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CandidateServiceTest extends GenericTestUnit {

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private IndividualTypeService individualTypeService;

	@Autowired
	private VacancyService vacancyService;

	@Autowired
	private JobPositionService jobPositionService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SubUnitService subUnitService;

	@Autowired
	private DepartamentService departamentService;

	private JobPosition createNewJobPositionTest() {

		JobPosition jobPosition = new JobPosition();
		jobPosition.setType("Gestor de Clientes");
		jobPosition.setDescription("Suporte aos Clientes");
		jobPositionService.create(jobPosition);
		return jobPosition;
	}

	private Department createNewDepartament() {

		Department department = new Department();
		department.setName("Movertako");
		department.setDescription("Equipe de Support");
		departamentService.create(department);
		return department;
	}

	private Employee createHiringManager() {

		Employee hiringManager = new Employee();
		hiringManager.setName("Santoro");
		hiringManager.setLastName("Maposse");
		hiringManager.setMiddleName("Jose");
		hiringManager.setAcademicLevel("Gradueted");
		hiringManager.setBirthday(Calendar.getInstance().getTime());
		hiringManager.setNationality("Mozambican");
		hiringManager.setJob_position(createNewJobPositionTest());
		hiringManager.setDepartment(createNewDepartament());
		employeeService.create(hiringManager);
		return hiringManager;
	}

	private Vacancy createVacancy() {

		JobPosition jobTitle = new JobPosition();
		jobTitle.setType("Senior Manager");
		jobTitle.setDescription("Inolab Representant/Administration Manager");
		jobPositionService.create(jobTitle);
		JobPosition savedjobPosition = jobPositionService
				.find(jobTitle.getId());

		SubUnit subUnit = new SubUnit();
		subUnit.setName("IT/Tecnologies");
		subUnitService.create(subUnit);

		Vacancy vacancy = new Vacancy();

		vacancy.setName("vacany for Inolab Manager");
		vacancy.setStatus("Un_published");
		vacancy.getHiringManagers().add(createHiringManager());
		vacancy.setJobTitle(savedjobPosition); // eg: Accountant, Java
												// programmer, Sales Manager
		vacancy.setSubUnit(subUnit);
		vacancyService.create(vacancy);

		return vacancy;
	}

	private Candidate createCandidate() {

		Candidate candidate = new Candidate();
		candidate.setName("Jhon");
		candidate.setLastName("New Man");
		candidate.setGender("Male");
		candidate.setAcademicLevel("Gradueted");
		candidate.setBirthday(Calendar.getInstance().getTime());
		candidate.setNationality("Mozambican");
		candidate.getVacancies().add(createVacancy());
		return candidate;
		
	}

	private Candidate createCandidateTest() {

		IndividualType it = new IndividualType();
		it.setName("Individual");
		individualTypeService.create(it);
		Candidate candidate = createCandidate();
		candidate.setIndividualType(it);
		candidate.getVacancies().add(createVacancy());

		candidateService.create(candidate);

		return candidate;
	}

	@Test
	public void createTest() {

		Candidate candidate = createCandidateTest();
		Assert.assertNotNull(candidate);
		Assert.assertNotNull(candidate.getId());
	}

}
