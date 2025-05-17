package es.upsa.dasi.web.adapters.input.rest;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.clientes.FindAllClientesUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.mvc.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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

    @GET
    @Controller
    @UriRef("findAllClientes")
    @View("/jsps/clientes/clientes.jsp")
    public void findAllClientes () throws HotelesAppException
    {
        List<Cliente> clientes = findAllClientesUseCase.execute();
        models.put("clientes", clientes);
    }

    @GET
    @PATH("/{id}")
    @Controller
    @UriRef("findClienteById")
    public Response findClienteById (@PathParam("id") String id) throws HotelesAppException
    {
        Optional<Cliente> optCliente = findAllClientesUseCase.execute(id);

        models.put("cliente", optCliente.get());
        return Response.ok("/jsps/clientes/cliente.jsp")).build();
    }




}
