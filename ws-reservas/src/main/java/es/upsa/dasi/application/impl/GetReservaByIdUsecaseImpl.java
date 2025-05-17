package es.upsa.dasi.application.impl;

import es.upsa.dasi.application.GetReservaByIdUsecase;
import es.upsa.dasi.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetReservaByIdUsecaseImpl implements GetReservaByIdUsecase
{
    @Inject
    Repository repository;

    @Override
    public Optional<Reserva> execute(String id) throws HotelesAppException {
        return repository.getReservaById(id);
    }
}
