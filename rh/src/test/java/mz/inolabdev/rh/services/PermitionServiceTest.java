package mz.inolabdev.rh.services;

import java.util.List;
import java.util.Set;

import mz.inolabdev.rh.GenericTestUnit;
import mz.inolabdev.rh.entity.Permission;
import mz.inolabdev.rh.entity.Role;
import mz.inolabdev.rh.exception.DuplicatePermissionException;
import mz.inolabdev.rh.exception.PermissionNotFoundException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PermitionServiceTest extends GenericTestUnit {

	private static final String DUP_PERM_CREATE  = "CTRL_STRATEGY_LIST_GET";
    private static final String PERM_CREATE      = "CTRL_TEST";
    private static final String PERM_MOD         = "CTRL_TEST_01";
	
	@Autowired
	private PermissionService permissionService;

	@Test(expected=DuplicatePermissionException.class) 
    public void insertDuplicatePermission() throws Exception {
        System.out.println("\n\n### Starting: insertDuplicatePermission()");
        
        Permission perm = new Permission();
        perm.setPermissionname(DUP_PERM_CREATE);
        
        Permission perm2 = new Permission();
        perm2.setPermissionname(DUP_PERM_CREATE);
        
        permissionService.addPermission(perm);
        permissionService.addPermission(perm2);
        
        System.out.println("\n\n### Ending: insertDuplicatePermission()");
    }

    @Test(expected=PermissionNotFoundException.class)
    public void getNonExistentPermission() throws Exception {
        System.out.println("\n\n### Starting: getNonExistentPermission()");
        
        Permission perm = permissionService.getPermission("ROGUE_PERM");
        System.out.println("Permission: [" + perm.toString() +"]");
        System.out.println("\n\n### Ending: getNonExistentPermission()");
    }
    
    @Test(expected=PermissionNotFoundException.class)
    public void getNonExistentPermissionByID() throws Exception {
        System.out.println("\n\n### Starting: getNonExistentPermissionByID()");
        
        Permission perm = permissionService.getPermission(99L);
        System.out.println("Permission: [" + perm.toString() +"]");
        System.out.println("\n\n### Ending: getNonExistentPermissionByID()");
    }
    
    @Test
    public void testRole() throws Exception {
        System.out.println("\n\n### Starting: testInsertPermission()");

        Permission perm = new Permission();
        perm.setPermissionname(PERM_CREATE);
        
        permissionService.addPermission(perm);
        
        System.out.println("### Ending: testInsertPermission()");
    
        
        
        System.out.println("\n\n### Starting: testGetPermissionByName(): " + PERM_CREATE);
        
        perm = permissionService.getPermission(PERM_CREATE);
        System.out.println("  " + perm.toString());

        System.out.println("### Ending: testGetPermissionByName()");

        
        
        System.out.println("\n\n### Starting: testGetPermissionByInt(): " + PERM_CREATE);
        
        perm = permissionService.getPermission(perm.getId());
        System.out.println("  " + perm.toString());

        System.out.println("### Ending: testGetPermissionByInt()");


        
        System.out.println("\n\n### Starting: testUpdatePermission()");
        
        perm = permissionService.getPermission(PERM_CREATE);
        System.out.println("  Init perm: " + perm.toString());

        perm.setPermissionname(PERM_MOD);;
        permissionService.updatePermission(perm);
        
        perm = permissionService.getPermission(perm.getId());
        System.out.println("  Mod perm: " + perm.toString());

        System.out.println("### Ending: testUpdatePermission()");

        
        
        System.out.println("\n\n### Starting: testDeletePermission()");

        perm = permissionService.getPermission(perm.getId());
        System.out.println("  " + perm.toString());

        permissionService.deletePermission(perm.getId());

        System.out.println("### Ending: testDeletePermission()\n\n\n");
    }

    @Test
    public void testGetAllPermission() throws Exception {
        System.out.println("\n\n### Starting: testGetAllPermission()");
        List<Permission> permsList = permissionService.getPermissions();
        
        System.out.println("  List:");
        for (Permission perm : permsList) {
            System.out.println("    " + perm.toString());
            printAllRoles(perm.getPermRoles());
        }
        System.out.println("### Ending: testGetAllPermission()\n\n");
    }

    public void printAllRoles(Set <Role> roles) throws Exception {
        for (Role role : roles) {   
            System.out.println("      role: [" + role.getAuthority() + "]"); 
        }
    }
    
    @Test
    public void testInsertPermission() throws Exception {
        System.out.println("\n\n### Starting: testInsertPermission()");
        Permission perm = new Permission();
        perm.setPermissionname(PERM_MOD);
        permissionService.addPermission(perm);
        perm = permissionService.getPermission(PERM_MOD);
        System.out.println("  " + perm.toString());
        System.out.println("### Ending: testInsertPermission()");
    }
    
    @Test
    public void testDeletePermission() throws Exception {
        System.out.println("\n\n### Starting: testDeletePermission()");
        
        Permission perm = new Permission();
        perm.setPermissionname(PERM_MOD);
        permissionService.addPermission(perm);

        Permission getPerm = permissionService.getPermission(PERM_MOD);
        System.out.println("  " + getPerm.toString());
        permissionService.deletePermission(getPerm.getId());

        System.out.println("### Ending: testDeletePermission()\n\n\n");
    }
}
