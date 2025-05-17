package es.upsa.dasi.web.domain.exceptions;

import jakarta.ws.rs.NotFoundException;

public class HotelNotFoundRuntimeException extends NotFoundException {
    public HotelNotFoundRuntimeException()
    {
        super("Hotel no encontrado");
    }
}
