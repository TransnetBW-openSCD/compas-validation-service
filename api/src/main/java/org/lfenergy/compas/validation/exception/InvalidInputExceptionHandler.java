package org.lfenergy.compas.validation.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.lfenergy.compas.validation.rest.api.dto.ErrorResponse;

@Provider
public class InvalidInputExceptionHandler implements ExceptionMapper<CompasInvalidInputException> {

    @Override
    public Response toResponse(CompasInvalidInputException e) {
        var response = new ErrorResponse();
        response.setMessage(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }
}
