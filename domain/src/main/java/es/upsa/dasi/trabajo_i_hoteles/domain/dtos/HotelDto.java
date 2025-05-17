package es.upsa.dasi.trabajo_i_hoteles.domain.dtos;

import lombok.*;

@Data
public class HotelDto {
    private String nombre;
    private String ciudad;
    private Integer estrellas;
    private String descripcion;
}
