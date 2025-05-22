package es.upsa.dasi.web.application.hoteles;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;

public interface AddHotelUseCase {
    Hotel execute(HotelDto hotelDto);
}
