package es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.UpdateClienteUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateClienteUseCaseImpl implements UpdateClienteUseCase
{
    @Inject
    Repository repository;

    @Override
    public Cliente execute (Cliente cliente) throws HotelesAppException
    {
        return repository.save(cliente);
    }
}
