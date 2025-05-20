package es.upsa.dasi.web.adapters.input.rest.mappers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.web.adapters.input.rest.dtos.ClienteForm;

public class Mappers
{
    public static ClienteDto toClienteDto (ClienteForm clienteForm)
    {
        return ClienteDto.builder()
                         .withNombre(clienteForm.getNombre())
                         .withEmail(clienteForm.getEmail())
                         .withTelefono(clienteForm.getTelefono())
                         .build();
    }
    public static Cliente toCliente (ClienteForm clienteForm)
    {
        return Cliente.builder()
                      .withNombre(clienteForm.getNombre())
                      .withEmail(clienteForm.getEmail())
                      .withTelefono(clienteForm.getTelefono())
                      .build();
    }
}
