package es.upsa.dasi.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface UpdateReservaUsecase
{
    Reserva execute(Reserva reserva) throws HotelesAppException;
}
