package es.upsa.dasi.trabajo_i_hoteles.wsclientes.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.Optional;

public interface GetClienteByIdUseCase
{
    Optional<Cliente> execute(String id) throws HotelesAppException;
}
