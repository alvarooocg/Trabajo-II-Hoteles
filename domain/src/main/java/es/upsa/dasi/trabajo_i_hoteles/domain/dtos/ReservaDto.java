package es.upsa.dasi.trabajo_i_hoteles.domain.dtos;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto
{
    private String id_cliente;
    private String id_habitacion;
    private LocalDate fecha_entrada;
    private LocalDate fecha_salida;
}
