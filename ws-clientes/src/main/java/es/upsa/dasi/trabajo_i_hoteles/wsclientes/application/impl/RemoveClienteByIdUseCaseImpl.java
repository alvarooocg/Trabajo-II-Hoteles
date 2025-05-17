package es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.RemoveClienteByIdUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveClienteByIdUseCaseImpl implements RemoveClienteByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public void execute(String idCliente) throws HotelesAppException
    {
        repository.removeByid(idCliente);
    }
}
