package es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.repository.impl;

import es.upsa.dasi.trabajo_i_hoteles.wsclientes.adapters.output.persitence.Dao;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.ClienteNotFoundException;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository
{
    @Inject
    Dao dao;

    @Override
    public List<Cliente> findAll() throws HotelesAppException
    {
        return dao.selectAll();
    }

    @Override
    public List<Cliente> findByIds(List<String> ids) throws HotelesAppException {
        return dao.selectByIds(ids);
    }

    @Override
    public Optional<Cliente> findById(String id) throws HotelesAppException
    {
        return dao.selectById(id);
    }

    @Override
    public Cliente save(Cliente cliente) throws HotelesAppException
    {
        return (cliente.getId() == null)? dao.insert(cliente) : dao.update(cliente).orElseThrow( () -> new ClienteNotFoundException() );
    }

    @Override
    public void removeByid(String id) throws HotelesAppException
    {
        dao.deleteById(id);
    }
}
