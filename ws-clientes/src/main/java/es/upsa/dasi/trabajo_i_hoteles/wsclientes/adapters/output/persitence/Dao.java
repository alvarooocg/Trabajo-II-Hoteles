package es.upsa.dasi.trabajo_i_hoteles.wsclientes.adapters.output.persitence;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    List<Cliente> selectAll() throws HotelesAppException;
    List<Cliente> selectByIds(List<String> ids) throws HotelesAppException;
    Optional<Cliente> selectById (String id) throws HotelesAppException;
    Cliente insert(Cliente cliente) throws HotelesAppException;
    Optional<Cliente> update(Cliente cliente) throws HotelesAppException;
    void deleteById (String id) throws HotelesAppException;
}
