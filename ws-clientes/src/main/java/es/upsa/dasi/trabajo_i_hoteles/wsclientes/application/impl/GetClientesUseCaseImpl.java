package es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.GetClientesUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetClientesUseCaseImpl implements GetClientesUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Cliente> execute() throws HotelesAppException
    {
        return repository.findAll();
    }
}
