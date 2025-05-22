package es.upsa.dasi.web.application.hoteles.impl;

import es.upsa.dasi.web.application.hoteles.DeleteHotelByIdUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.HotelesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class DeleteHotelByIdUseCaseImpl implements DeleteHotelByIdUseCase {
    @Inject
    @RestClient
    HotelesGatewayRestClient hotelesGatewayRestClient;

    @Override
    public void execute(String id) {
        hotelesGatewayRestClient.deleteHotelById(id);
    }
}
