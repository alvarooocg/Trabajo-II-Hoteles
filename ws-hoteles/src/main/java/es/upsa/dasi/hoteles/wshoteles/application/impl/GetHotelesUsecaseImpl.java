package es.upsa.dasi.hoteles.wshoteles.application.impl;

import es.upsa.dasi.hoteles.wshoteles.application.GetHotelesUsecase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.hoteles.wshoteles.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetHotelesUsecaseImpl implements GetHotelesUsecase {
    @Inject
    Repository repository;

    @Override
    public List<Hotel> execute() throws HotelesAppException {
        List<Hotel> hoteles = repository.getHoteles();
        return hoteles;
    }
}
