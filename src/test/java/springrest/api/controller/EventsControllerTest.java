package springrest.api.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
@ContextConfiguration(locations = { "classpath:springrest-servlet.xml", "classpath:applicationContext.xml" })
public class EventsControllerTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@BeforeClass
	void setup()
	{	
		this.mockMvc = MockMvcBuilders.webAppContextSetup( wac ).build();
	}
	
	@Test
    void getEvents() throws Exception
    {
        this.mockMvc.perform( get( "/events" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath(  "$[0].eventName" ).value( "Robotics" ) );
    }
	
	@Test
    void getEventAttendees() throws Exception
    {
        this.mockMvc.perform( get( "/events/getattendees/37" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath(  "$[0].username" ).value( "chengyu" ) );
    }
	
	@Test
    void getEventNoAttendees() throws Exception
    {
        this.mockMvc.perform( get( "/events/getattendees/45" ) )
            .andExpect( status().isBadRequest() )
            .andExpect( jsonPath(  "$.message" ).value( "No user attended the event" ) );
    }
	
	@Test
    void approveEvent() throws Exception
    {
		this.mockMvc
        .perform( put( "/events/approveevent/30/37" ).contentType( "application/json" )
            )
        .andExpect( status().is2xxSuccessful() );
    }
	
	@Test
    void approveEventFailure() throws Exception
    {
		this.mockMvc
        .perform( put( "/events/approveevent/31/37" ).contentType( "application/json" )
            )
        .andExpect( status().isBadRequest() )
        .andExpect(jsonPath("$.message").value("User is not authorized"));
    }
	
	@Test
    void rejectEvent() throws Exception
    {
		this.mockMvc
        .perform( put( "/events/rejectevent/30/37" ).contentType( "application/json" )
            )
        .andExpect( status().is2xxSuccessful() );
    }
	
	@Test
    void rejectEventFailure() throws Exception
    {
		this.mockMvc
        .perform( put( "/events/rejectevent/31/37" ).contentType( "application/json" )
            )
        .andExpect( status().isBadRequest() )
        .andExpect(jsonPath("$.message").value("User is not authorized"));
    }
	
	@Test
    void eventAttendee() throws Exception
    {
		this.mockMvc
        .perform( put( "/events/eventattendee/31/37" )
            )
        .andExpect( status().isOk() );
    }
	
	@Test
	void addEvent() throws Exception
	{
		this.mockMvc
        .perform( post( "/events/addevent/31" ).contentType( "application/json" )
            .content( "{\"eventName\":\"abc\", \"eventDescription\": \"abcd\", \"startTime\": \"10-24-2018\", "
            		+ "\"endTime\": \"10-28-2018\", \"location\": \"Engineering and Technology\"}" ) )
        .andExpect( status().is2xxSuccessful() );
	}
}
