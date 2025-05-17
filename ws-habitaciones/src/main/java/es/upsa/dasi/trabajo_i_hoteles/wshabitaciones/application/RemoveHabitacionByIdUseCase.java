package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.application;


import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface RemoveHabitacionByIdUseCase
{
    void execute(String id) throws HotelesAppException;
}
