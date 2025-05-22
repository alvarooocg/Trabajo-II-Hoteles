package es.upsa.dasi.web.infrastructure.rest.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ErrorDto;
import es.upsa.dasi.web.domain.exceptions.ClientesNotFoundRuntimeException;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

public class ClientesResponseExceptionMapper implements ResponseExceptionMapper<WebApplicationException>
{
    @Override
    public WebApplicationException toThrowable(Response response)
    {
        Response.Status status = response.getStatusInfo().toEnum();
        return switch (status)
        {
            case NOT_FOUND -> new ClientesNotFoundRuntimeException();

            default        -> {
                ErrorDto errorDto = response.readEntity(ErrorDto.class);
                yield new InternalServerErrorException(errorDto.getMessage());
            }
        };
    }
}
