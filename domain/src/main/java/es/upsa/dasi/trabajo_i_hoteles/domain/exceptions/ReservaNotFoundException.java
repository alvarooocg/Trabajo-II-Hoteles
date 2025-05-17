package es.upsa.dasi.trabajo_i_hoteles.domain.exceptions;

public class ReservaNotFoundException extends HotelesAppException {
    public ReservaNotFoundException() {
        super("La reserva no existe");
    }
}
