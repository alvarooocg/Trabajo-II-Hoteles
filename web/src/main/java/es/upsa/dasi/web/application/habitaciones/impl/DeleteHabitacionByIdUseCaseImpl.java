package es.upsa.dasi.web.application.habitaciones.impl;

import es.upsa.dasi.web.application.habitaciones.DeleteHabitacionByIdUseCase;

import es.upsa.dasi.web.infrastructure.rest.gateway.HabitacionesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class DeleteHabitacionByIdUseCaseImpl implements DeleteHabitacionByIdUseCase
{
    @Inject
    @RestClient
    HabitacionesGatewayRestClient restClient;

    @Override
    public void execute(String id)
    {
        restClient.deleteHabitacionById(id);
    }
}
