package es.upsa.dasi.trabajo_i_hoteles.domain.exceptions;

public class HotelNotFoundAppException extends HotelesAppException {
    public HotelNotFoundAppException(String message) {
        super(message);
    }
    public HotelNotFoundAppException() {
        super("Hotel no encontrado.");
    }
}
