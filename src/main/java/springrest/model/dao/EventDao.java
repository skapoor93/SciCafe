package springrest.model.dao;

import java.util.List;

import springrest.model.Event;

/***************************************************************
 * Interface for Events Data Access Object for defining methods 
 * for CRUD operations to Events.
 * 
 * @author shivam
 *
 **************************************************************/
public interface EventDao {

	List<Event> getAllEvents();
}
