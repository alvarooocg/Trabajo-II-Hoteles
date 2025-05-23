package es.upsa.dasi.web.application.reservas.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.web.application.reservas.UpdateReservaByIdUseCase;
import es.upsa.dasi.web.domain.exceptions.ReservaNotFoundRuntimeException;
import es.upsa.dasi.web.infrastructure.rest.gateway.ReservasGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class UpdateReservaByIdUseCaseImpl implements UpdateReservaByIdUseCase
{
    @Inject
    @RestClient
    ReservasGatewayRestClient restClient;


    @Override
    public Optional<Reserva> execute(String id, ReservaDto reservaDto)
    {
        try
        {
            Reserva reserva = restClient.updateReservaById(id, reservaDto);
            return Optional.of(reserva);
        }catch (ReservaNotFoundRuntimeException e)
        {
            return Optional.empty();
        }
    }
}
