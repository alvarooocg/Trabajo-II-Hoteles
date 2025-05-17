package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.RemoveHabitacionByIdUseCase;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveHabitacionByIdUseCaseImpl implements RemoveHabitacionByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public void execute(String id) throws HotelesAppException
    {
        repository.removeById(id);
    }
}
