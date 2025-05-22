package es.upsa.dasi.web.domain.exceptions;

import jakarta.ws.rs.NotFoundException;

public class ReservaNotFoundRuntimeException extends NotFoundException
{
    public ReservaNotFoundRuntimeException()
    {
        super("Reserva no encontrada");
    }
}
