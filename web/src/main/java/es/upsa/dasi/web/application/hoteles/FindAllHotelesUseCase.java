package es.upsa.dasi.web.application.hoteles;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;

import java.util.List;

public interface FindAllHotelesUseCase {
    List<Hotel> execute() throws HotelesAppException;
}
