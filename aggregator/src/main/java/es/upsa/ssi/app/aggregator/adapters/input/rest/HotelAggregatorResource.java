package es.upsa.ssi.app.aggregator.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.ssi.app.aggregator.application.usecases.FindHotelWithHabitacionesByIdUsecase;
import es.upsa.ssi.app.aggregator.model.aggregators.HotelWithHabitaciones;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/hoteles")
@RequestScoped
public class HotelAggregatorResource {
    @Inject
    FindHotelWithHabitacionesByIdUsecase findHotelWithHabitacionesByIdUsecase;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response requestHotelWithHabitaciones(@PathParam("id")String id) throws HotelesAppException {
        HotelWithHabitaciones result = findHotelWithHabitacionesByIdUsecase.execute(id);

        return Response.ok()
                .entity(result)
                .build();
    }

}
