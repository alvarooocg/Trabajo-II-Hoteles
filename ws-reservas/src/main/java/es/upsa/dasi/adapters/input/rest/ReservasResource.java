package es.upsa.dasi.adapters.input.rest;

import es.upsa.dasi.application.*;
import es.upsa.dasi.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.ReservaNotFoundException;
import es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/reservas")
public class ReservasResource
{
    @Inject
    GetReservasUsecase getReservasUsecase;
    @Inject
    GetReservasByIdsUsecase getReservasByIdsUsecase;
    @Inject
    GetReservaByIdUsecase getReservaByIdUsecase;
    @Inject
    AddReservaUseCase addReservaUseCase;
    @Inject
    UpdateReservaUsecase updateReservaUsecase;
    @Inject
    RemoveReservaByIdUsecase removeReservaByIdUsecase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservas(@DefaultValue("") @QueryParam("ids") List<String> ids) throws HotelesAppException
    {
        List<Reserva> reservas = ( ids.isEmpty() ) ? getReservasUsecase.execute() : getReservasByIdsUsecase.execute(ids);
        return Response.ok()
                .entity(new GenericEntity<List<Reserva>>(reservas){})
                .build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReservaById(@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Reserva> optionalReserva = getReservaByIdUsecase.execute(id);

        return optionalReserva.map( reserva ->  Response.ok()
                                                                .entity(reserva)
                                                                .build())
                                .orElseThrow(() -> new ReservaNotFoundException());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReserva(ReservaDto reservaDto, @Context UriInfo uriInfo) throws HotelesAppException
    {
        Reserva reserva = Mappers.toReserva(reservaDto);
        Reserva newReserva = addReservaUseCase.execute(reserva);

        return Response.created( createReservaURI(uriInfo, newReserva) )
                .entity(newReserva)
                .build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReserva(@PathParam("id") String id, ReservaDto reservaDto) throws HotelesAppException
    {
        Reserva reserva = Mappers.toReserva(reservaDto)
                .withId(id);
        Reserva updatedReserva = updateReservaUsecase.execute(reserva);

        return Response.ok()
                .entity(updatedReserva)
                .build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteReservaById(@PathParam("id") String id) throws HotelesAppException
    {
        removeReservaByIdUsecase.execute(id);
        return Response.noContent()
                .build();
    }

    private URI createReservaURI(UriInfo uriInfo, Reserva reserva)
    {
        return uriInfo.getBaseUriBuilder()
                .path("/reservas/{id}")
                .resolveTemplate("id", reserva.getId())
                .build();
    }
}
