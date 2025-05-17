package es.upsa.dasi.trabajo_i_hoteles.shared.quarkus.adapters.rest.client.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HabitacionNotFoundException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.RestClientAppException;

import java.util.function.Function;
import java.util.function.Supplier;

public class WsHabitacionesResponseExceptionMapper extends WsResponseExceptionMapper<HotelesAppException> {
    public WsHabitacionesResponseExceptionMapper() {
        super((message) -> new RestClientAppException(message), () -> new HabitacionNotFoundException());
    }
}
