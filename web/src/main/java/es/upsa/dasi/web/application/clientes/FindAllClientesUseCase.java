package es.upsa.dasi.web.application.clientes;


import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;

public interface FindAllClientesUseCase
{
    List<Cliente> execute() throws HotelesAppException;
}
