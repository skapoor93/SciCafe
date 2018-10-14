package springrest.model.dao;

import java.util.List;

import springrest.model.User;

/***************************************************************
 * Interface for Users Data Acess Object for defining methods for 
 * CRUD operations to Users.
 * 
 * @author shivam
 *
 **************************************************************/
public interface UserDao {

    User getUser( Long id );

    List<User> getUsers();

    User saveUser( User user );

}
