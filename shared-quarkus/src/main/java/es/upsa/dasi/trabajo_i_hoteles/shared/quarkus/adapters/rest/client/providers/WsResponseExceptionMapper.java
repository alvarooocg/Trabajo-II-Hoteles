package es.upsa.dasi.trabajo_i_hoteles.shared.quarkus.adapters.rest.client.providers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ErrorDto;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class WsResponseExceptionMapper<E extends Exception> implements ResponseExceptionMapper<E> {
    private Supplier<E> notFoundExceptionSupplier;
    private Function<String, E> defaultExceptionFunction;

    public WsResponseExceptionMapper(Function<String, E> defaultExceptionFunction, Supplier<E> notFoundExceptionSupplier)
    {
        this.defaultExceptionFunction = defaultExceptionFunction;
        this.notFoundExceptionSupplier = notFoundExceptionSupplier;
    }

    @Override
    public E toThrowable(Response response)
    {
        return switch ( response.getStatusInfo().toEnum() )
        {
            case NOT_FOUND -> notFoundExceptionSupplier.get();
            default        -> {
                ErrorDto errorDto = response.readEntity(ErrorDto.class);
                yield defaultExceptionFunction.apply(errorDto.getMessage());
            }
        };
    }
}
