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
import springrest.model.Program;
import springrest.model.dao.ProgramDao;

@RestController
@RequestMapping("/programs")
public class ProgramController {

	@Autowired
	ProgramDao programDao;

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllPrograms()
	{
		ApiErrorResponse errorResponse = null;
		List<Program> result = null;
		try {
			result = programDao.getAllPrograms();
			if(result == null || result.isEmpty())
			{
				errorResponse = new ApiErrorResponse("Currently there are no programs");
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProgramById(@PathVariable Long id)
	{
		ApiErrorResponse errorResponse = null;
		Program result = null;
		try {
			result = programDao.getProgram(id);
			if(result == null) 
			{
				errorResponse = new ApiErrorResponse("There is no program with this id");
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addProgram(@RequestBody Program program)
	{
		Program result = null;
		ApiErrorResponse response = null;
		try {
			List<Program> temp = programDao.getProgramByProgramName(program.getProgramName());
			if(temp == null || temp.isEmpty())
			result = programDao.saveProgram(program);
			else {
				response = new ApiErrorResponse("Duplicate program");
				return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e)
		{
			response = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(result, HttpStatus.CREATED);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProgram(@PathVariable Long id, @RequestBody Program program)
	{
		ApiErrorResponse errorResponse = null;
		try {
			if(programDao.getProgram(id)==null)
			{
				errorResponse = new ApiErrorResponse("There is no program with this id");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			}else if(!programDao.getProgramByProgramName(program.getProgramName()).isEmpty())
			{
				errorResponse = new ApiErrorResponse("Program name is not unique");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			}
			program.setId(id);
			programDao.saveProgram(program);
		}catch(Exception e)
		{
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProgram(@PathVariable Long id)
	{
		ApiErrorResponse errorResponse = null;
		try {
			Program program = programDao.getProgram(id);
			if(program == null)
			{
				errorResponse = new ApiErrorResponse("There is no program with this id");
				return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
			}
			programDao.deleteProgram(program);
		}catch(Exception e)
		{
			errorResponse = new ApiErrorResponse(e.getMessage());
			return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
