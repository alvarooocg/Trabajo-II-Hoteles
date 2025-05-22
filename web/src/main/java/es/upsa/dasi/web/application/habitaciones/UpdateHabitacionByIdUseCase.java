package es.upsa.dasi.web.application.habitaciones;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import java.util.Optional;

public interface UpdateHabitacionByIdUseCase
{
    Optional<Habitacion> execute(String id, HabitacionDto habitacionDto);
}
