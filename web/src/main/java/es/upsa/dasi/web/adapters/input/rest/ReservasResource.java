package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.adapters.input.rest.dtos.ReservaForm;
import es.upsa.dasi.web.adapters.input.rest.mappers.Mappers;
import es.upsa.dasi.web.application.reservas.*;
import es.upsa.dasi.web.domain.exceptions.ReservaNotFoundRuntimeException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.MvcContext;
import jakarta.mvc.UriRef;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.ParamError;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/{locale}/reservas")
public class ReservasResource
{
    @Inject
    Models models;

    @Inject
    FindAllReservasUseCase findAllReservasUseCase;

    @Inject
    FindReservaByIdUseCase findReservaByIdUseCase;

    @Inject
    UpdateReservaByIdUseCase updateReservaByIdUseCase;

    @Inject
    AddReservaUseCase addReservaUseCase;

    @Inject
    DeleteReservaByIdUseCase deleteReservaByIdUseCase;

    @Inject
    MvcContext mvc;

    @Inject
    BindingResult bindingResult;

    @GET
    @Controller
    @UriRef("findAllReservas")
    //@View("/jsps/reservas/reservas.jsp")
    public Response findAllReservas () throws HotelesAppException
    {
        List<Reserva> reservas = findAllReservasUseCase.execute();
        models.put("reservas", reservas);
        return Response.ok("/jsps/reservas/reservas.jsp").build();
    }

    @GET
    @Path("/{id}")
    @Controller
    @UriRef("findReservaById")
    public Response findReservaById (@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Reserva> optReserva = findReservaByIdUseCase.execute(id);
        if (optReserva.isPresent()) {
            models.put("reserva", optReserva.get());
            return Response.ok("/jsps/reservas/reserva.jsp").build();
        }else
        {
            throw new ReservaNotFoundRuntimeException();
        }
    }

    @PUT
    @Path("/{id}")
    @UriRef("updateReservaById")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateReservaById (@PathParam("id") String id, @Valid @BeanParam ReservaForm reservaForm) throws HotelesAppException
    {
        try
        {
            ReservaDto reservaDto = Mappers.toReservaDto(reservaForm);
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

                Reserva reserva = es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers.toReserva(reservaDto).withId(id);
                models.put("action", Action.UPDATE);
                models.put("reserva", reserva);
                models.put("errores", errores);
                return Response.ok("/jsps/reservas/reserva.jsp").build();
            }

            Optional<Reserva> optReserva = updateReservaByIdUseCase.execute(id, reservaDto);

            if (optReserva.isEmpty())
            {
                return Response.ok("/jsps/reservas/reservaNotFound.jsp").build();
            }

            return Response.seeOther(mvc.uri("findAllReservas", Map.of("locale", mvc.getLocale()))).build();
        }catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/reservas/errorReserva.jsp").build();
        }
    }

    @POST
    @UriRef("insertReserva")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertReserva(@Valid @BeanParam ReservaForm reservaForm)
    {
        try
        {
            if ( bindingResult.isFailed() )
            {
                Reserva reserva = Mappers.toReserva(reservaForm);
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
                models.put("reserva", reserva);
                models.put("errores", errores);
                return Response.ok("/jsps/reservas/reserva.jsp").build();
            }

            ReservaDto reservaDto = Mappers.toReservaDto(reservaForm);
            Reserva reserva = addReservaUseCase.execute(reservaDto);

            return Response.seeOther(mvc.uri("findAllReservas", Map.of("locale", mvc.getLocale()))).build();
        } catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/reservas/errorReserva.jsp").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @UriRef("deleteReservaById")
    @Controller
    public Response deleteReservaById(@PathParam("id") String id) throws HotelesAppException
    {
        try
        {
            deleteReservaByIdUseCase.execute(id);
            return Response.seeOther(mvc.uri("findAllReservas", Map.of("locale", mvc.getLocale()))).build();
        } catch (ReservaNotFoundRuntimeException exception)
        {
            return Response.ok("/jsps/reservas/reservaNotFound.jsp").build();
        }
        catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/reservas/errorReserva.jsp").build();
        }
    }
}
