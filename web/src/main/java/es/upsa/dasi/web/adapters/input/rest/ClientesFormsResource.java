package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.adapters.input.rest.dtos.Action;
import es.upsa.dasi.web.application.clientes.FindClienteByIdUseCase;
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
public class ClientesFormsResource
{
    @Inject
    FindClienteByIdUseCase findClienteByIdUseCase;

    @Inject
    Models models;

    @GET
    @Path("/update/cliente/{id}")
    @Controller
    @UriRef("formUpdateClienteById")
    public Response formUpdateClienteById(@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Cliente> clienteOpt = findClienteByIdUseCase.execute(id);
        if (clienteOpt.isEmpty()) return Response.ok("/jsps/clientes/clienteNotFound.jsp").build();

        models.put("action", Action.UPDATE);
        models.put("cliente", clienteOpt.get());
        return Response.ok("/jsps/clientes/cliente.jsp").build();
    }

}
