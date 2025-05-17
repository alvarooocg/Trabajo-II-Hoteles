package es.upsa.dasi.hoteles.wshoteles.application;


import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

public interface RemoveHotelByIdUsecase {
    void execute(String id) throws HotelesAppException;
}
