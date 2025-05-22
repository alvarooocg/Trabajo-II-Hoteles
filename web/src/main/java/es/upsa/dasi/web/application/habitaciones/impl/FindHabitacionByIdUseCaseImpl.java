package es.upsa.dasi.web.application.habitaciones.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.habitaciones.FindHabitacionByIdUseCase;

import es.upsa.dasi.web.domain.exceptions.HabitacionNotFoundRuntimeException;
import es.upsa.dasi.web.infrastructure.rest.gateway.HabitacionesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class FindHabitacionByIdUseCaseImpl implements FindHabitacionByIdUseCase
{
    @Inject
    @RestClient
    HabitacionesGatewayRestClient restClient;

    @Override
    public Optional<Habitacion> execute(String id) throws HotelesAppException
    {
        try
        {
            Habitacion habitacion = restClient.findHabitacionById(id);
            return Optional.of(habitacion);
        }catch (HabitacionNotFoundRuntimeException exception)
        {
            return Optional.empty();
        }
    }
}
