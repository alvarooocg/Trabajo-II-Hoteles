package es.upsa.dasi.web.application.clientes.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.clientes.FindClienteByIdUseCase;
import es.upsa.dasi.web.domain.exceptions.ClientesNotFoundRuntimeException;
import es.upsa.dasi.web.infrastructure.rest.gateway.ClientesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class FindClienteByIdUseCaseImpl implements FindClienteByIdUseCase
{
    @Inject
    @RestClient
    ClientesGatewayRestClient restClient;

    @Override
    public Optional<Cliente> execute(String id) throws HotelesAppException
    {
        try
        {
            Cliente cliente = restClient.findClienteById(id);
            return Optional.of(cliente);
        }catch (ClientesNotFoundRuntimeException exception)
        {
            return Optional.empty();
        }
    }

}
