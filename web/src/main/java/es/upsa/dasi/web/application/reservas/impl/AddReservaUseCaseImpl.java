package es.upsa.dasi.web.application.reservas.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.web.application.reservas.AddReservaUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.ReservasGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AddReservaUseCaseImpl implements AddReservaUseCase
{
    @Inject
    @RestClient
    ReservasGatewayRestClient restClient;

    @Override
    public Reserva execute(ReservaDto reservaDto)
    {
        return restClient.addReserva(reservaDto);
    }
}
