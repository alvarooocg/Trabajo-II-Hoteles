package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.GetHabitacionByIdUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetHabitacionByIdUseCaseImpl implements GetHabitacionByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public Optional<Habitacion> execute(String id) throws HotelesAppException
    {
        return repository.findById(id);
    }
}
