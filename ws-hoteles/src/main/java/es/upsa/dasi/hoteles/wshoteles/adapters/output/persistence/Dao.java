package es.upsa.dasi.hoteles.wshoteles.adapters.output.persistence;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;
import java.util.Optional;

public interface Dao {
    List<Hotel> findHoteles() throws HotelesAppException;
    Optional<Hotel> findHotelById(String id) throws HotelesAppException;
    List<Hotel> findHotelesByIds(List<String> ids) throws HotelesAppException;
    Hotel insertHotel(Hotel hotel) throws HotelesAppException;
    Optional<Hotel> updateHotel(Hotel hotel) throws HotelesAppException;
    void deleteHotelById(String id) throws HotelesAppException;
}
