package es.upsa.dasi.hoteles.gateway.adapters.input.rest;

import es.upsa.dasi.hoteles.gateway.adapters.output.RestApiHabitaciones;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.net.URI;

@Path("/habitaciones")
public class HabitacionesResource
{
    @Inject
    @RestClient
    RestApiHabitaciones remoteApi;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findHabitaciones(@QueryParam("ids") @DefaultValue("") String ids) throws HotelesAppException
    {
        return ids.isEmpty()? remoteApi.findAll() : remoteApi.findAll(ids);
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findHabitacionById(@PathParam("id") String id) throws HotelesAppException
    {
        return remoteApi.findById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(HabitacionDto habitacionDto, @Context UriInfo uriInfo) throws HotelesAppException
    {
        URI baseUri = uriInfo.getBaseUri();
        String scheme = baseUri.getScheme();
        String host = baseUri.getHost();
        String port = String.valueOf( baseUri.getPort() );

        return remoteApi.create(habitacionDto, scheme, host, port);
    }

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(HabitacionDto habitacionDto, @PathParam("id") String id) throws HotelesAppException
    {
        return remoteApi.update(id, habitacionDto);
    }

    @Path("/{id}")
    @DELETE
    public Response delete(@PathParam("id") String id) throws HotelesAppException
    {
        return remoteApi.deleteById(id);
    }
}
