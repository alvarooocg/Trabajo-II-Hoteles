package es.upsa.dasi.trabajo_i_hoteles.shared.quarkus.adapters.rest.client.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.ReservaNotFoundException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.RestClientAppException;

public class WsReservasResponseExceptionMapper extends WsResponseExceptionMapper<HotelesAppException> {
    public WsReservasResponseExceptionMapper() {
        super((message) -> new RestClientAppException(message), () -> new ReservaNotFoundException());
    }
}
