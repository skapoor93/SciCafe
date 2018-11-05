package springrest.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import springrest.model.Role;
import springrest.model.dao.RolesDao;

@Repository
public class RolesDaoImpl implements RolesDao{

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	@Transactional
	public Role saveRole(Role role) {
		return entityManager.merge( role );
	}

	@Override
	public List<Role> getRoleByName(String name) {
		List<Role> roles = entityManager.createQuery("select r from Role r where r.name = :name").setParameter("name", name).getResultList();
		
		return roles;
	}
}
