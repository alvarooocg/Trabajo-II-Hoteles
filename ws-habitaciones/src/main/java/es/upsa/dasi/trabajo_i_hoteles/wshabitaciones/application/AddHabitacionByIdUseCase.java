package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface AddHabitacionByIdUseCase
{
    Habitacion execute(Habitacion habitacion) throws HotelesAppException;
}
