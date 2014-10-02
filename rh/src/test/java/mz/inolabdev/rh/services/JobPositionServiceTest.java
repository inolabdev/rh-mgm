package mz.inolabdev.rh.services;

import static org.junit.Assert.assertNotNull;
import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.JobPosition;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JobPositionServiceTest extends GenericTestUnit {

	@Autowired
	private JobPositionService jobPositionService;

	private JobPosition createNewJob() {

		JobPosition job = new JobPosition();
		job.setType("Testator");
		job.setDescription("InoLab");

		return job;
	}

	@Test
	public void createTest() {

		JobPosition job = createNewJob();

		JobPosition jobSaved = jobPositionService.create(job);

		assertNotNull(jobSaved);
		assertNotNull(jobSaved.getId());
	}
}
