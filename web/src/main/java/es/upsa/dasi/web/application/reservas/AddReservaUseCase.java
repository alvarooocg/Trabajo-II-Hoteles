package es.upsa.dasi.web.application.reservas;


import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;

public interface AddReservaUseCase
{
    Reserva execute(ReservaDto reservaDto);
}
