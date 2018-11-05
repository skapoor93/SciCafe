package springrest.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import springrest.model.Program;
import springrest.model.dao.ProgramDao;

/**
 * 
 * @author shivam
 *
 */
@Repository
public class ProgramDaoImpl implements ProgramDao{
	
	@PersistenceContext
    private EntityManager entityManager;
	
	/**
	 * 
	 */
	@Override
	public List<Program> getAllPrograms() {
		return entityManager.createQuery("from Program order by id", Program.class).getResultList();
	}

	/**
	 * 
	 */
	@Override
	public Program getProgram(Long id) {
		return entityManager.find(Program.class, id);
	}

	/**
	 * 
	 */
	@Override
	@Transactional
	public Program saveProgram(Program program) {
		return entityManager.merge(program);
	}

	@Override
	@Transactional
	public void deleteProgram(Program program) {
		
		entityManager.remove(program);
	}

	@Override
	public List<Program> getProgramByProgramName(String programName) {
		List<Program> result = entityManager.createQuery("select p from Program p where p.programName = :programName").setParameter("programName", programName).getResultList();
		return result;
	}
}