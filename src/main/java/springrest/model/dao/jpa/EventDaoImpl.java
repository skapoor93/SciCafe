package springrest.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import springrest.model.Event;
import springrest.model.dao.EventDao;

/***************************************************************
 * Repository for Events implementing EventDao to implement 
 * functionalities of methods for CRUD operations to Events.
 * 
 * @author shivam
 *
 **************************************************************/
@Repository
public class EventDaoImpl implements EventDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Event> getAllEvents() {
		return entityManager.createQuery("from Event order by id", Event.class).getResultList();
	}

	@Override
	@Transactional
	public Event createEvent(Event event) {
		return entityManager.merge(event);
	}

	@Override
	public Event getEventById(Long id) {
		return entityManager.find(Event.class, id);
	}

}
