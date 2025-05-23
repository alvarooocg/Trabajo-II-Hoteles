package es.upsa.dasi.web.application.reservas.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.reservas.FindReservaByIdUseCase;
import es.upsa.dasi.web.domain.exceptions.ReservaNotFoundRuntimeException;
import es.upsa.dasi.web.infrastructure.rest.gateway.ReservasGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class FindReservaByIdUseCaseImpl implements FindReservaByIdUseCase
{
    @Inject
    @RestClient
    ReservasGatewayRestClient restClient;

    @Override
    public Optional<Reserva> execute(String id) throws HotelesAppException
    {
        try
        {
            Reserva reserva = restClient.findReservaById(id);
            return Optional.of(reserva);
        }catch (ReservaNotFoundRuntimeException exception)
        {
            return Optional.empty();
        }
    }
}
