package springrest.api.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import springrest.model.Event;
import springrest.model.Role;
import springrest.model.User;
import springrest.model.dao.EventDao;
import springrest.model.dao.UserDao;

/************************************************************************
 * Controller class to manage Events created by different Users which are
 * stored in the database.
 *
 * @author shivam
 *
 ***********************************************************************/
@RestController
@RequestMapping("/events")
public class EventsController {

	@Autowired
	private EventDao eventsDao;

	@Autowired
	private UserDao userDao;

	/**
	 * Rest API to get all the events from database which are created
	 * by different users.
	 *
	 * @return list of all the events stored in the database.
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getEvents()
	{
		ApiErrorResponse errorResponse = null;
		List<Event> result = null;
		try {
			result = eventsDao.getAllEvents();
			if(result == null || result.isEmpty())
			{
				errorResponse = new ApiErrorResponse("Currently there are no events");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e)
		{
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/addevent/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createEvent(@PathVariable("userId") Long userId,@RequestBody Event event)
	{
		User user = null;
		Event result = null;
		try {
			user = userDao.getUser(userId);
			event.setSubmittedBy(user);
			result = eventsDao.createEvent(event);
		}catch(Exception e)
		{
			ApiErrorResponse errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/approveevent/{userId}/{eventId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> approveEvent(@PathVariable Long userId, @PathVariable("eventId") Long eventId)
	{
		ApiErrorResponse errorResponse = null;
		User user = null;
		Event event = null;
		try {
			user = userDao.getUser(userId);
			Set<Role> roles = user.getRoles();
			boolean flag = false;
			for(Role role: roles)
			{
				if(role.getName().equals("administrator"))
				{
					flag = true;
				}
			}
			if(flag)
			{
				event = eventsDao.getEventById(eventId);
				event.setPosted(true);
				eventsDao.createEvent(event);
			}else {
				errorResponse = new ApiErrorResponse("User is not authorized");
				return new ResponseEntity<Object>(errorResponse,HttpStatus.BAD_REQUEST); 
			}
		}catch(Exception e)
		{
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/rejectevent/{userId}/{eventId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> rejectEvent(@PathVariable Long userId, @PathVariable("eventId") Long eventId)
	{
		ApiErrorResponse errorResponse = null;
		User user = null;
		Event event = null;
		try {
			user = userDao.getUser(userId);
			Set<Role> roles = user.getRoles();
			boolean flag = false;
			for(Role role: roles)
			{
				if(role.getName().equals("administrator"))
				{
					flag = true;
				}
			}
			if(flag)
			{
				event = eventsDao.getEventById(eventId);
				if(event.isPosted())
				{
					event.setPosted(false);
					eventsDao.createEvent(event);
				}
			}else {
				errorResponse = new ApiErrorResponse("User is not authorized");
				return new ResponseEntity<Object>(errorResponse,HttpStatus.BAD_REQUEST); 
			}
		}catch(Exception e)
		{
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/eventattendee/{userId}/{eventId}", method = RequestMethod.PUT)
	public ResponseEntity<Object> eventAttendee(@PathVariable("userId") Long userId, @PathVariable("eventId") Long eventId)
	{
		User user = null;
		Event event = null;
		Set<User> attendee = new HashSet<>();
		try {
			user = userDao.getUser(userId);
			event = eventsDao.getEventById(eventId);
			attendee.add(user);
			event.setUsersAttended(attendee);
			eventsDao.createEvent(event);
		}catch(Exception e)
		{
			ApiErrorResponse errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/getattendees/{eventId}", method = RequestMethod.GET)
	public ResponseEntity<Object> getEventAttendees(@PathVariable("eventId") Long eventId)
	{
		ApiErrorResponse errorResponse = null;
		Event event = null;
		Set<User> attendee = null;
		try {
			event = eventsDao.getEventById(eventId);
			attendee = event.getUsersAttended();
			if(attendee == null || attendee.isEmpty()) {
				errorResponse = new ApiErrorResponse("No user attended the event");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e)
		{
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>(attendee, HttpStatus.OK);
	}
}
