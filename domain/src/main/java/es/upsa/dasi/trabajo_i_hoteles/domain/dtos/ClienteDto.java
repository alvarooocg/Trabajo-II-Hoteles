package es.upsa.dasi.trabajo_i_hoteles.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto
{
    private String nombre;
    private String email;
    private String telefono;
}
