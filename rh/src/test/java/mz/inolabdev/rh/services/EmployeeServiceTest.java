package mz.inolabdev.rh.services;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Department;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.JobPosition;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceTest extends GenericTestUnit {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private IndividualTypeService individualTypeService;
	@Autowired
	private JobPositionService jobPositionService;
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
		department.setCreatedAt(Calendar.getInstance().getTime());
		departamentService.create(department);
		return department;

	}

	private Employee creteNewEmployeeTest() {
		Employee employee = new Employee();
		employee.setName("Eusebio");
		employee.setLastName("Maposse");
		employee.setMiddleName("Jose");
		employee.setAcademicLevel("12");
		employee.setBirthday(Calendar.getInstance().getTime());
		employee.setNationality("Moçambicana");
		employee.setJob_position(createNewJobPositionTest());
		employee.setDepartment(createNewDepartament());

		return employee;
	}

	@Test
	public void createTest() {

		Employee employee = creteNewEmployeeTest();

		Employee employeeSaved = employeeService.create(employee);

		assertNotNull(employeeSaved);
		assertNotNull(employeeSaved.getId());
	}

}
