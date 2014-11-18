package mz.inolabdev.rh.services;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Department;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartametServiceTest extends GenericTestUnit {
	
	@Autowired
	private DepartamentService departamentService;

	private Department createDepartament() {
		Department department = new Department();

		department.setName("Mavertako");
		department.setDescription("Gestao de Projeectos");
		return department;
	}

	private Department createDepartamentTest() {
		Department department = createDepartament();
		departamentService.create(department);

		return department;
	}

	@Test
	public void createTest() {

		Department department = createDepartamentTest();

		Assert.assertNotNull(department);
		Assert.assertNotNull(department.getId());
	}
}
