package es.upsa.dasi.domain.repository.impl;

import es.upsa.dasi.adapters.output.persistance.Dao;
import es.upsa.dasi.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.ReservaNotFoundException;
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
    public List<Reserva> getReservas() throws HotelesAppException
    {
        return dao.findHoteles();
    }

    @Override
    public List<Reserva> getReservasByIds(List<String> ids) throws HotelesAppException {
        return dao.findReservasByIds(ids);
    }

    @Override
    public Optional<Reserva> getReservaById(String id) throws HotelesAppException {
        return dao.findReservaById(id);
    }

    @Override
    public Reserva saveReserva(Reserva reserva) throws HotelesAppException
    {
        return (reserva.getId() == null) ? dao.insertReserva(reserva) : dao.updateReserva(reserva)
                .orElseThrow(() -> new ReservaNotFoundException());

    }

    @Override
    public void removeReservaById(String id) throws HotelesAppException
    {
        dao.deleteReservaById(id);
    }
}
