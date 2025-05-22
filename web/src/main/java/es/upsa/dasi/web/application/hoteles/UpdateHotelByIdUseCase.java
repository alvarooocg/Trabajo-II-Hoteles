package es.upsa.dasi.web.application.hoteles;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;

import java.util.Optional;

public interface UpdateHotelByIdUseCase {
    Optional<Hotel> execute(String id, HotelDto hotelDto);
}
