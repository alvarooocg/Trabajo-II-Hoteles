package es.upsa.ssi.app.aggregator.application.repository;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Repository {
    Optional<Hotel> getHotelById(String id) throws HotelesAppException;
    List<Habitacion> getHabitacionesByHotel(String id) throws HotelesAppException;
}
