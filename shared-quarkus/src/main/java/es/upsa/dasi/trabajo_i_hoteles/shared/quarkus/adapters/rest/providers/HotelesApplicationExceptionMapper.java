package es.upsa.dasi.trabajo_i_hoteles.shared.quarkus.adapters.rest.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ErrorDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.NotFoundAppException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class HotelesApplicationExceptionMapper implements ExceptionMapper<HotelesAppException> {
    @Override
    public Response toResponse(HotelesAppException hotelesAppException) {
        String message = hotelesAppException.getMessage();
        Response.Status status = (hotelesAppException instanceof NotFoundAppException)? Response.Status.NOT_FOUND : Response.Status.INTERNAL_SERVER_ERROR;

        return Response.status(status)
                .entity(ErrorDto.builder()
                                .withMessage(message)
                                .build()
                )
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
