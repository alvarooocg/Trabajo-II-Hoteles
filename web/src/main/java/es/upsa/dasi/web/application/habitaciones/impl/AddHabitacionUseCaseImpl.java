package es.upsa.dasi.web.application.habitaciones.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.web.application.habitaciones.AddHabitacionUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.HabitacionesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AddHabitacionUseCaseImpl implements AddHabitacionUseCase
{
    @Inject
    @RestClient
    HabitacionesGatewayRestClient restClient;

    @Override
    public Habitacion execute(HabitacionDto habitacionDto)
    {
        return restClient.addHabitacion(habitacionDto);
    }
}
