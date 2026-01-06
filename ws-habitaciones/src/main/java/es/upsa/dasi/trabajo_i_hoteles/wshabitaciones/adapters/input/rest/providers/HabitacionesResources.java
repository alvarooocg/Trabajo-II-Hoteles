package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.adapters.input.rest.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application.*;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/habitaciones")
public class HabitacionesResources
{
    @Inject
    GetHabitacionesUseCase getHabitacionesUseCase;

    @Inject
    GetHabitacionesByIdsUseCase getHabitacionesByIdsUseCase;

    @Inject
    GetHabitacionByIdUseCase getHabitacionByIdUseCase;

    @Inject
    AddHabitacionByIdUseCase addHabitacionByIdUseCase;

    @Inject
    UpdateHabitacionUseCase updateHabitacionUseCase;

    @Inject
    RemoveHabitacionByIdUseCase removeHabitacionByIdUseCase;

    @Inject
    GetHabitacionesByHotelIdUseCase getHabitacionesByHotelIdUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll(@DefaultValue("") @QueryParam("ids") List<String> ids) throws HotelesAppException
    {
        List<Habitacion> habitaciones = (ids.isEmpty())? getHabitacionesUseCase.execute() : getHabitacionesByIdsUseCase.execute(ids);
        return Response.ok().entity(habitaciones).build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById (@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Habitacion> optHabitacion = getHabitacionByIdUseCase.execute(id);
        return optHabitacion.map( habitacion -> Response.ok().entity(habitacion).build() )
                .orElse(Response.status(Response.Status.NOT_FOUND).build()
                );
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCliente (HabitacionDto habitacionDto, @Context UriInfo uriInfo) throws HotelesAppException
    {
        Habitacion habitacion = Mappers.toHabitacion(habitacionDto);
        Habitacion insertedHabitacion = addHabitacionByIdUseCase.execute(habitacion);
        return Response.ok().entity( insertedHabitacion ).build();
    }

    @Path("/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCliente (@PathParam("id") String id, HabitacionDto habitacionDto) throws HotelesAppException
    {
        Habitacion habitacion = Mappers.toHabitacion(habitacionDto);
        Habitacion updatedHabitacion = addHabitacionByIdUseCase.execute(habitacion);
        return Response.ok().entity( updatedHabitacion ).build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteClienteById(@PathParam("id") String id) throws HotelesAppException
    {
        removeHabitacionByIdUseCase.execute(id);
        return Response.noContent()
                .build();
    }

    @Path("/hoteles/{hotelId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByHotelId(@PathParam("hotelId") String hotelId) throws HotelesAppException {
        List<Habitacion> habitaciones = getHabitacionesByHotelIdUseCase.execute(hotelId);
        return Response.ok().entity(habitaciones).build();
    }


    private URI createPersonaURI(UriInfo uriInfo, Habitacion habitacion)
    {
        return uriInfo.getBaseUriBuilder()
                .path("/clientes/{id}")
                .resolveTemplate("id", habitacion.getId())
                .build();

    }





}
