package springrest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springrest.model.User;
import springrest.model.dao.UserDao;

/********************************************
 * Controller class for managing user object.
 * 
 * @author shivam
 *
 *******************************************/
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    /**
     * Rest API to get a user from database based on the id of the user
     * passed to the API
     * 
     * @param id
     * @return an object of User class 
     */
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public User getUser( @PathVariable Long id )
    {
        return userDao.getUser( id );
    }

    /**
     * Rest API to get all the users stored in the database.
     * 
     * @return List of all the Users stored in the database.
     */
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getUsers()
    {
        return userDao.getUsers();
    }

}
