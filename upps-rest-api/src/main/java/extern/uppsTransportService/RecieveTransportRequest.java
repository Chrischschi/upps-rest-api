package extern.uppsTransportService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import extern.uppsTransportService.model.TransportRequestData;

/**
 * Currently only resource (exposed at "transport/request" path)
 */
@Path("transport/request")
public class RecieveTransportRequest {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     * 
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    /** 
     * The method we need 
     * 
     * TODO: Test and Real Implementation
     *  
     * @param data
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN) //id mappable to text/plain?
    public long recieveTransportRequest(TransportRequestData data) {
    	long transportRequestId = 0;
    	
    	return transportRequestId; 
    }
}
