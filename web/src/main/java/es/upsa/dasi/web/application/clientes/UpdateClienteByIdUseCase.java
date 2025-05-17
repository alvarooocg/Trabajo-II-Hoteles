package es.upsa.dasi.web.application.clientes;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;

import java.util.Optional;

public interface UpdateClienteByIdUseCase
{
    Optional<Cliente> execute(String id, ClienteDto clienteDto);
}
