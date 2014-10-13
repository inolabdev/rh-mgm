delimiter //
DROP PROCEDURE IF EXISTS createPermission;
DROP PROCEDURE IF EXISTS createRole;
DROP PROCEDURE IF EXISTS createRoleHasPermission;
DROP PROCEDURE IF EXISTS createUser;
DROP PROCEDURE IF EXISTS createUserHasRole;
DROP PROCEDURE IF EXISTS createUserEntry;

create procedure createPermission($name varchar(50))
begin
    insert into permissions (created, updated, permissionname) values (Now(), Now(), $name);
end //

create procedure createRole($name varchar(50), out $id int)
begin
    insert into roles (created, updated, rolename) values (Now(), Now(), $name);
    set $id := last_insert_id();
end //

create procedure createRoleHasPermission($role_id smallint, $perm_name varchar(50))
begin
    declare _perm_id int;
    select id from permissions where permissionname = $perm_name into _perm_id;
    insert into role_permissions (role_id, permission_id) values ($role_id, _perm_id);
end //

create procedure createUserEntry($name varchar(50), out $id int)
begin
    insert into users (created, updated, username, password, enabled) values (Now(), Now(), $name, 'password', 1);
    set $id := last_insert_id();
end //

create procedure createUserHasRole($user_id int, $role_id smallint)
begin
    insert into user_roles (user_id, role_id) values ($user_id, $role_id);
end //

delimiter ;