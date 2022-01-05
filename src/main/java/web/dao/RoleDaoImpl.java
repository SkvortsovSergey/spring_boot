package web.dao;

import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager () {
        return this.entityManager;
    }


    @Override
    public Role getRoleByName (String name) {
           return entityManager.createQuery("SELECT r FROM Role r WHERE r.role=:name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult();


    }

    @Override
    public List<Role> getAllRoles () {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role getRoleById (int id) {
        return getEntityManager().find(Role.class, id);
    }
}