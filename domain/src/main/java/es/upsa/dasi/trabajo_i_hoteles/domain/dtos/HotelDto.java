package es.upsa.dasi.trabajo_i_hoteles.domain.dtos;

import lombok.*;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    private String nombre;
    private String ciudad;
    private Integer estrellas;
    private String descripcion;
}
