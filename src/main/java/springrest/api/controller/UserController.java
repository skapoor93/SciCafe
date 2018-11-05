package springrest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springrest.hibernate.util.ApiErrorResponse;
import springrest.model.User;
import springrest.model.dao.UserDao;

/********************************************
 * Controller class for managing user object.
 * 
 * @author shivam
 *
 *******************************************/
@RestController
@RequestMapping("/users")
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
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUser( @PathVariable Long id )
	{
		User result = null;
		ApiErrorResponse errorResponse = null;
		try {
			result = userDao.getUser(id);
			if(result == null) {
				errorResponse = new ApiErrorResponse("No User with this ID is present");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			}
				
		}catch(Exception e)
		{
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	/**
	 * Rest API to get all the users stored in the database.
	 * 
	 * @return List of all the Users stored in the database.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getUsers()
	{
		ApiErrorResponse errorResponse = null;
		List<User> result = null;
		try {
			result = userDao.getUsers();
			if(result == null || result.isEmpty()) {
				errorResponse = new ApiErrorResponse("No user is present");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	/**
	 * Rest API for registration of User.
	 * 
	 * @param user
	 * @return ResponseEntity<String> 
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addUser(@RequestBody User user)
	{
		User result = null;
		ApiErrorResponse errorResponse = null;
		try
		{
			List<User> temp = userDao.getUserByUsername(user.getUsername());
			if(temp == null || temp.isEmpty())
			result = userDao.saveUser(user);
			else {
				errorResponse = new ApiErrorResponse("User already exists");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e)
		{
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		return new ResponseEntity<Object>(result,HttpStatus.CREATED);
	}
}
