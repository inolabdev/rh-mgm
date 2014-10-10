package mz.inolabdev.rh.services;

import java.util.Calendar;
import java.util.List;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Department;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.entity.SubUnit;
import mz.inolabdev.rh.entity.Vacancy;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class VacancyServiceTest extends GenericTestUnit {

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
		return hiringManager;
	}

	private Vacancy createVacancyTest() {

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

	@Test
	public void createTest() {

		Vacancy vacancy = createVacancyTest();
		Assert.assertNotNull(vacancy);
		Assert.assertNotNull(vacancy.getId());
	}

	@Test
	public void deleteTest() {

		Vacancy vacany = new Vacancy();
		Vacancy vacanySaved = vacancyService.create(vacany);
		vacancyService.delete(vacanySaved.getId());
		Vacancy vacanyDeleted = vacancyService.find(vacanySaved.getId());
		Assert.assertNull(vacanyDeleted);
	}

	@Test
	public void findTest() {

		Vacancy vacany = new Vacancy();
		Vacancy vacanySaved = vacancyService.create(vacany);
		Vacancy foundVacancy = vacancyService.find(vacanySaved.getId());
		Assert.assertNotNull(foundVacancy);
	}

	@Test
	public void countTest() {

		Vacancy vacany = new Vacancy();
		long countBefore = vacancyService.count();
		vacancyService.create(vacany);
		long countAfter = vacancyService.count();
		Assert.assertEquals(countBefore, countAfter - 1);
	}

	@Test
	public void getAllTest() {

		this.createVacancyTest();

		List<Vacancy> all = vacancyService.getAll();
		Assert.assertNotNull(all);
		Assert.assertTrue(all.size() > 0);
	}

}
