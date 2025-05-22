package es.upsa.dasi.web.domain.exceptions;

import jakarta.ws.rs.NotFoundException;

public class HabitacionNotFoundRuntimeException extends NotFoundException
{
    public HabitacionNotFoundRuntimeException()
    {
        super("Habitacion no encontrada");
    }
}
