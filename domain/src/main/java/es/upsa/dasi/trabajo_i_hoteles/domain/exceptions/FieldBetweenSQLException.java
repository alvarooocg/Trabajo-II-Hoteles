package es.upsa.dasi.trabajo_i_hoteles.domain.exceptions;

public class FieldBetweenSQLException extends HotelesAppException {
    public FieldBetweenSQLException(String fieldName, String minValue, String maxValue) {
        super("El campo " + fieldName + " debe estar comprendido entre " + minValue + " y " + maxValue);
    }
}
