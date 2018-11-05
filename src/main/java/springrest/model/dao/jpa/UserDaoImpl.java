package springrest.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springrest.model.User;
import springrest.model.dao.UserDao;

/***************************************************************
 * Repository for Users implementing UserDao to implement 
 * functionalities of methods for CRUD operations to Users.
 * 
 * @author shivam
 *
 **************************************************************/
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User getUser( Long id )
    {
        return entityManager.find( User.class, id );
    }

    @Override
    public List<User> getUsers()
    {
        return entityManager.createQuery( "from User order by id", User.class )
            .getResultList();
    }

    @Override
    @Transactional
    public User saveUser( User user )
    {
        return entityManager.merge( user );
    }

	@Override
	public List<User> getUserByUsername(String username) {
		List<User> users=  entityManager.createQuery("select u from User u where u.username = :username").setParameter("username", username).getResultList();
		return users;
	}

}
