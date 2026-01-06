package es.upsa.ssi.app.aggregator.adapters.output.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.ssi.app.aggregator.adapters.output.rest.providers.AggregatorResponseExceptionMapper;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "rest.client.habitaciones")
@RegisterProvider(AggregatorResponseExceptionMapper.class)
@Path("/habitaciones")
public interface HabitacionesServiceClient {
    @GET
    @Path("/hoteles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<Habitacion> requestHabitacionesByHotelId(@PathParam("id")String id) throws HotelesAppException;
}
