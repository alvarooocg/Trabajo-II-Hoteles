package es.upsa.dasi.web.application.habitaciones;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;

public interface AddHabitacionUseCase
{
    Habitacion execute(HabitacionDto habitacionDto);
}
