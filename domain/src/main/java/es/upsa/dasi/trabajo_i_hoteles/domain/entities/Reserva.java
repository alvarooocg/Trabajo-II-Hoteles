package es.upsa.dasi.trabajo_i_hoteles.domain.entities;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@With
public class Reserva
{
    private String id;
    private String id_cliente;
    private String id_habitacion;
    private LocalDate fecha_entrada;
    private LocalDate fecha_salida;
}
