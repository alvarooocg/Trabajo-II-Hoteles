package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.domain.repository;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Habitacion> findAll() throws HotelesAppException;
    List<Habitacion> findByIds(List<String> ids) throws HotelesAppException;
    Optional<Habitacion> findById(String id) throws HotelesAppException;
    Habitacion save(Habitacion habitacion) throws HotelesAppException;
    void removeById(String id) throws HotelesAppException;
}
