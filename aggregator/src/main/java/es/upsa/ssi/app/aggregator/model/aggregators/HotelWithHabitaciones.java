package es.upsa.ssi.app.aggregator.model.aggregators;

import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.*;

import java.util.List;

@Data
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class HotelWithHabitaciones {
    public Hotel hotel;
    @Singular
    public List<Habitacion> habitaciones;
}
