package es.upsa.dasi.trabajo_i_hoteles.domain.exceptions;

public class HotelNotFoundAppException extends HotelesAppException {
    public HotelNotFoundAppException() {
        super("El hotel no existe");
    }
}
