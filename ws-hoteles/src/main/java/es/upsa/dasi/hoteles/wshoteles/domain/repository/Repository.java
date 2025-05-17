package es.upsa.dasi.hoteles.wshoteles.domain.repository;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Repository
{
    List<Hotel> getHoteles() throws HotelesAppException;
    Optional<Hotel> getHotelById(String id) throws HotelesAppException;
    List<Hotel> getHotelesByIds(List<String> ids) throws HotelesAppException;
    Hotel saveHotel(Hotel hotel) throws HotelesAppException;
    void removeHotelById(String id) throws HotelesAppException;
}
