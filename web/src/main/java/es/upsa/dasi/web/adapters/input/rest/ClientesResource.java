package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.clientes.FindAllClientesUseCase;
import es.upsa.dasi.web.application.clientes.FindClienteByIdUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;


@ApplicationScoped
@Path("/{locale}/clientes")
public class ClientesResource
{
    @Inject
    Models models;

    @Inject
    MvcContext mvc;

    @Inject
    FindAllClientesUseCase findAllClientesUseCase;

    @Inject
    FindClienteByIdUseCase findClienteByIdUseCase;

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
    public Response updateClienteById (@PathParam("id") String id)
    {
        return Response.ok().build();
    }

    @POST
    @UriRef("insertCliente")
    @Controller
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response insertCliente()
    {
        return null;
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
