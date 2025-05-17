package es.upsa.dasi.hoteles.wshoteles.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface UpdateHotelUsecase {
    Hotel execute(Hotel hotel) throws HotelesAppException;
}
