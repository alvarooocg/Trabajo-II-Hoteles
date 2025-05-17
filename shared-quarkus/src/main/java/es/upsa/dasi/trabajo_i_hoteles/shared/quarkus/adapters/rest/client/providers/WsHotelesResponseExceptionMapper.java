package es.upsa.dasi.trabajo_i_hoteles.shared.quarkus.adapters.rest.client.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelNotFoundAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.RestClientAppException;

public class WsHotelesResponseExceptionMapper extends WsResponseExceptionMapper<HotelesAppException> {
    public WsHotelesResponseExceptionMapper() {
        super((message) -> new RestClientAppException(message), () -> new HotelNotFoundAppException());
    }
}
