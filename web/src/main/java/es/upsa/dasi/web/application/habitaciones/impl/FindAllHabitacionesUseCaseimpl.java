package es.upsa.dasi.web.application.habitaciones.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.habitaciones.FindAllHabitacionesUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.HabitacionesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class FindAllHabitacionesUseCaseimpl implements FindAllHabitacionesUseCase
{
    @Inject
    @RestClient
    HabitacionesGatewayRestClient restClient;

    @Override
    public List<Habitacion> execute() throws HotelesAppException
    {
        return restClient.findHabitaciones();
    }
}
