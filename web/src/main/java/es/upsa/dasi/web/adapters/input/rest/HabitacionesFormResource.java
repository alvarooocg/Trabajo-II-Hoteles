package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.application.habitaciones.FindHabitacionByIdUseCase;
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
public class HabitacionesFormResource
{
    @Inject
    FindHabitacionByIdUseCase findHabitacionByIdUseCase;

    @Inject
    Models models;

    @GET
    @Path("/update/habitacion/{id}")
    @Controller
    @UriRef("formUpdateHabitacionById")
    public Response formUpdateHabitacionById(@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Habitacion> habitacionOpt = findHabitacionByIdUseCase.execute(id);
        if (habitacionOpt.isEmpty()) return Response.ok("/jsps/habitaciones/habitacionNotFound.jsp").build();

        models.put("action", Action.UPDATE);
        models.put("habitacion", habitacionOpt.get());
        return Response.ok("/jsps/habitaciones/habitacion.jsp").build();
    }

    @GET
    @Path("/insert/habitacion")
    @Controller
    @UriRef("formInsertHabitacion")
    public Response formInserthabitacion()
    {
        models.put("action", Action.INSERT);
        return Response.ok("/jsps/habitaciones/habitacion.jsp").build();
    }

    @GET
    @Path("/delete/habitacion/{id}")
    @Controller
    @UriRef("formDeleteHabitacionById")
    public Response formDeleteHabitacionById(@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Habitacion> habitacionOptional = findHabitacionByIdUseCase.execute(id);
        if (habitacionOptional.isEmpty()) return Response.ok("/jsps/habitaciones/habitacionNotFound.jsp").build();

        models.put("action", Action.DELETE);
        models.put("habitacion", habitacionOptional.get());
        return Response.ok("/jsps/habitaciones/habitacion.jsp").build();
    }

}
