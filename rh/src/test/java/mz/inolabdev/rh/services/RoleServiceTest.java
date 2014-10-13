package mz.inolabdev.rh.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.exception.DuplicatePermissionException;
import mz.inolabdev.rh.exception.DuplicateRoleException;
import mz.inolabdev.rh.exception.RoleNotFoundException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class RoleServiceTest extends GenericTestUnit {

	private static final String DUP_ROLE_CREATE = "ROLE_ADMIN";
	private static final String ROLE_CREATE = "ROLE_TESTER";
	private static final String ROLE_MOD = "ROLE_TESTER_01";
	private static final String PERM_01 = "CTRL_STRATEGY_LIST_GET";
	private static final String PERM_02 = "CTRL_TEST_01";

	@Autowired
	private PermissionService permService;

	@Autowired
	private RoleService roleService;

	@Test(expected = DuplicateRoleException.class)
	public void insertDuplicateRoleWPerm() throws Exception {
		System.out.println("\n\n### Starting: insertDuplicateRoleWPerm()");

		persistPermition();
		
		Set<Permission> permissions = new HashSet<Permission>();
		Permission perm1 = permService.getPermission(PERM_01);
		Permission perm2 = permService.getPermission(PERM_02);

		permissions.add(perm1);
		permissions.add(perm2);

		Role role = new Role();
		role.setRolename(DUP_ROLE_CREATE);
		role.setPermissions(permissions);

		roleService.addRole(role);

		System.out.println("\n\n### Ending: insertDuplicateRoleWPerm()");
	}

	@Test(expected = DuplicateRoleException.class)
	public void insertDuplicateRoleWOPerm() throws Exception {
		System.out.println("\n\n### Starting: insertDuplicateRoleWOPerm()");
		
		Permission perm = new Permission();
		perm.setPermissionname(PERM_01);

		Permission perm2 = new Permission();
		perm2.setPermissionname(PERM_02);

		permService.addPermission(perm);
		permService.addPermission(perm2);
		
		Set<Permission> permissions = new HashSet<Permission>();
		permissions.add(perm);
		permissions.add(perm2);
		
		Role role = new Role();
		role.setRolename(DUP_ROLE_CREATE);
		role.setPermissions(permissions);
		roleService.addRole(role);
		System.out.println("\n\n### Ending: insertDuplicateRoleWOPerm()");
	}

	@Test(expected = RoleNotFoundException.class)
	public void getNonExistentRole() throws Exception {
		System.out.println("\n\n### Starting: getNonExistentRole()");
		Role role = roleService.getRole("ROGUE_ROLE");
		System.out.println("Role: [" + role.toString() + "]");
		System.out.println("\n\n### Ending: getNonExistentRole()");
	}

	@Test(expected = RoleNotFoundException.class)
	public void getNonExistentRoleByID() throws Exception {
		System.out.println("\n\n### Starting: getNonExistentRoleByID()");
		Role role = roleService.getRole(99L);
		System.out.println("Role: [" + role.toString() + "]");
		System.out.println("\n\n### Ending: getNonExistentRoleByID()");
	}

	@Test
	public void testRoleWOPerm() throws Exception {
		System.out.println("\n\n### Starting: testInsertRoleWOPerm()");
		Role role = new Role();
		role.setRolename(ROLE_CREATE);
		roleService.addRole(role);
		System.out.println("### Ending: testInsertRoleWOPerm()");

		System.out.println("\n\n### Starting: testGetRoleByIntWOPerm(): "
				+ ROLE_CREATE);
		role = roleService.getRole(ROLE_CREATE);
		System.out.println("  " + role.toString());
		System.out.println("### Ending: testGetRoleByIntWOPerm()");

		System.out.println("\n\n### Starting: testGetRoleByNameWOPerm(): "
				+ ROLE_CREATE);
		role = roleService.getRole(ROLE_CREATE);
		System.out.println("  " + role.toString());
		System.out.println("### Ending: testGetRoleByNameWOPerm()");

		System.out.println("\n\n### Starting: testUpdateRoleWOPerm()");
		role = roleService.getRole(ROLE_CREATE);
		System.out.println("  Init user: " + role.toString());
		role.setRolename(ROLE_MOD);
		roleService.updateRole(role);

		role = roleService.getRole(role.getId());
		System.out.println("  Mod user: " + role.toString());
		System.out.println("### Ending: testUpdateRoleWOPerm()");

		System.out.println("\n\n### Starting: testDeleteRoleWOPerm()");
		role = roleService.getRole(role.getId());
		System.out.println("  " + role.toString());
		roleService.deleteRole(role.getId());
		System.out.println("### Ending: testDeleteRoleWOPerm()\n\n\n");
	}

	@Test
	public void testRole() throws Exception {
		
		persistPermition();
		
		Set<Permission> permissions = new HashSet<Permission>();
		Permission perm1 = permService.getPermission(PERM_01);
		Permission perm2 = permService.getPermission(PERM_02);

		permissions.add(perm1);
		permissions.add(perm2);

		System.out.println("\n\n### Starting: testInsertRole()");
		Role role = new Role();
		role.setRolename(ROLE_CREATE);
		role.setPermissions(permissions);
		roleService.addRole(role);
		System.out.println("### Ending: testInsertRole()");

		System.out.println("\n\n### Starting: testGetRoleByName(): "
				+ ROLE_CREATE);
		role = roleService.getRole(ROLE_CREATE);
		System.out.println("  " + role.toString());
		printAllPermissions(role.getPermissions());
		System.out.println("### Ending: testGetRoleByName()");

		System.out.println("\n\n### Starting: testGetRoleByInt(): "
				+ ROLE_CREATE);
		role = roleService.getRole(role.getId());
		System.out.println("  " + role.toString());
		printAllPermissions(role.getPermissions());
		System.out.println("### Ending: testGetRoleByInt()");

		System.out.println("\n\n### Starting: testUpdateRole()");
		role = roleService.getRole(ROLE_CREATE);
		System.out.println("  Init user: " + role.toString());
		printAllPermissions(role.getPermissions());
		role.setRolename(ROLE_MOD);
		roleService.updateRole(role);
		role = roleService.getRole(role.getId());
		System.out.println("  Mod user: " + role.toString());
		printAllPermissions(role.getPermissions());
		System.out.println("### Ending: testUpdateRole()");

		System.out.println("\n\n### Starting: testDeleteRole()");
		role = roleService.getRole(role.getId());
		System.out.println("  " + role.toString());
		printAllPermissions(role.getPermissions());
		roleService.deleteRole(role.getId());
		System.out.println("### Ending: testDeleteRole()\n\n\n");
	}

	@Test
	public void testGetAllRoles() throws Exception {
		System.out.println("\n\n### Starting: testGetAllRoles()");
		List<Role> rolesList = roleService.getRoles();

		System.out.println("  List:");
		for (Role role : rolesList) {
			System.out.println("    " + role.toString());
			printAllPermissions(role.getPermissions());
		}
		System.out.println("### Ending: testGetAllRoles()\n\n");
	}

	public void printAllPermissions(Set<Permission> permissions)
			throws Exception {
		for (Permission perm : permissions) {
			System.out
					.println("      perm: [" + perm.getPermissionname() + "]");
		}
	}

	public void persistPermition() throws DuplicatePermissionException {
		
		Permission perm = new Permission();
		perm.setPermissionname(PERM_01);

		Permission perm2 = new Permission();
		perm2.setPermissionname(PERM_02);

		permService.addPermission(perm);
		permService.addPermission(perm2);
	}

}
