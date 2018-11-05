package springrest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springrest.hibernate.util.ApiErrorResponse;
import springrest.model.Role;
import springrest.model.dao.RolesDao;

/********************************************
 * Controller class for managing user object.
 * 
 * @author shivam
 *
 *******************************************/
@RestController
@RequestMapping("/roles")
public class RolesController {

	@Autowired
	RolesDao rolesDao;

	/**
	 * Rest API for creating Role
	 * 
	 * @param role
	 * @return ResponseEntity<Object>
	 */
	@RequestMapping(value = "/addrole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addRole(@RequestBody Role role)
	{
		ApiErrorResponse errorResponse = null;
		Role result = null;
		try
		{
			if(rolesDao.getRoleByName(role.getName())==null)
				result = rolesDao.saveRole(role);
			else
			{
				errorResponse = new ApiErrorResponse("Role already exists");
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
