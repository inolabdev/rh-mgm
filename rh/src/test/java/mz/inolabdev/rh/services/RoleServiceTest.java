package mz.inolabdev.rh.services;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.entity.Role;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceTest extends GenericTestUnit {

	private static final String ROLE_CREATE_01 = "ROLE_ADMIN";

	private static final String PERM_01 = "CTRL_STRATEGY_LIST_GET";
	private static final String PERM_02 = "CTRL_TEST_01";

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

	private Role newRole() {

		Permission perm01 = newPermition(PERM_01);
		Permission perm02 = newPermition(PERM_02);

		Set<Permission> perms = new HashSet<Permission>();
		perms.add(perm01);
		perms.add(perm02);

		Role role = new Role();
		role.setPermissions(perms);
		role.setRolename(ROLE_CREATE_01);

		roleService.create(role);

		return role;
	}

	@Test
	public void testCreateRole() {

		Role role = newRole();

		assertNotNull(role.getId());
		assertEquals(2, role.getPermissions().size());
	}
	
	@Test
	public void testFindRoleById() {

		Role r = newRole();
		
		Role role = roleService.find(r.getId());

		assertNotNull(role.getId());
	}
	
	@Test
	public void testFindRoleByName() {
		
		newRole();

		Role role = roleService.find(ROLE_CREATE_01);

		assertNotNull(role.getId());
	}

}
