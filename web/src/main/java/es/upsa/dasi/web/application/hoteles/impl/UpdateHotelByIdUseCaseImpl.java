package es.upsa.dasi.web.application.hoteles.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.web.application.hoteles.UpdateHotelByIdUseCase;
import es.upsa.dasi.web.domain.exceptions.HotelNotFoundRuntimeException;
import es.upsa.dasi.web.infrastructure.rest.gateway.HotelesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.Optional;

@ApplicationScoped
public class UpdateHotelByIdUseCaseImpl implements UpdateHotelByIdUseCase {
    @Inject
    @RestClient
    HotelesGatewayRestClient hotelesGatewayRestClient;

    @Override
    public Optional<Hotel> execute(String id, HotelDto hotelDto) {
        try {
            Hotel hotel = hotelesGatewayRestClient.updateHotelById(id, hotelDto);
            return Optional.of(hotel);
        } catch (HotelNotFoundRuntimeException hotelNotFoundRuntimeException) {
            return Optional.empty();
        }
    }
}
