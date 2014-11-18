package mz.inolabdev.rh.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.JobPosition;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CreatedAndUpdatedDateTest extends GenericTestUnit {
	
	@Autowired
	private JobPositionService jobTest;

	private JobPosition createNewJob() {
		JobPosition job = new JobPosition();
		return job;
	}

	@Test
	public void createdTest() {
		
		JobPosition job = createNewJob();
		job.setDescription("Created Date");
		
		jobTest.create(job);
		
		assertNotNull(job.getCreated());
		
	}
	
	@Test
	public void updatedTest() {
		
		JobPosition job = createNewJob();
		job.setDescription("Updated Date");
		
		jobTest.create(job);
		
		assertNotNull(job.getUpdated());
		
	}
	
	@Test
	public void firstUpdatedIsEqualToCreatedDateTest() {
		
		JobPosition job = createNewJob();
		job.setDescription("First Updated Is Esqual To Created Date");
		
		jobTest.create(job);
		
		assertEquals(job.getCreated(), job.getUpdated());
		
	}
	
	@Test
	public void newUpdatedDateAfterUpdateModelTest() {
		
		JobPosition job = createNewJob();
		job.setDescription("New Updated Date After Update Model");
		
		jobTest.create(job);
		
		Date firstUpdated = job.getUpdated();
		JobPosition updatedEvent = jobTest.find(job.getId());
		job.setDescription("Updated Models Attribute");
		
		jobTest.update(updatedEvent);
		
		JobPosition lastUpdate = jobTest.find(updatedEvent.getId());
		
		assertEquals(lastUpdate.getCreated(), firstUpdated);
		assertNotEquals(firstUpdated, lastUpdate.getUpdated());
		
	}
}
