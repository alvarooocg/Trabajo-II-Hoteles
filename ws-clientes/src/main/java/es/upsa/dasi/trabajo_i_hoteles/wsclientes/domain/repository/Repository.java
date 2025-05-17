package es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.repository;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Cliente> findAll() throws HotelesAppException;
    List<Cliente> findByIds(List<String> ids) throws HotelesAppException;
    Optional<Cliente> findById(String id) throws HotelesAppException;
    Cliente save(Cliente cliente) throws HotelesAppException;
    void removeByid(String id) throws HotelesAppException;
}
