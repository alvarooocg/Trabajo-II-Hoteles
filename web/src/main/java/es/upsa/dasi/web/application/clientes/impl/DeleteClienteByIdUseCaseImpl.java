package es.upsa.dasi.web.application.clientes.impl;

import es.upsa.dasi.web.application.clientes.DeleteClienteByIdUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.ClientesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class DeleteClienteByIdUseCaseImpl implements DeleteClienteByIdUseCase
{
    @Inject
    @RestClient
    ClientesGatewayRestClient clientesGatewayRestClient;

    @Override
    public void execute(String id)
    {
        clientesGatewayRestClient.deleteClienteById(id);
    }
}
