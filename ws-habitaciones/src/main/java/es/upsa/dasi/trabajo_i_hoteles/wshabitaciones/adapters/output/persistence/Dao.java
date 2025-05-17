package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.adapters.output.persistence;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    List<Habitacion> selectAll() throws HotelesAppException;
    Optional<Habitacion> selectById(String id) throws HotelesAppException;
    List<Habitacion> selectByIds(List<String> ids) throws HotelesAppException;
    Habitacion insert(Habitacion habitacion) throws HotelesAppException;
    Optional<Habitacion> update(Habitacion habitacion) throws HotelesAppException;
    void deleteById(String id) throws HotelesAppException;
}
