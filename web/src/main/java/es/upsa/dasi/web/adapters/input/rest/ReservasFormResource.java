package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.application.reservas.FindReservaByIdUseCase;
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
public class ReservasFormResource
{
    @Inject
    FindReservaByIdUseCase fIndReservaByIdUseCase;

    @Inject
    Models models;

    @GET
    @Path("/update/reserva/{id}")
    @Controller
    @UriRef("formUpdateReservaById")
    public Response formUpdateReservaById(@PathParam("id") String id) throws HotelesAppException {
        Optional<Reserva> reservaOptional = fIndReservaByIdUseCase.execute(id);
        if (reservaOptional.isEmpty()) return Response.ok("/jsps/reservas/reservaNotFound.jsp").build();

        models.put("action", Action.UPDATE);
        models.put("reserva", reservaOptional.get());
        return Response.ok("/jsps/reservas/reserva.jsp").build();
    }

    @GET
    @Path("/insert/reserva")
    @Controller
    @UriRef("formInsertReserva")
    public Response formInsertReserva() {
        models.put("action", Action.INSERT);
        return Response.ok("/jsps/reservas/reserva.jsp").build();
    }

    @GET
    @Path("/delete/reserva/{id}")
    @Controller
    @UriRef("formDeleteReservaById")
    public Response formDeleteReservaById(@PathParam("id") String id) throws HotelesAppException {
        Optional<Reserva> reservaOptional = fIndReservaByIdUseCase.execute(id);
        if(reservaOptional.isEmpty()) return Response.ok("/jsps/reservas/reservaNotFound.jsp").build();

        models.put("action", Action.DELETE);
        models.put("reserva", reservaOptional.get());
        return Response.ok("/jsps/reservas/reserva.jsp").build();
    }
}
