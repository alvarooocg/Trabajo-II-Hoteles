package es.upsa.dasi.web.application.reservas;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.Optional;

public interface FindReservaByIdUseCase
{
    Optional<Reserva> execute(String id) throws HotelesAppException;
}
