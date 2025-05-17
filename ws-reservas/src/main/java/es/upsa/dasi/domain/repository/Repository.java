package es.upsa.dasi.domain.repository;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Reserva> getReservas() throws HotelesAppException;
    List<Reserva> getReservasByIds(List<String> ids) throws HotelesAppException;
    Optional<Reserva> getReservaById(String id) throws HotelesAppException;
    Reserva saveReserva(Reserva reserva) throws HotelesAppException;
    void removeReservaById(String id) throws HotelesAppException;

}
