package es.upsa.dasi.web.application.clientes;

import java.util.Optional;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface FindClienteByIdUseCase
{
    Optional<Cliente> execute(String id) throws HotelesAppException;
}
