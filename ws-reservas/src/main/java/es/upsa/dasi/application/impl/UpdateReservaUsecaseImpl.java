package es.upsa.dasi.application.impl;

import es.upsa.dasi.adapters.output.persistance.Dao;
import es.upsa.dasi.application.UpdateReservaUsecase;
import es.upsa.dasi.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateReservaUsecaseImpl implements UpdateReservaUsecase
{
    @Inject
    Repository repository;

    @Override
    public Reserva execute(Reserva reserva) throws HotelesAppException
    {
        return repository.saveReserva(reserva);
    }
}
