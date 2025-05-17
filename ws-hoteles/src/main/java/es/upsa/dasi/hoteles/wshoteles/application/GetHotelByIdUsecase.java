package es.upsa.dasi.hoteles.wshoteles.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.Optional;

public interface GetHotelByIdUsecase {
    public Optional<Hotel> execute(String id) throws HotelesAppException;
}
