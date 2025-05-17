package es.upsa.dasi.trabajo_i_hoteles.domain.entities;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@With
public class Hotel {
    private String id;
    private String nombre;
    private String ciudad;
    private Integer estrellas;
    private String descripcion;
}
