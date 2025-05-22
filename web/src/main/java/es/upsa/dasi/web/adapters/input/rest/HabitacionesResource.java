package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.adapters.input.rest.dtos.HabitacionForm;
import es.upsa.dasi.web.adapters.input.rest.mappers.Mappers;
import es.upsa.dasi.web.application.habitaciones.*;

import es.upsa.dasi.web.domain.exceptions.HabitacionNotFoundRuntimeException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.stream.Collectors;


@ApplicationScoped
@Path("/{locale}/habitaciones")
public class HabitacionesResource
{
    @Inject
    Models models;

    @Inject
    FindAllHabitacionesUseCase findAllHabitacionesUseCase;

    @Inject
    FindHabitacionByIdUseCase findHabitacionByIdUseCase;

    @Inject
    UpdateHabitacionByIdUseCase updateHabitacionByIdUseCase;

    @Inject
    AddHabitacionUseCase addHabitacionUseCase;

    @Inject
    DeleteHabitacionByIdUseCase deleteHabitacionByIdUseCase;

    @Inject
    MvcContext mvc;

    @Inject
    BindingResult bindingResult;

    @GET
    @Controller
    @UriRef("findAllHabitaciones")
    //@View("/jsps/habitaciones/habitaciones.jsp")
    public Response findAllHabitaciones () throws HotelesAppException
    {
        List<Habitacion> habitaciones = findAllHabitacionesUseCase.execute();
        models.put("habitaciones", habitaciones);
        return Response.ok("/jsps/habitaciones/habitaciones.jsp").build();
    }

    @GET
    @Path("/{id}")
    @Controller
    @UriRef("findHabitacionById")
    public Response findHabitacionById (@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Habitacion> optHabitacion = findHabitacionByIdUseCase.execute(id);
        if (optHabitacion.isPresent()) {
            models.put("habitacion", optHabitacion.get());
            return Response.ok("/jsps/habitaciones/habitacion.jsp").build();
        }else
        {
            throw new HabitacionNotFoundRuntimeException();
        }
    }

    @PUT
    @Path("/{id}")
    @UriRef("updateHabitacionById")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateHabitacionById (@PathParam("id") String id, @Valid @BeanParam HabitacionForm habitacionForm) throws HotelesAppException
    {
        try
        {
            HabitacionDto habitacionDto = Mappers.toHabitacionDto(habitacionForm);
            if (bindingResult.isFailed())
            {
                Map<String, List<String>> errores = new HashMap<>();
                Set<ParamError> allErrors = bindingResult.getAllErrors();
                for (ParamError paramError : allErrors)
                {
                    List<String> messages = errores.get(paramError.getParamName());
                    if (messages == null) messages = new ArrayList<>();
                    errores.put(paramError.getParamName(), messages);
                }

                Map<String, List<String>> errs = bindingResult.getAllErrors()
                        .stream()
                        .collect(Collectors.groupingBy(paramError -> paramError.getParamName(),
                                Collectors.mapping(paramError -> paramError.getMessage(),
                                        Collectors.toList())
                        ) );

                Habitacion habitacion = es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers.toHabitacion(habitacionDto).withId(id);
                models.put("action", Action.UPDATE);
                models.put("habitacion", habitacion);
                models.put("errores", errores);
                return Response.ok("/jsps/habitaciones/habitacion.jsp").build();
            }

            Optional<Habitacion> optHabitacion = updateHabitacionByIdUseCase.execute(id, habitacionDto);

            if (optHabitacion.isEmpty())
            {
                return Response.ok("/jsps/habitaciones/habitacionNotFound.jsp").build();
            }

            return Response.seeOther(mvc.uri("findAllHabitaciones", Map.of("locale", mvc.getLocale()))).build();
        }catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/habitaciones/errorHabitacion.jsp").build();
        }
    }

    @POST
    @UriRef("insertHabitacion")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertHabitacion(@Valid @BeanParam HabitacionForm habitacionForm)
    {
        try
        {
            if ( bindingResult.isFailed() )
            {
                Habitacion habitacion = Mappers.toHabitacion(habitacionForm);
                Map<String, List<String>> errores = new HashMap<>();

                for (ParamError paramError : bindingResult.getAllErrors())
                {
                    List<String> paramErrores = errores.get(paramError.getParamName());
                    if (paramErrores == null)
                    {
                        paramErrores = new ArrayList<>();
                        errores.put(paramError.getParamName(), paramErrores);
                    }
                    paramErrores.add( paramError.getMessage() );
                }

                models.put("action", Action.INSERT);
                models.put("habitacion", habitacion);
                models.put("errores", errores);
                return Response.ok("/jsps/habitaciones/habitacion.jsp").build();
            }

            HabitacionDto habitacionDto = Mappers.toHabitacionDto(habitacionForm);
            Habitacion habitacion = addHabitacionUseCase.execute(habitacionDto);

            return Response.seeOther(mvc.uri("findAllHabitaciones", Map.of("locale", mvc.getLocale()))).build();
        } catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/habitaciones/errorHabitacion.jsp").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @UriRef("deleteHabitacionById")
    @Controller
    public Response deleteHabitacionById(@PathParam("id") String id) throws HotelesAppException
    {
        try
        {
            deleteHabitacionByIdUseCase.execute(id);
            return Response.seeOther(mvc.uri("findAllHabitaciones", Map.of("locale", mvc.getLocale()))).build();
        } catch (HabitacionNotFoundRuntimeException exception)
        {
            return Response.ok("/jsps/habitaciones/habitacionNotFound.jsp").build();
        }
        catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/habitaciones/errorHabitacion.jsp").build();
        }
    }

}
