package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.adapters.input.rest.dtos.ClienteForm;
import es.upsa.dasi.web.adapters.input.rest.mappers.Mappers;
import es.upsa.dasi.web.application.clientes.*;
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
@Path("/{locale}/clientes")
public class ClientesResource
{
    @Inject
    Models models;

    @Inject
    FindAllClientesUseCase findAllClientesUseCase;

    @Inject
    FindClienteByIdUseCase findClienteByIdUseCase;

    @Inject
    UpdateClienteByIdUseCase updateClienteByIdUseCase;

    @Inject
    AddClienteUseCase addClienteUseCase;

    @Inject
    DeleteClienteByIdUseCase deleteClienteByIdUseCase;

    @Inject
    MvcContext mvc;

    @Inject
    BindingResult bindingResult;

    @GET
    @Controller
    @UriRef("findAllClientes")
    //@View("/jsps/clientes/clientes.jsp")
    public Response findAllClientes () throws HotelesAppException
    {
        List<Cliente> clientes = findAllClientesUseCase.execute();
        models.put("clientes", clientes);
        return Response.ok("/jsps/clientes/clientes.jsp").build();
    }

    @GET
    @Path("/{id}")
    @Controller
    @UriRef("findClienteById")
    public Response findClienteById (@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Cliente> optCliente = findClienteByIdUseCase.execute(id);

        models.put("cliente", optCliente.get());
        return Response.ok("/jsps/clientes/cliente.jsp").build();
    }

    @PUT
    @Path("/{id}")
    @UriRef("updatePeliculaById")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response updateClienteById (@PathParam("id") String id, @Valid @BeanParam ClienteForm clienteForm) throws HotelesAppException
    {
        try
        {
            ClienteDto clienteDto = Mappers.toClienteDto(clienteForm);
            if (bindingResult.isFailed())
            {
                ParamError p;
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

                Cliente cliente = es.upsa.dasi.trabajo_i_hoteles.domain.mappers.Mappers.toCliente(clienteDto).withId(id);
                models.put("action", Action.UPDATE);
                models.put("cliente", cliente);
                models.put("errores", errores);
                return Response.ok("/jsps/clientes/cliente.jsp").build();
            }

            Optional<Cliente> optCliente = updateClienteByIdUseCase.execute(id, clienteDto);

            if (optCliente.isEmpty())
            {
                return Response.ok("/jsps/clientes/clienteNotFound.jsp").build();
            }

            return Response.seeOther(mvc.uri("findAllClientes", Map.of("locale", mvc.getLocale()))).build();
        }catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/error.jsp").build();
        }
    }

    @POST
    @UriRef("insertCliente")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertCliente()
    {
        try
        {

        }catch (InternalServerErrorException exception)
        {
            models.put("errorMessage", exception.getMessage());
            return Response.ok("/jsps/error.jsp").build();
        }
    }

    @DELETE
    @Path("/{id}")
    @UriRef("deleteClienteById")
    @Controller
    public Response deleteClienteById(@PathParam("id") String id)
    {
        return null;
    }



}
