package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import java.util.Set;

@Repository
public interface RoleRepository  {

    Role getRoleById (Integer id);


    Set<Role> getAll();
    Role getByName(String roleName);


}
