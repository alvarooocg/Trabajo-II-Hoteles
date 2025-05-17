package es.upsa.dasi.application.impl;

import es.upsa.dasi.application.GetReservasByIdsUsecase;
import es.upsa.dasi.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetReservasByIdsUsecaseImpl implements GetReservasByIdsUsecase
{
    @Inject
    Repository repository;

    @Override
    public List<Reserva> execute(List<String> ids) throws HotelesAppException {
        return repository.getReservasByIds(ids);
    }
}
