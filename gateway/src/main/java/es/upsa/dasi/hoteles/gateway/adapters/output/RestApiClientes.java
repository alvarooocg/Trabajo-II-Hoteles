package es.upsa.dasi.hoteles.gateway.adapters.output;

import es.upsa.dasi.hoteles.gateway.adapters.input.rest.utils.HttpXHeaders;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8082")
@Path("/clientes")
public interface RestApiClientes
{
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response findAll();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Response findAll(@QueryParam("ids") String ids);

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Response findById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response create(ClienteDto clienteDto,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_PROTO) String schema,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_HOST) String host,
                    @HeaderParam(HttpXHeaders.X_FORWARDED_PORT) String port
    );

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response update(@PathParam("id") String id, ClienteDto clienteDto);

    @DELETE
    @Path("/{id}")
    Response deleteById(@PathParam("id") String id);
}
