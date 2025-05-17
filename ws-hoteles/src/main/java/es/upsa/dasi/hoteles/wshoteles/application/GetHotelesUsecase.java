package es.upsa.dasi.hoteles.wshoteles.application;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;

public interface GetHotelesUsecase {
    List<Hotel> execute() throws HotelesAppException;
}
