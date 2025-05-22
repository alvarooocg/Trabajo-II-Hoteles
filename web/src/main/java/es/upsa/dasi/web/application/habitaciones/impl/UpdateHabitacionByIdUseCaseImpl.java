package es.upsa.dasi.web.application.habitaciones.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.web.application.habitaciones.UpdateHabitacionByIdUseCase;

import es.upsa.dasi.web.domain.exceptions.HabitacionNotFoundRuntimeException;
import es.upsa.dasi.web.infrastructure.rest.gateway.HabitacionesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class UpdateHabitacionByIdUseCaseImpl implements UpdateHabitacionByIdUseCase
{
    @Inject
    @RestClient
    HabitacionesGatewayRestClient restClient;

    @Override
    public Optional<Habitacion> execute(String id, HabitacionDto habitacionDto)
    {
        try
        {
            Habitacion habitacion = restClient.updateHabitacionById(id, habitacionDto);
            return Optional.of(habitacion);
        }catch (HabitacionNotFoundRuntimeException e)
        {
            return Optional.empty();
        }
    }
}
