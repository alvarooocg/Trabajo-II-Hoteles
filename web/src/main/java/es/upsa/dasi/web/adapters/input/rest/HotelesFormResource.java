package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.application.hoteles.FIndHotelByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.UriRef;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
@Path("/{locale}/forms")
public class HotelesFormResource {
    @Inject
    FIndHotelByIdUseCase fIndHotelByIdUseCase;

    @Inject
    Models models;

    @GET
    @Path("/update/hotel/{id}")
    @Controller
    @UriRef("formUpdateHotelById")
    public Response formUpdateHotelById(@PathParam("id") String id) throws HotelesAppException {
        Optional<Hotel> hotelOptional = fIndHotelByIdUseCase.execute(id);
        if (hotelOptional.isEmpty()) return Response.ok("/jsps/hoteles/hotelNotFound.jsp").build();

        models.put("action", Action.UPDATE);
        models.put("hotel", hotelOptional.get());
        return Response.ok("/jsps/hoteles/hotel.jsp").build();
    }

    @GET
    @Path("/insert/hotel")
    @Controller
    @UriRef("formInsertHotel")
    public Response formInsertHotel() {
        models.put("action", Action.INSERT);
        return Response.ok("/jsps/hoteles/hotel.jsp").build();
    }

    @GET
    @Path("/delete/hotel/{id}")
    @Controller
    @UriRef("formDeleteHotelById")
    public Response formDeleteHotelById(@PathParam("id") String id) throws HotelesAppException {
        Optional<Hotel> hotelOptional = fIndHotelByIdUseCase.execute(id);
        if(hotelOptional.isEmpty()) return Response.ok("/jsps/hoteles/hotelNotFound.jsp").build();

        models.put("action", Action.DELETE);
        models.put("hotel", hotelOptional.get());
        return Response.ok("/jsps/hoteles/hotel.jsp").build();
    }
}
