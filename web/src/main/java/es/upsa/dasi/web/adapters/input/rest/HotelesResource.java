package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.hoteles.FIndHotelByIdUseCase;
import es.upsa.dasi.web.application.hoteles.FindAllHotelesUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.krazo.binding.BindingResultImpl;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Path("/{locale}/hoteles")
public class HotelesResource
{
    @Inject
    FindAllHotelesUseCase findAllHotelesUseCase;

    @Inject
    FIndHotelByIdUseCase fIndHotelByIdUseCase;

    @Inject
    Models models;

    @Inject
    MvcContext mvcContext;

    @Inject
    private BindingResultImpl bindingResultImpl;

    @GET
    @Controller
    @UriRef("findAllHoteles")
    @View("/jsps/hoteles/hoteles.jsp")
    public void findAllHoteles() throws HotelesAppException {
        List<Hotel> hoteles = findAllHotelesUseCase.execute();
        models.put("hoteles", hoteles);
    }

    @GET
    @Path("/{id}")
    @Controller
    @UriRef("findHotelById")
    @View("/jsps/hoteles/hotel.jsp")
    public Response findHotelById(@PathParam("id") String id) throws HotelesAppException {
        Optional<Hotel> optHotel = fIndHotelByIdUseCase.execute(id);
        if(optHotel.isEmpty()) return Response.ok("/jsps/hoteles/hotelNotFound.jsp").build();

        models.put("action", Action.VIEW);
        models.put("hotel", optHotel.get());
        return Response.ok("/jsps/hoteles/hotel.jsp").build();
    }
}
