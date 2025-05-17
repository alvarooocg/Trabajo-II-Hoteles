package es.upsa.dasi.trabajo_i_hoteles.domain.dtos;

import lombok.Data;

@Data
public class HabitacionDto
{
    private String id_hotel;
    private String numero;
    private String tipo;
    private double precio;
    private Boolean disponible;
}
