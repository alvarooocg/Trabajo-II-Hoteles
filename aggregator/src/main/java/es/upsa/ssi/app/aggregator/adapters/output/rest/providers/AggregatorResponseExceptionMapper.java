package es.upsa.ssi.app.aggregator.adapters.output.rest.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ErrorDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelNotFoundAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

@Provider
public class AggregatorResponseExceptionMapper implements ResponseExceptionMapper<HotelesAppException> {
    @Override
    public HotelesAppException toThrowable(Response response) {
        return  switch ( response.getStatusInfo().toEnum() )
        {
            case NOT_FOUND ->  new HotelNotFoundAppException(  response.readEntity(ErrorDto.class).getMessage()   );


            default -> {
                ErrorDto errorDto = response.readEntity(ErrorDto.class);
                String message = errorDto.getMessage();
                yield new HotelesAppException(message);
            }
        };
    }

    @Override
    public boolean handles(int status, jakarta.ws.rs.core.MultivaluedMap<String, Object> headers) {
        return status >= 400;
    }
}
