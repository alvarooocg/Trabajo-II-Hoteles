package es.upsa.dasi.application.impl;

import es.upsa.dasi.application.GetReservasUsecase;
import es.upsa.dasi.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetReservasUsecaseImpl implements GetReservasUsecase
{
    @Inject
    Repository repository;

    @Override
    public List<Reserva> execute() throws HotelesAppException
    {
        return repository.getReservas();
    }
}
