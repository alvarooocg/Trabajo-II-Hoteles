package es.upsa.dasi.web.application.hoteles;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.Optional;

public interface FIndHotelByIdUseCase {
    Optional<Hotel> execute(String id) throws HotelesAppException;
}
