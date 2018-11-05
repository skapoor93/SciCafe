package springrest.model.dao;

import java.util.List;

import springrest.model.Role;

/***************************************************************
 * Interface for Users Data Acess Object for defining methods for 
 * CRUD operations to Roles.
 * 
 * @author shivam
 *
 **************************************************************/
public interface RolesDao {

	Role saveRole(Role role);
	
	List<Role> getRoleByName(String name);
}
