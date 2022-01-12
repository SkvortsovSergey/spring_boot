package web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.dao.RoleRepository;
import web.model.Role;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByRole (String name) {
        return roleRepository.getByName(name);
    }

    @Override
    public Role getRoleById (Integer id) {
        return roleRepository.getRoleById(id);
    }

    @Override
    public Set<Role> getAllRolesSet () {
        return  roleRepository.getAll();
    }

    @Override
    public HashSet<Role> getSetOfRoles (String[] roleNames) {
        return Arrays.stream(roleNames).map(this::getRoleByRole).collect(Collectors.toCollection(HashSet::new));
    }
    @Override
    public HashSet<Role> getSetOfRoles (Set<String> roleNames) {
        return roleNames.stream().map(this::getRoleByRole).collect(Collectors.toCollection(HashSet::new));
    }


}
