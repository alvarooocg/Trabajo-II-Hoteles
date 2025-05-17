package es.upsa.dasi.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;

public interface GetReservasByIdsUsecase
{
    List<Reserva> execute(List<String> ids) throws HotelesAppException;
}
