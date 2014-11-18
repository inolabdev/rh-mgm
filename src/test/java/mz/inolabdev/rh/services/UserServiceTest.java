package mz.inolabdev.rh.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.entity.User;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceTest extends GenericTestUnit {

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

	@Test
	public void createUser() {

		storeRoles();

		User user = new User();
		user.setUsername("inolab");
		user.setPassword("password");
		user.setEnabled(true);

		for (Role role : roleService.getAll()) {

			user.addRole(role);
		}

		userService.create(user);

		assertNotNull(user.getId());
		assertEquals(2, user.getPermissions().size());
	}

	@Test
	public void findUserByName() {

		storeRoles();

		User user = new User();
		user.setUsername("inolab");
		user.setPassword("password");
		user.setEnabled(true);

		for (Role role : roleService.getAll()) {

			user.addRole(role);
		}

		userService.create(user);
		
		User found = userService.find(user.getUsername());
		
		assertEquals(user.getId(), found.getId());
	}
	
	@Test(expected = NullPointerException.class)
	public void findUser() {
		
		userService.find("inolab-test");
	}
}
