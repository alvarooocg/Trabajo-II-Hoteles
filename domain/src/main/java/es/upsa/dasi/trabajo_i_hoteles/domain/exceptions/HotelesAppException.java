package es.upsa.dasi.trabajo_i_hoteles.domain.exceptions;

public class HotelesAppException extends Exception
{
    public HotelesAppException()
    {
    }

    public HotelesAppException(String message)
    {
        super(message);
    }

    public HotelesAppException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public HotelesAppException(Throwable cause)
    {
        super(cause);
    }

    public HotelesAppException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
