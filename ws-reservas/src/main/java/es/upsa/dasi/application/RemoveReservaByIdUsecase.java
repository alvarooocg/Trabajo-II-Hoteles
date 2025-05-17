package es.upsa.dasi.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface RemoveReservaByIdUsecase
{
    void execute(String id) throws HotelesAppException;

}
