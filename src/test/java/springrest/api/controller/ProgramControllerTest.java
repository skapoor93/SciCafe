package springrest.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:springrest-servlet.xml",
"classpath:applicationContext.xml" })
public class ProgramControllerTest extends AbstractTransactionalTestNGSpringContextTests{

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeClass
	void setup()
	{
		this.mockMvc = MockMvcBuilders.webAppContextSetup( wac ).build();
	}

	@Test
	public void getAllPrograms() throws Exception
	{
		this.mockMvc.perform( get( "/programs" ) )
		.andExpect( status().isOk() )
		.andExpect( jsonPath( "$[0].programName" ).value( "LSAMP" ) );
	}

	@Test
	public void getProgramById() throws Exception
	{
		this.mockMvc.perform( get( "/programs/34" ) )
		.andExpect( status().isOk() )
		.andExpect( jsonPath( "$.programName" ).value( "LSAMP" ) );
	}

	@Test
	public void addProgram() throws Exception
	{
		this.mockMvc
		.perform( post( "/programs" ).contentType( "application/json" )
				.content("{\"programName\": \"LSAMP5\",\"programFullName\": \"LSAMP5 department\",\"programDescription\": \"LSAMP5 Description\"}" ) )
		.andExpect( status().is2xxSuccessful() );
	}

	@Test
	public void updateProgram() throws Exception
	{
		this.mockMvc
		.perform( put( "/programs/58" ).contentType( "application/json" )
				.content("{\"programName\": \"LSAMP4_2\",\"programFullName\": \"LSAMP4_2 department\",\"programDescription\": \"LSAMP4_2 Description\"}" ) )
		.andExpect( status().is2xxSuccessful() );
	}

	@Test
	public void updateProgramFailure1() throws Exception
	{
		this.mockMvc
		.perform( put( "/programs/90" ).contentType( "application/json" )
				.content("{\"programName\": \"LSAMP4_2\",\"programFullName\": \"LSAMP4_2 department\",\"programDescription\": \"LSAMP4_2 Description\"}" ) )
		.andExpect( status().isBadRequest() )
		.andExpect(jsonPath("$.message").value("There is no program with this id"));
	}

	@Test
	public void updateProgramFailure2() throws Exception
	{
		this.mockMvc
		.perform( put( "/programs/58" ).contentType( "application/json" )
				.content("{\"programName\": \"LSAMP4_1\",\"programFullName\": \"LSAMP4_1 department\",\"programDescription\": \"LSAMP4_2 Description\"}" ) )
		.andExpect( status().isBadRequest() )
		.andExpect(jsonPath("$.message").value("Program name is not unique"));
	}

	@Test
	public void deleteProgram() throws Exception
	{
		this.mockMvc
		.perform( delete( "/programs/58" ))
		.andExpect( status().isOk() );
	}
	
	@Test
	public void deleteProgramFailure() throws Exception
	{
		this.mockMvc
		.perform( delete( "/programs/90" ))
		.andExpect( status().isBadRequest())
		.andExpect(jsonPath("$.message").value("There is no program with this id"));
	}
}
