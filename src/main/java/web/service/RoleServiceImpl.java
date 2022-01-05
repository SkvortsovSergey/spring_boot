package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.RoleDao;
import web.model.Role;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl (RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role getRoleByName (String name) {
        return roleDao.getRoleByName(name);
    }

    @Override
    public Role getRoleById (int id) {
        return roleDao.getRoleById(id);
    }
    @Override
    public HashSet<Role> getSetOfRoles (String[] roleNames) {
        return Arrays.stream(roleNames).map(this::getRoleByName).collect(Collectors.toCollection(HashSet::new));

    }
    @Override
    public List<Role> getAllRoles () {
        return roleDao.getAllRoles();
    }
}
