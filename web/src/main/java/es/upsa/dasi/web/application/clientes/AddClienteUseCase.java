package es.upsa.dasi.web.application.clientes;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;

public interface AddClienteUseCase
{
    Cliente execute(Cliente cliente);
}
