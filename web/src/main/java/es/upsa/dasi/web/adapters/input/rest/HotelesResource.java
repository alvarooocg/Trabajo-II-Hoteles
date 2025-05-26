package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelNotFoundAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.HotelForm;
import es.upsa.dasi.web.adapters.input.rest.mappers.Mappers;
import es.upsa.dasi.web.application.hoteles.*;
import es.upsa.dasi.web.domain.exceptions.HotelNotFoundRuntimeException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.mvc.binding.ParamError;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.krazo.binding.BindingResultImpl;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/{locale}/hoteles")
public class HotelesResource
{
    @Inject
    FindAllHotelesUseCase findAllHotelesUseCase;

    @Inject
    FIndHotelByIdUseCase fIndHotelByIdUseCase;

    @Inject
    UpdateHotelByIdUseCase updateHotelByIdUseCase;

    @Inject
    AddHotelUseCase addHotelUseCase;

    @Inject
    DeleteHotelByIdUseCase deleteHotelByIdUseCase;

    @Inject
    MvcContext mvc;

    @Inject
    Models models;


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
    public Response findHotelById(@PathParam("id") String id) throws HotelesAppException {
        Optional<Hotel> optHotel = fIndHotelByIdUseCase.execute(id);
        if(optHotel.isEmpty()) return Response.ok("/jsps/hoteles/hotelNotFound.jsp").build();

        models.put("action", Action.VIEW);
        models.put("hotel", optHotel.get());
        return Response.ok("/jsps/hoteles/hotel.jsp").build();
    }

    @PUT
    @Path("/{id}")
    @UriRef("updateHotelById")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateHotelById(@PathParam("id") String id, @Valid @BeanParam HotelForm hotelForm) {
        try {
            HotelDto hotelDto = Mappers.toHotelDto(hotelForm);


            if(bindingResultImpl.isFailed()) {
                Map<String, List<String>> errors = new HashMap<>();
                Set<ParamError> allErrors = bindingResultImpl.getAllErrors();

            if(bindingResult.isFailed()) {
                Map<String, String> errors = new HashMap<>();
                Set<ParamError> allErrors = bindingResult.getAllErrors();

                for(ParamError error : allErrors) {
                    List<String> messages = errors.get(error.getParamName());
                    if(messages == null) messages = new ArrayList<>();

                    errors.put(error.getParamName(), messages);
                }

                bindingResult.getAllErrors().stream()
                        .collect(Collectors.groupingBy(paramError -> paramError.getParamName()));

                List<String> errores = bindingResult.getAllMessages();
                Hotel hotel = es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers.toHotel(hotelDto);
                models.put("action", Action.UPDATE);
                models.put("hotel", hotel);
                models.put("errors", errores);
                return Response.ok("/jsps/hoteles/hotel.jsp").build();
            }
            Optional<Hotel> optionalHotel = updateHotelByIdUseCase.execute(id, hotelDto);

            if(optionalHotel.isEmpty()) return Response.ok("/jsps/hoteles/hotelNotFound.jsp").build();

            return Response.seeOther(mvc.uri("findAllHoteles", Map.of("locale", mvc.getLocale()))).build();
        } catch (InternalServerErrorException exception) {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/hoteles/errorHotel.jsp").build();
        }
    }

    @POST
    @UriRef("insertHotel")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertHotel(@Valid @BeanParam HotelForm hotelForm) {
        try {
            if (bindingResult.isFailed()) {
                Hotel hotel = Mappers.toHotel(hotelForm);
                Map<String, List<String>> errores = new HashMap<>();

                for(ParamError paramError : bindingResult.getAllErrors()) {
                    List<String> paramErrors = errores.get(paramError.getParamName());
                    if(paramErrors == null) {
                        paramErrors = new ArrayList<>();
                        errores.put(paramError.getParamName(), paramErrors);
                    }
                    paramErrors.add(paramError.getMessage());
                }

                models.put("action", Action.INSERT);
                models.put("hotel", hotel);
                models.put("errores", errores);
                return Response.ok("/jsps/hoteles/hotel.jsp").build();
            }
            HotelDto hotelDto = Mappers.toHotelDto(hotelForm);
            Hotel hotel = addHotelUseCase.execute(hotelDto);

            return Response.seeOther(mvc.uri("findAllHoteles", Map.of("locale", mvc.getLocale()))).build();
        } catch (InternalServerErrorException exception) {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("jsps/hoteles/errorHotel.jsp").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @UriRef("deleteHotelById")
    @Controller
    public Response deleteHotelById(@PathParam("id") String id) {
        try {
            deleteHotelByIdUseCase.execute(id);
            return Response.seeOther(mvc.uri("findAllHoteles", Map.of("locale", mvc.getLocale()))).build();
        } catch (HotelNotFoundRuntimeException exception) {
            return Response.ok("/jsps/hoteles/hotelNotFound.jsp").build();
        } catch (InternalServerErrorException exception) {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/hoteles/errorHotel.jsp").build();
        }
    }
}
