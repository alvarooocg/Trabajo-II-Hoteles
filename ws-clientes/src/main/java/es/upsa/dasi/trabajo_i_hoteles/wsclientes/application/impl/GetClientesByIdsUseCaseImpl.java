package es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.impl;

import es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.GetClientesByIdsUseCase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetClientesByIdsUseCaseImpl implements GetClientesByIdsUseCase
{
    @Inject
    Repository repository;

    @Override
    public List<Cliente> execute(List<String> ids) throws HotelesAppException
    {
        return repository.findByIds(ids);
    }
}
