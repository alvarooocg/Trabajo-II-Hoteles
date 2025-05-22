package es.upsa.dasi.web.application.reservas.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.reservas.FindAllReservasUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.ReservasGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class FindAllReservasUseCaseImpl implements FindAllReservasUseCase
{
    @Inject
    @RestClient
    ReservasGatewayRestClient restClient;

    @Override
    public List<Reserva> execute() throws HotelesAppException
    {
        return restClient.findReservas();
    }
}
