package es.upsa.dasi.web.infrastructure.rest.gateway;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
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

    @PUT
    @Path("/clientes/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Cliente updateCliente (@PathParam("id") String id, ClienteDto clienteDto);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Cliente addCliente (ClienteDto clienteDto);

    @Path("/{id}")
    @DELETE
    void deleteClienteById(@PathParam("id") String id);
}
