package es.upsa.dasi.web.application.clientes.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.web.application.clientes.UpdateClienteByIdUseCase;
import es.upsa.dasi.web.domain.exceptions.ClientesNotFoundRuntimeException;
import es.upsa.dasi.web.infrastructure.rest.gateway.ClientesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class UpdateClienteByIdUseCaseImpl implements UpdateClienteByIdUseCase
{
    @Inject
    @RestClient
    ClientesGatewayRestClient restClient;

    @Override
    public Optional<Cliente> execute(String id, ClienteDto clienteDto)
    {
        try
        {
            Cliente cliente1 = restClient.updateCliente(id, clienteDto);
            return Optional.of(cliente1);
        }catch (ClientesNotFoundRuntimeException e)
        {
            return Optional.empty();
        }
    }
}
