package es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.GetClienteByIdUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;

@ApplicationScoped
public class GetClienteByIdUseCaseImpl implements GetClienteByIdUseCase
{
    @Inject
    Repository repository;

    @Override
    public Optional<Cliente> execute(String id) throws HotelesAppException
    {
        return repository.findById(id);
    }
}
