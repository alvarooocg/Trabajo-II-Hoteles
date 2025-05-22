package es.upsa.dasi.web.adapters.input.rest.dtos;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class HabitacionForm
{
    @FormParam("id")
    @MvcBinding
    private String id;

    @FormParam("id_hotel")
    @MvcBinding
    @NotNull
    @NotBlank
    private String id_hotel;

    @FormParam("numero")
    @MvcBinding
    @NotNull
    @Size(min = 1, max = 20)
    @NotBlank
    private String numero;

    @FormParam("tipo")
    @MvcBinding
    @NotNull
    @Size(min = 1, max = 50)
    @NotBlank
    private String tipo;

    @FormParam("precio")
    @MvcBinding
    @NotNull
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    private Double precio;

    @FormParam("disponible")
    @MvcBinding
    private Boolean disponible;
}
