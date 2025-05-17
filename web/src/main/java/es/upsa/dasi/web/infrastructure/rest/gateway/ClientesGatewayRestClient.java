package es.upsa.dasi.web.infrastructure.rest.gateway;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8082")
public interface ClientesGatewayRestClient
{
    @GET
    @Path("/clientes")
    @Produces(MediaType.APPLICATION_JSON)
    List<Cliente> findAllClientes ();

    @GET
    @Path("/clientes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Cliente findClienteById (@PathParam("id") String id);



}
