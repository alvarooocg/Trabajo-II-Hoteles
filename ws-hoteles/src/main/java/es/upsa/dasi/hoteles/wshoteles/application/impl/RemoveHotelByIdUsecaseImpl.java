package es.upsa.dasi.hoteles.wshoteles.application.impl;

import es.upsa.dasi.hoteles.wshoteles.application.RemoveHotelByIdUsecase;
import es.upsa.dasi.hoteles.wshoteles.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RemoveHotelByIdUsecaseImpl implements RemoveHotelByIdUsecase {
    @Inject
    Repository repository;

    @Override
    public void execute(String id) throws HotelesAppException {
        repository.removeHotelById(id);
    }
}
