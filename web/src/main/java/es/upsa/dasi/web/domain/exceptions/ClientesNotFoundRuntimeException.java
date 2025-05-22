package es.upsa.dasi.web.domain.exceptions;

import jakarta.ws.rs.NotFoundException;

public class ClientesNotFoundRuntimeException extends NotFoundException
{
    public ClientesNotFoundRuntimeException() { super("Cliente no encontrado"); }
}
