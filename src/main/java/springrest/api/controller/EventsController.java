package springrest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springrest.model.Event;
import springrest.model.dao.EventDao;

/************************************************************************
 * Controller class to manage Events created by different Users which are
 * stored in the database.
 *
 * @author shivam
 *
 ***********************************************************************/
@RestController
public class EventsController {

	@Autowired
	private EventDao eventsDao;

	/**
	 * Rest API to get all the events from database which are created
	 * by different users.
	 *
	 * @return list of all the events stored in the database.
	 */
	@RequestMapping(value = "/events", method = RequestMethod.GET)
	public List<Event> getEvents()
	{
		return eventsDao.getAllEvents();
	}

}
