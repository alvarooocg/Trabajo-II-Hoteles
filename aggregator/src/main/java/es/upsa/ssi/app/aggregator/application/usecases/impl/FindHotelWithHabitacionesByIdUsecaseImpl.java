package es.upsa.ssi.app.aggregator.application.usecases.impl;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelNotFoundAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.ssi.app.aggregator.application.repository.Repository;
import es.upsa.ssi.app.aggregator.application.usecases.FindHotelWithHabitacionesByIdUsecase;
import es.upsa.ssi.app.aggregator.model.aggregators.HotelWithHabitaciones;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class FindHotelWithHabitacionesByIdUsecaseImpl implements FindHotelWithHabitacionesByIdUsecase {
    @Inject
    Repository repository;

    @Override
    public HotelWithHabitaciones execute(String id) throws HotelesAppException {
        Optional<Hotel> optionalHotel = repository.getHotelById(id);

        if(optionalHotel.isPresent()){
            Hotel hotel = optionalHotel.get();

            List<Habitacion> habitaciones = repository.getHabitacionesByHotel(id);

            HotelWithHabitaciones result = HotelWithHabitaciones.builder()
                    .withHotel(hotel)
                    .withHabitaciones(habitaciones)
                    .build();

            return result;
        }
        throw new HotelNotFoundAppException();
    }


}
