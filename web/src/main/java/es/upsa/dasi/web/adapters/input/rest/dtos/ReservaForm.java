package es.upsa.dasi.web.adapters.input.rest.dtos;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class ReservaForm
{
    @FormParam("id")
    @MvcBinding
    private String id;

    @FormParam("id_cliente")
    @MvcBinding
    @NotNull
    @NotBlank
    private String id_cliente;

    @FormParam("id_habitacion")
    @MvcBinding
    @NotNull
    @NotBlank
    private String id_habitacion;

    @FormParam("fecha_entrada")
    @MvcBinding
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe tener formato yyyy-MM-dd")
    private String fecha_entrada;

    @FormParam("fecha_salida")
    @MvcBinding
    @NotNull
    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "La fecha debe tener formato yyyy-MM-dd")
    private String fecha_salida;
}
