package mz.inolabdev.rh.services;

import static org.junit.Assert.*;

import java.util.Calendar;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.List;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Department;
import mz.inolabdev.rh.entity.Employee;
import mz.inolabdev.rh.entity.JobPosition;
import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.entity.User;

import org.junit.Assert;
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
	
	private static final String ROLE_CREATE_01 = "ROLE_ADMIN";
	private static final String ROLE_CREATE_02 = "ROLE_CLIENT";

	private static final String PERM_01 = "CTRL_TEST_01";
	private static final String PERM_02 = "CTRL_TEST_02";
	
	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permService;

	@Autowired
	private RoleService roleService;

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
	
	private Permission newPermition(String permition) {

		Permission perm = new Permission();
		perm.setPermissionname(permition);

		permService.create(perm);

		return perm;
	}

	private void storeRoles() {

		Permission perm01 = newPermition(PERM_01);
		Permission perm02 = newPermition(PERM_02);

		Set<Permission> perms = new HashSet<Permission>();
		perms.add(perm01);
		perms.add(perm02);

		Role role01 = new Role();
		role01.addPermition(perm01);
		role01.addPermition(perm02);
		role01.setRolename(ROLE_CREATE_01);

		Role role02 = new Role();
		role02.addPermition(perm01);
		role02.setRolename(ROLE_CREATE_02);

		roleService.create(role01);
		roleService.create(role02);
	}
	
	private User createUser() {
		
		storeRoles();

		User user = new User();
		user.setUsername("inolab");
		user.setPassword("password");
		user.setEnabled(true);

		for (Role role : roleService.getAll()) {

			user.addRole(role);
		}
		
		return user;
	}

	@Test
	public void createTest() {

		Employee employee = creteNewEmployeeTest();

		Employee employeeSaved = employeeService.create(employee);

		assertNotNull(employeeSaved);
		assertNotNull(employeeSaved.getId());
	}
	
	@Test
	public void allWhereUserIdIsNullTest() {

		Employee employee_01 = creteNewEmployeeTest();
		employee_01.setName("No User");
		
		Employee employee_02 = creteNewEmployeeTest();
		User userLogin_01 = createUser();
		userService.create(userLogin_01);
		employee_02.setName("User Login 1");
		employee_02.setUserLogin(userLogin_01);
		
		Employee employee_03 = creteNewEmployeeTest();
		User userLogin_02 = createUser();
		userLogin_02.setUsername("inolab02");
		userLogin_02.setEnabled(true);
		userService.create(userLogin_02);
		employee_03.setName("User Login 2");
		employee_03.setUserLogin(userLogin_02);

		employeeService.create(employee_01);
		employeeService.create(employee_02);
		employeeService.create(employee_03);
		
		List<Employee> haveUserIdNull = employeeService.allWhereUserIdIsNull();

		assertEquals(1, haveUserIdNull.size());
		assertTrue(haveUserIdNull.contains(employee_01));
	}

	@Test
	public void getAllTest() {
		this.createTest();
		List<Employee> all = employeeService.getAll();

		Assert.assertNotNull(all);
		Assert.assertTrue(all.size() > 0);
	}

}
