package es.upsa.dasi.hoteles.wshoteles.application.impl;

import es.upsa.dasi.hoteles.wshoteles.application.UpdateHotelUsecase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.hoteles.wshoteles.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UpdateHotelUsecaseImpl implements UpdateHotelUsecase {
    @Inject
    Repository repository;

    @Override
    public Hotel execute(Hotel hotel) throws HotelesAppException {
        return repository.saveHotel(hotel);
    }
}
