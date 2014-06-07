package extern.uppsTransportService;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("transport/request").request().get(String.class);
        assertEquals("Got it!", responseMsg);
    }
}
