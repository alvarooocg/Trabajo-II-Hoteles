package es.upsa.dasi.web.infrastructure.rest.gateway;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.web.infrastructure.rest.providers.HabitacionesResponseExceptionMapper;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8083")
@RegisterProvider(HabitacionesResponseExceptionMapper.class)
public interface HabitacionesGatewayRestClient
{
        @GET
        @Path("/habitaciones")
        @Produces(MediaType.APPLICATION_JSON)
        List<Habitacion> findHabitaciones();

        @GET
        @Path("/habitaciones/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        Habitacion findHabitacionById(@PathParam("id") String id);

        @PUT
        @Path("/habitaciones/{id}")
        @Produces(MediaType.APPLICATION_JSON)
        @Consumes(MediaType.APPLICATION_JSON)
        Habitacion updateHabitacionById(@PathParam("id") String id, HabitacionDto habitacionDto);

        @POST
        @Path("/habitaciones")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        Habitacion addHabitacion (HabitacionDto habitacionDto);

        @Path("/habitaciones/{id}")
        @DELETE
        void deleteHabitacionById(@PathParam("id") String id);
}


