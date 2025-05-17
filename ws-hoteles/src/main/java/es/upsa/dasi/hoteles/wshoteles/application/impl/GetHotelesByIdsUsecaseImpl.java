package es.upsa.dasi.hoteles.wshoteles.application.impl;

import es.upsa.dasi.hoteles.wshoteles.application.GetHotelesByIdsUsecase;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.hoteles.wshoteles.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetHotelesByIdsUsecaseImpl implements GetHotelesByIdsUsecase {
    @Inject
    Repository repository;

    @Override
    public List<Hotel> execute(List<String> ids) throws HotelesAppException {
        return repository.getHotelesByIds(ids);
    }
}
