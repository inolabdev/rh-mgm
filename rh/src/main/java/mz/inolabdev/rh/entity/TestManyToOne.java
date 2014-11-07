package mz.inolabdev.rh.entity;

import mz.inolabdev.rh.entity.EnumUtils.DefaultStatus;

public class TestManyToOne {

	public static void main(String[] args) {
		Employee em = new Employee();
		em.setName("jorge");
		
		Employee em1 = new Employee();
		em1.setName("eusebio");
		
		ContactPoint cp = new ContactPoint();
		ContactPoint cp1 = new ContactPoint();
		
		
		//cp.setEmployee(em);
		//cp1.setEmployee(em);
		
		//cp.setEmployee(em1);
		
		
		
	
		System.out.println("enum size(): "+ DefaultStatus.values().toString());
		//System.out.println("list of employees: "+ cp1.getEmployee().getName());
	}

}
