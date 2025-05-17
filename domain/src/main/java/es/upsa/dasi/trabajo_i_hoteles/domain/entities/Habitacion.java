package es.upsa.dasi.trabajo_i_hoteles.domain.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@With
public class Habitacion
{
    private String id;
    private String id_hotel;
    private String numero;
    private String tipo;
    private double precio;
    private Boolean disponible;
}
