package mz.inolabdev.rh.services;

import java.util.List;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.SubUnit;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SubUnitServiceTest extends GenericTestUnit {

	@Autowired
	private SubUnitService subUnitService;

	@Autowired
	private VacancyService vacancyService;

	private SubUnit createNewSubUnit() {

		SubUnit subUnit = new SubUnit();
		subUnit.setName("IT");
		return subUnit;
	}

	private SubUnit createSubUnitTest() {

		SubUnit subUnit = createNewSubUnit();
		subUnitService.create(subUnit);
		return subUnit;
	}

	@Test
	public void createTest() {

		SubUnit savedSubUnit = createSubUnitTest();
		Assert.assertNotNull(savedSubUnit);
		Assert.assertNotNull(savedSubUnit.getId());
	}

	@Test
	public void deleteTest() {

		SubUnit subUnit = createNewSubUnit();
		SubUnit subUnitSaved = subUnitService.create(subUnit);
		subUnitService.delete(subUnitSaved.getId());
		SubUnit subUnitDeleted = subUnitService.find(subUnitSaved.getId());
		Assert.assertNull(subUnitDeleted);
	}

	@Test
	public void findTest() {

		SubUnit subUnit = createNewSubUnit();
		SubUnit subUnitSaved = subUnitService.create(subUnit);
		SubUnit foundSubUnit = subUnitService.find(subUnitSaved.getId());
		Assert.assertNotNull(foundSubUnit);
	}

	@Test
	public void countTest() {

		SubUnit subUnit = createNewSubUnit();
		long countBefore = subUnitService.count();
		subUnitService.create(subUnit);
		long countAfter = subUnitService.count();
		Assert.assertEquals(countBefore, countAfter - 1);
	}

	@Test
	public void getAllTest() {

		this.createTest();
		List<SubUnit> all = subUnitService.getAll();
		Assert.assertNotNull(all);
		Assert.assertTrue(all.size() > 0);
	}

}
