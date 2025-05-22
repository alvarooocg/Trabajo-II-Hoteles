package es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.exceptions;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public class ClienteHasReservasSQLException extends HotelesAppException
{
    public ClienteHasReservasSQLException() {
        super("El cliente no puede eliminarse porque tiene asociadas reservas");
    }
}