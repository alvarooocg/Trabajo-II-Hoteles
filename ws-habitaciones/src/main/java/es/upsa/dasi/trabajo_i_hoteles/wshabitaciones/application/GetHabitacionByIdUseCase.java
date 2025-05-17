package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.Optional;

public interface GetHabitacionByIdUseCase
{
    Optional<Habitacion> execute (String id) throws HotelesAppException;
}
