package extern.uppsTransportService;

import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



/**
 * Currently only resource (exposed at "transport/request" path)
 */
@Path("transport/request")
public class RecieveTransportRequest {
	
	private static long transportRequestId = 0;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     * 
     * This method is useful for checking if the web service is available or not.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String showStatus() {
        return "Online";
    }
    
    /** 
     * The method we need 
     * 
     * @param data
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN) //id mappable to text/plain?
    public long recieveTransportRequest(JsonObject data) {
    	
    	System.out.println(data);
    	
    	return ++transportRequestId; 
    }
}
