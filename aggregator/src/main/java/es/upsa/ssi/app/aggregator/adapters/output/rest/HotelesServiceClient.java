package es.upsa.ssi.app.aggregator.adapters.output.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.ssi.app.aggregator.adapters.output.rest.providers.AggregatorResponseExceptionMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "rest.client.hoteles")
@RegisterProvider(AggregatorResponseExceptionMapper.class)
@Path("/hoteles")
public interface HotelesServiceClient {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    Hotel requestHotelById(@PathParam("id") String id) throws HotelesAppException;

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Hotel updateHotel(@PathParam("id")String id, Hotel hotel) throws HotelesAppException;
}
