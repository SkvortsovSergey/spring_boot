package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager () {
        return this.entityManager;
    }


    @Override
    public Role getByName (String name) {
        Role role = null;
        try {
            role = getEntityManager().createQuery("SELECT r FROM Role r WHERE r.role=:name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Роли с таким именем не существует!");
        }
        return role;
    }

    @Override
    public Set<Role> getAll () {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Role getRoleById (Integer id) {
        return getEntityManager().find(Role.class, id);
    }
}