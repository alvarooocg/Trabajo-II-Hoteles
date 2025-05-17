package es.upsa.dasi.trabajo_i_hoteles.shared.quarkus.adapters.rest.client.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.ClienteNotFoundException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.RestClientAppException;

public class WsClientesResponseExceptionMapper extends WsResponseExceptionMapper<HotelesAppException> {
    public WsClientesResponseExceptionMapper() {
        super((message) -> new RestClientAppException(message), () -> new ClienteNotFoundException());
    }
}
