package es.upsa.dasi.web.infrastructure.rest.gateway;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;

import es.upsa.dasi.web.infrastructure.rest.providers.ReservasResponseExceptionMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8084")
@RegisterProvider(ReservasResponseExceptionMapper.class)
public interface ReservasGatewayRestClient
{
    @GET
    @Path("/reservas")
    @Produces(MediaType.APPLICATION_JSON)
    List<Reserva> findReservas();

    @GET
    @Path("/reservas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Reserva findReservaById(@PathParam("id") String id);

    @PUT
    @Path("/reservas/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Reserva updateReservaById(@PathParam("id") String id, ReservaDto reservaDto);

    @POST
    @Path("/reservas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Reserva addReserva (ReservaDto habitacionDto);

    @Path("/reservas/{id}")
    @DELETE
    void deleteReservaById(@PathParam("id") String id);
}
