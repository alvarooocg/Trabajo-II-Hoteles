package es.upsa.dasi.web.application.hoteles.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.hoteles.FindAllHotelesUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.HotelesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class FindAllHotelesUseCaseImpl implements FindAllHotelesUseCase {
    @Inject
    @RestClient
    HotelesGatewayRestClient hotelesGatewayRestClient;

    @Override
    public List<Hotel> execute() throws HotelesAppException {
        return hotelesGatewayRestClient.findHoteles();
    }
}
