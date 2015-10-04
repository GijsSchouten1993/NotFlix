package model;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ResponseWithException extends WebApplicationException {
    public ResponseWithException(String message,Status status) {
        super(Response.status(status)
            .entity(message).type(MediaType.TEXT_PLAIN).build());
    }
}