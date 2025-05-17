package es.upsa.dasi.application.impl;

import es.upsa.dasi.application.RemoveReservaByIdUsecase;
import es.upsa.dasi.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveReservaByIdUsecaseImpl implements RemoveReservaByIdUsecase
{
    @Inject
    Repository repository;

    @Override
    public void execute(String id) throws HotelesAppException
    {
        repository.removeReservaById(id);
    }
}
