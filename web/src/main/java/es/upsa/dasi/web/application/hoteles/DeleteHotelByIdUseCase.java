package es.upsa.dasi.web.application.hoteles;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;

public interface DeleteHotelByIdUseCase {
    void execute(String id);
}
