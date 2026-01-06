package es.upsa.ssi.app.aggregator.adapters.output.rest.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ErrorDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelNotFoundAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class HotelesAppExceptionMapper implements ExceptionMapper<HotelesAppException> {

    @Override
    public Response toResponse(HotelesAppException exception) {
        ErrorDto errorDto = new ErrorDto(exception.getMessage());

        Response.Status status = exception instanceof HotelNotFoundAppException
                ? Response.Status.NOT_FOUND
                : Response.Status.INTERNAL_SERVER_ERROR;

        return Response.status(status)
                .entity(errorDto)
                .build();
    }
}

