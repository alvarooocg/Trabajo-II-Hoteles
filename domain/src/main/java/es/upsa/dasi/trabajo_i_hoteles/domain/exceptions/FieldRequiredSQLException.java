package es.upsa.dasi.trabajo_i_hoteles.domain.exceptions;

public class FieldRequiredSQLException extends HotelesAppException {
    public FieldRequiredSQLException(String fieldName) {
        super("El campo " + fieldName + " no puede estar vacio");
    }
}
