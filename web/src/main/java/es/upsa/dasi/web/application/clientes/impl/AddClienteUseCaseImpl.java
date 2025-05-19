package es.upsa.dasi.web.application.clientes.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.web.application.clientes.AddClienteUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.ClientesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AddClienteUseCaseImpl implements AddClienteUseCase
{
    @Inject
    @RestClient
    ClientesGatewayRestClient restClient;

    @Override
    public Cliente execute(Cliente cliente)
    {
        return restClient.addCliente(cliente);
    }
}
