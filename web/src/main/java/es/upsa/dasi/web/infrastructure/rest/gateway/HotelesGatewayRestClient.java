package es.upsa.dasi.web.infrastructure.rest.gateway;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.web.infrastructure.rest.providers.HotelesResponseExceptionMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(baseUri = "http://localhost:8082")
@RegisterProvider(HotelesResponseExceptionMapper.class)
public interface HotelesGatewayRestClient
{
    @GET
    @Path("/hoteles")
    @Produces(MediaType.APPLICATION_JSON)
    List<Hotel> findHoteles();

    @GET
    @Path("/hoteles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Hotel findHotelById(@PathParam("id") String id);

    @PUT
    @Path("/hoteles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Hotel updateHotelById(@PathParam("id") String id, HotelDto hotelDto);
}
