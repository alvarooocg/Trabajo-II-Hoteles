package es.upsa.dasi.hoteles.wshoteles.application.impl;

import es.upsa.dasi.hoteles.wshoteles.application.GetHotelByIdUsecase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.hoteles.wshoteles.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Optional;
@ApplicationScoped
public class GetHotelByIdUsecaseImpl implements GetHotelByIdUsecase {
    @Inject
    Repository repository;

    @Override
    public Optional<Hotel> execute(String id) throws HotelesAppException {
        return repository.getHotelById(id);
    }
}
