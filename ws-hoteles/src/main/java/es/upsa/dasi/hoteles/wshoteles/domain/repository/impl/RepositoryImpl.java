package es.upsa.dasi.hoteles.wshoteles.domain.repository.impl;

import es.upsa.dasi.hoteles.wshoteles.adapters.output.persistence.Dao;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelNotFoundAppException;
import es.upsa.dasi.hoteles.wshoteles.domain.repository.Repository;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {
    @Inject
    Dao dao;

    @Override
    public List<Hotel> getHoteles() throws HotelesAppException {
        List<Hotel> hoteles = dao.findHoteles();
        return hoteles;
    }

    @Override
    public Optional<Hotel> getHotelById(String id) throws HotelesAppException {
        return dao.findHotelById(id);
    }

    @Override
    public List<Hotel> getHotelesByIds(List<String> ids) throws HotelesAppException {
        return dao.findHotelesByIds(ids);
    }

    @Override
    public Hotel saveHotel(Hotel hotel) throws HotelesAppException {
        return (hotel.getId() == null) ? dao.insertHotel(hotel) : dao.updateHotel(hotel)
                                                                     .orElseThrow(() -> new HotelNotFoundAppException());
    }

    @Override
    public void removeHotelById(String id) throws HotelesAppException {
        dao.deleteHotelById(id);
    }
}
