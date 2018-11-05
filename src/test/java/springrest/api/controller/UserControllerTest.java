package springrest.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
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
class UserControllerTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @BeforeClass
    void setup()
    {
        this.mockMvc = MockMvcBuilders.webAppContextSetup( wac ).build();
    }

    @Test
    void getUserTest() throws Exception
    {
        this.mockMvc.perform( get( "/users/39" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "username" ).value( "cysun" ) );
    }
    
    @Test
    void getUsers() throws Exception
    {
    	
        this.mockMvc.perform( get( "/users" ) ).andExpect( status().isOk() )
            .andExpect( jsonPath(  "$[0].username" ).value( "chengyu" ) );
    }
    
//    @Test
//    void getUsers() throws Exception
//    {
//        this.mockMvc.perform( get( "/users" ) )
//            .andExpect( status().isOk() )
//            .andExpect( jsonPath( "$[0].username" ).value( "admin" ) );
//    }
//
    @Test
    void getUsers2() throws Exception
    {
        this.mockMvc.perform( get( "/users" ) )
            .andExpect( status().isOk() )
            .andExpect( jsonPath( "$.length()" )
                .value( Matchers.greaterThanOrEqualTo( 2 ) ) );
    }
//
    @Test
    void addUser() throws Exception
    {
        this.mockMvc
            .perform( post( "/users/registration" ).contentType( "application/json" )
                .content( "{\"username\":\"chengyu\"}" ) )
            .andExpect( status().is4xxClientError() )
            .andExpect(jsonPath("$.message").value("User already exists"));
    }
//
    @Test
    @Rollback(false)
    void addUser2() throws Exception
    {
        this.mockMvc
            .perform( post( "/users/registration" ).contentType( "application/json" )
                .content( "{\"username\":\"john\", \"password\": \"abcd\", \"firstName\": \"John\", "
                		+ "\"lastName\": \"John\"}" ) )
            .andExpect( status().isCreated() );
    }
}
