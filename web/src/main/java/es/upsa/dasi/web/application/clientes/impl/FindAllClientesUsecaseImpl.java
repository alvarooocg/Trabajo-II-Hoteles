package es.upsa.dasi.web.application.clientes.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.clientes.FindAllClientesUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.ClientesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class FindAllClientesUsecaseImpl implements FindAllClientesUseCase
{
    @Inject
    @RestClient
    ClientesGatewayRestClient restClient;

    @Override
    public List<Cliente> execute() throws HotelesAppException
    {
        return restClient.findAllClientes();
    }

}
