package es.upsa.dasi.web.application.habitaciones;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.Optional;

public interface FindHabitacionByIdUseCase
{
    Optional<Habitacion> execute(String id) throws HotelesAppException;
}
