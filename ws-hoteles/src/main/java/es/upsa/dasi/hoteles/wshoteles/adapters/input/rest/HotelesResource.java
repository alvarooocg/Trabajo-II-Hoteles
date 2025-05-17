package es.upsa.dasi.hoteles.wshoteles.adapters.input.rest;

import es.upsa.dasi.hoteles.wshoteles.application.*;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelNotFoundAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/hoteles")
public class HotelesResource {
    @Inject
    GetHotelesUsecase getHotelesUsecase;

    @Inject
    GetHotelByIdUsecase getHotelByIdUsecase;

    @Inject
    GetHotelesByIdsUsecase getHotelesByIdsUsecase;

    @Inject
    AddHotelUsecase addHotelUsecase;

    @Inject
    UpdateHotelUsecase updateHotelUsecase;

    @Inject
    RemoveHotelByIdUsecase removeHotelByIdUsecase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHoteles(@DefaultValue("") @QueryParam("ids") List<String> ids) throws HotelesAppException {
        List<Hotel> hoteles = ( ids.isEmpty() ) ? getHotelesUsecase.execute() : getHotelesByIdsUsecase.execute(ids);
        return Response.ok()
                .entity( new GenericEntity<List<Hotel>>(hoteles) {})
                .build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHotelById(@PathParam("id") String id) throws HotelesAppException {
        Optional<Hotel> optionalHotel = getHotelByIdUsecase.execute(id);

        return optionalHotel.map(hotel -> Response.ok()
                                                        .entity(hotel)
                                                        .build())
                                .orElseThrow(() -> new HotelNotFoundAppException());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHotel(HotelDto hotelDto, @Context UriInfo uriInfo) throws HotelesAppException {
        Hotel hotel = Mappers.toHotel(hotelDto);
        Hotel newHotel = addHotelUsecase.execute(hotel);

        return Response.created( createHotelURI(uriInfo, newHotel) )
                       .entity(newHotel)
                       .build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHotel(@PathParam("id") String id, HotelDto hotelDto) throws HotelesAppException {
        Hotel hotel = Mappers.toHotel(hotelDto)
                             .withId(id);
        Hotel updatedHotel = updateHotelUsecase.execute(hotel);

        return Response.ok()
                       .entity(updatedHotel)
                       .build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteHotelById(@PathParam("id") String id) throws HotelesAppException {
        removeHotelByIdUsecase.execute(id);
        return Response.noContent()
                       .build();
    }

    private URI createHotelURI(UriInfo uriInfo, Hotel hotel) {
        return uriInfo.getBaseUriBuilder()
                      .path("/hoteles/{id}")
                      .resolveTemplate("id", hotel.getId())
                      .build();
    }
}
