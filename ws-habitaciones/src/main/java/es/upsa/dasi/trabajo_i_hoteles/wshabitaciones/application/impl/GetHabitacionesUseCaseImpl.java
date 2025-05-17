package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.GetHabitacionesUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetHabitacionesUseCaseImpl implements GetHabitacionesUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Habitacion> execute() throws HotelesAppException
    {
        return repository.findAll();
    }
}
