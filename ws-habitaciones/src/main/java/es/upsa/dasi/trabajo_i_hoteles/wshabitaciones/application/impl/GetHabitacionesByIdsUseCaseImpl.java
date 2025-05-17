package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.GetHabitacionesByIdsUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetHabitacionesByIdsUseCaseImpl implements GetHabitacionesByIdsUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Habitacion> execute(List<String> ids) throws HotelesAppException
    {
        return repository.findByIds(ids);
    }
}
