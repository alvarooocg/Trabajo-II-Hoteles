package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.domain.repository.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.adapters.output.persistence.Dao;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HabitacionNotFoundException;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.domain.repository.Repository;
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
    public List<Habitacion> findAll() throws HotelesAppException
    {
        return dao.selectAll();
    }

    @Override
    public List<Habitacion> findByIds(List<String> ids) throws HotelesAppException
    {
        return dao.selectByIds(ids);
    }

    @Override
    public Optional<Habitacion> findById(String id) throws HotelesAppException
    {
        return dao.selectById(id);
    }

    @Override
    public Habitacion save(Habitacion habitacion) throws HotelesAppException
    {
        return (habitacion.getId() == null)? dao.insert(habitacion) : dao.update(habitacion).orElseThrow( () -> new HabitacionNotFoundException() );
    }

    @Override
    public void removeById(String id) throws HotelesAppException
    {
        dao.deleteById(id);
    }

    @Override
    public List<Habitacion> findByHotelId(String hotelId) throws HotelesAppException {
        return dao.findByHotelId(hotelId);
    }
}
