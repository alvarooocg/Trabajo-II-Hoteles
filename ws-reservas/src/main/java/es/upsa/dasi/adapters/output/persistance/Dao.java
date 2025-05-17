package es.upsa.dasi.adapters.output.persistance;


import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Dao
{
    List<Reserva> findHoteles() throws HotelesAppException;
    List<Reserva> findReservasByIds(List<String> ids) throws HotelesAppException;
    Optional<Reserva> findReservaById(String id) throws HotelesAppException;
    Reserva insertReserva(Reserva reserva) throws HotelesAppException;
    Optional<Reserva> updateReserva(Reserva reserva) throws HotelesAppException;
    void deleteReservaById(String id) throws HotelesAppException;
}
