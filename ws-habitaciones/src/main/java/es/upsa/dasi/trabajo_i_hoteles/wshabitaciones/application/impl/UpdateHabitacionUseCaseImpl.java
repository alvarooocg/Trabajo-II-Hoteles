package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.UpdateHabitacionUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateHabitacionUseCaseImpl implements UpdateHabitacionUseCase
{
    @Inject
    Repository repository;

    @Override
    public Habitacion execute(Habitacion habitacion) throws HotelesAppException
    {
        return repository.save(habitacion);
    }
}
