package es.upsa.dasi.trabajo_i_hoteles.wsclientes.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface RemoveClienteByIdUseCase
{
    void execute (String idCliente) throws HotelesAppException;
}
