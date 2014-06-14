package extern.uppsTransportService;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecieveTransportRequestTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {
        // start the server
        server = UPPS_REST_API_Main.startServer();
        // create the client
        Client c = ClientBuilder.newClient();
        
        Logger debugLogger = Logger.getLogger("debugLogger");
        
        debugLogger.setLevel(Level.ALL);
        
        c.register(new LoggingFilter(debugLogger,true));

        // uncomment the following line if you want to enable
        // support for JSON in the client (you also have to uncomment
        // dependency on jersey-media-json module in pom.xml and Main.startServer())
        // --
        // c.configuration().enable(new org.glassfish.jersey.media.json.JsonJaxbFeature());

        target = c.target(UPPS_REST_API_Main.BASE_URI);
    }

    @After
    public void tearDown() throws Exception {
        server.shutdownNow();
    }

    /**
     * Test to see that the message "Online" is sent in the response.
     */
    @Test
    public void testCheckStatus() {
        String responseMsg = target.path("transport/request").request().get(String.class);
        assertEquals("Online", responseMsg);
    }
    
    
    private long transportRequestId1;
    
    @Test
    public void testRecieveTransportRequest() {
    	JsonObject deliveryItem = Json.createObjectBuilder()
    			.add("nr", 1L)
    			.add("name", "lawnmower")
    			.build();
    	
    	JsonObject sendData = Json.createObjectBuilder()
    			.add("name", "christian schirin")
    			.add("address", "Main Street 123")
    			.add("deliveryItem", deliveryItem)
    			.build();
    			
    
    	
    	Entity<JsonObject> entity = Entity.json(sendData);
    	
    	Response response = target.path("transport/request").request().post(entity);
    	
    	long transportRequestId = response.readEntity(Long.class);
    	
    	assertTrue(transportRequestId > 0);
    	
    	transportRequestId1 = transportRequestId;
    	
    }
    
    @Test 
    public void testRecieveTransportRequestDifferentIDs() {
    	testRecieveTransportRequest(); 
    	
    	JsonObject sendData2 = Json.createObjectBuilder().build();
    	//we don't care if the json is semantically correct
    	
    	Entity<JsonObject> entity2 = Entity.json(sendData2);
    	
    	Response response2 = target.path("transport/request").request().post(entity2);
    	
    	long transportRequestId2 = response2.readEntity(Long.class);
    	
    	assertTrue(transportRequestId2 > transportRequestId1);
    	
    }
    
    
}
