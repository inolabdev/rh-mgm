package mz.inolabdev.rh.services;

import static org.junit.Assert.assertNotNull;
import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Permission;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermitionServiceTest extends GenericTestUnit {

	private static final String PERM_01 = "CTRL_TEST";
	private static final String PERM_02 = "CTRL_TEST_01";

	@Autowired
	private PermissionService permissionService;

	@Test
	public void createTest() {

		Permission permission = new Permission();
		permission.setPermissionname(PERM_01);

		Permission permission_2 = new Permission();
		permission.setPermissionname(PERM_02);

		permissionService.create(permission);
		permissionService.create(permission_2);

		assertNotNull(permission.getId());
		assertNotNull(permission_2.getId());
	}
}
