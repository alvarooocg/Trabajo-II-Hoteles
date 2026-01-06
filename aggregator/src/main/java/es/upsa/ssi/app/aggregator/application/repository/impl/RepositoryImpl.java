package es.upsa.ssi.app.aggregator.application.repository.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelNotFoundAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.ssi.app.aggregator.adapters.output.rest.HabitacionesServiceClient;
import es.upsa.ssi.app.aggregator.adapters.output.rest.HotelesServiceClient;
import es.upsa.ssi.app.aggregator.application.repository.Repository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RepositoryImpl implements Repository {

    @Inject
    @RestClient
    HotelesServiceClient hotelesServiceClient;

    @Inject
    @RestClient
    HabitacionesServiceClient habitacionesServiceClient;

    @Override
    public Optional<Hotel> getHotelById(String id) throws HotelesAppException {
        try {
            Hotel hotel = hotelesServiceClient.requestHotelById(id);
            return Optional.of(hotel);
        } catch (HotelesAppException hotelesAppException) {
            switch (hotelesAppException) {
                case HotelNotFoundAppException hotelNotFoundAppException:
                    System.out.println("Hotel no encontrado");
                    return Optional.empty();
                default: throw hotelesAppException;
            }
        }
    }

    @Override
    public List<Habitacion> getHabitacionesByHotel(String id) throws HotelesAppException {
        try {
            List<Habitacion> habitaciones = habitacionesServiceClient.requestHabitacionesByHotelId(id);
            return habitaciones;
        } catch (Exception e) {
            System.out.println("ERROR en getHabitacionesByHotel: " + e.getClass().getName());
            System.out.println("Mensaje: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

}
