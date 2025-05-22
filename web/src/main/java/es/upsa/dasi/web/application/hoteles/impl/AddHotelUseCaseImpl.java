package es.upsa.dasi.web.application.hoteles.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.web.application.hoteles.AddHotelUseCase;
import es.upsa.dasi.web.infrastructure.rest.gateway.HotelesGatewayRestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AddHotelUseCaseImpl implements AddHotelUseCase {
    @Inject
    @RestClient
    HotelesGatewayRestClient hotelesGatewayRestClient;


    @Override
    public Hotel execute(HotelDto hotelDto) {
        return hotelesGatewayRestClient.addHotel(hotelDto);
    }
}
