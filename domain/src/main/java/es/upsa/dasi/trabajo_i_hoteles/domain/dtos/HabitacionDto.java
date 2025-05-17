package es.upsa.dasi.trabajo_i_hoteles.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class HabitacionDto
{
    private String id_hotel;
    private String numero;
    private String tipo;
    private double precio;
    private Boolean disponible;
}
