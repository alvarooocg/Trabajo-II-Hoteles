package es.upsa.ssi.app.aggregator.application.usecases;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.ssi.app.aggregator.model.aggregators.HotelWithHabitaciones;

public interface FindHotelWithHabitacionesByIdUsecase {
    HotelWithHabitaciones execute(String id) throws HotelesAppException;
}
