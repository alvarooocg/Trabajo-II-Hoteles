package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;

public interface GetHabitacionesByHotelIdUseCase {
    List<Habitacion> execute(String hotelId) throws HotelesAppException;
}
