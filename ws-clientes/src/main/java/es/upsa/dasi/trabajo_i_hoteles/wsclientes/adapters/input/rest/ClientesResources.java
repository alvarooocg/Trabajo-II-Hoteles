package es.upsa.dasi.trabajo_i_hoteles.wsclientes.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.application.*;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/clientes")
public class ClientesResources
{
    @Inject
    GetClientesUseCase getClientesUseCase;

    @Inject
    GetClientesByIdsUseCase getClientesByIdsUseCase;

    @Inject
    GetClienteByIdUseCase getClienteByIdUseCase;

    @Inject
    AddClienteByIdUseCase addClienteByIdUseCase;

    @Inject
    UpdateClienteUseCase updateClienteUseCase;

    @Inject
    RemoveClienteByIdUseCase removeClienteByIdUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClientes(@DefaultValue("") @QueryParam("ids") List<String> ids) throws HotelesAppException
    {
        List<Cliente> clientes = (ids.isEmpty())? getClientesUseCase.execute() : getClientesByIdsUseCase.execute(ids);
        return Response.ok()
                .entity(new GenericEntity<List<Cliente>>(clientes) {})
                .build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClienteById(@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Cliente> optionalCliente = getClienteByIdUseCase.execute(id);

        return optionalCliente.map( cliente -> Response.ok().entity( cliente ).build()
                                  )
                                   .orElse(Response.status(Response.Status.NOT_FOUND).build()
                                   );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCliente (ClienteDto clienteDto, @Context UriInfo uriInfo) throws HotelesAppException
    {
        Cliente cliente = Mappers.toCliente(clienteDto);
        Cliente insertedCliente = addClienteByIdUseCase.execute(cliente);
        return Response.ok().entity( insertedCliente ).build();
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCliente (@PathParam("id") String id, ClienteDto clienteDto) throws HotelesAppException
    {
        Cliente cliente = Mappers.toCliente(clienteDto).withId(id);
        Cliente updatedCliente = updateClienteUseCase.execute( cliente );
        return Response.ok().entity( updatedCliente ).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteClienteById(@PathParam("id") String id) throws HotelesAppException
    {
        removeClienteByIdUseCase.execute(id);
        return Response.noContent()
                .build();
    }

    private URI createPersonaURI(UriInfo uriInfo, Cliente cliente)
    {
        return uriInfo.getBaseUriBuilder()
                .path("/clientes/{id}")
                .resolveTemplate("id", cliente.getId())
                .build();

    }


}
