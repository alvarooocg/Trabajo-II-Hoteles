package es.upsa.dasi.trabajo_i_hoteles.domain.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
@With
public class Cliente
{
    private String id;
    private String nombre;
    private String email;
    private String telefono;
}
