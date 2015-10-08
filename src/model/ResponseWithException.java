package model;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
/**
 * Deze class zorgt voor een response op een bad request. Deze exception kan gethrowed worden
 * wanneer we te maken hebben met GET, POST, UPDATE of DELETE
 */
public class ResponseWithException extends WebApplicationException {
    public ResponseWithException(String message,Status status) {
        super(Response.status(status)
            .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}