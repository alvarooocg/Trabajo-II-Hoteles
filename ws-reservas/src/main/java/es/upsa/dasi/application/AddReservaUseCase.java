package es.upsa.dasi.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface AddReservaUseCase
{
    Reserva execute(Reserva reserva) throws HotelesAppException;
}
