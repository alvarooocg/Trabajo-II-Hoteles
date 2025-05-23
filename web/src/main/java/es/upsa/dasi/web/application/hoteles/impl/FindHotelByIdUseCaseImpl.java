package es.upsa.dasi.web.application.hoteles.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.web.application.hoteles.FIndHotelByIdUseCase;
import es.upsa.dasi.web.domain.exceptions.HotelNotFoundRuntimeException;
import es.upsa.dasi.web.infrastructure.rest.gateway.HotelesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class FindHotelByIdUseCaseImpl implements FIndHotelByIdUseCase {
    @Inject
    @RestClient
    HotelesGatewayRestClient hotelesGatewayRestClient;

    @Override
    public Optional<Hotel> execute(String id) throws HotelesAppException {
        try {
            Hotel hotel = hotelesGatewayRestClient.findHotelById(id);
            return Optional.of(hotel);
        } catch (HotelNotFoundRuntimeException exception) {
            return Optional.empty();
        }
    }
}
