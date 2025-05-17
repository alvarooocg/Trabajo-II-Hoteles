package es.upsa.dasi.trabajo_i_hoteles.wsclientes.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface UpdateClienteUseCase
{
    Cliente execute (Cliente cliente) throws HotelesAppException;
}
