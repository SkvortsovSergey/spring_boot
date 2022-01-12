package web.service;

import web.model.Role;

import java.util.HashSet;
import java.util.Set;

public interface RoleService {

    Role getRoleByRole (String name);

    Role getRoleById (Integer id);

    HashSet<Role> getSetOfRoles (String[] roleNames);

    Set<Role> getAllRolesSet ();


    HashSet<Role> getSetOfRoles (Set<String> roleNames);
}
