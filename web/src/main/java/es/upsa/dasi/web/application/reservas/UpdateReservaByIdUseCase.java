package es.upsa.dasi.web.application.reservas;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;

import java.util.Optional;

public interface UpdateReservaByIdUseCase
{
    Optional<Reserva> execute(String id, ReservaDto reservaDto);
}
