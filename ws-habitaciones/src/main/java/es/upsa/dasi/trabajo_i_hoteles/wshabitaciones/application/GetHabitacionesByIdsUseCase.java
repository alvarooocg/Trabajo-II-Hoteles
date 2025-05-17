package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;

public interface GetHabitacionesByIdsUseCase
{
    List<Habitacion> execute(List<String> ids) throws HotelesAppException;
}
