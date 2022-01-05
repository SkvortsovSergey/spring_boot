package web.service;

import web.model.Role;

import java.util.HashSet;
import java.util.List;

public interface RoleService {

    Role getRoleByName (String name);

    Role getRoleById (int id);

    HashSet<Role> getSetOfRoles (String[] roleNames);

    List<Role> getAllRoles ();
}
