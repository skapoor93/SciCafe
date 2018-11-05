package springrest.model.dao;

import java.util.List;

import springrest.model.Program;

/***************************************************************
 * Interface for Users Data Acess Object for defining methods for 
 * CRUD operations to Programs.
 * 
 * @author shivam
 *
 **************************************************************/
public interface ProgramDao {
	
	List<Program> getAllPrograms();
	
	Program getProgram(Long id);
	
	Program saveProgram(Program program);
	
	void deleteProgram(Program program);
	
	public List<Program> getProgramByProgramName(String programName);
}
