package es.upsa.dasi.web.adapters.input.rest.dtos;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class HotelForm {
    @FormParam("nombre")
    @MvcBinding
    @NotNull
    @NotBlank
    @Size(min = 1, max = 100)
    String nombre;

    @FormParam("ciudad")
    @MvcBinding
    @NotNull
    @NotBlank
    @Size(min = 1, max = 50)
    String ciudad;

    @FormParam("estrellas")
    @MvcBinding
    @Digits(integer = 1, fraction = 0)
    @DecimalMin(value = "1")
    @DecimalMax(value = "5")
    int estrellas;

    @FormParam("descripcion")
    @MvcBinding
    @NotNull
    @NotBlank
    @Size(min = 10, max = 400)
    String descripcion;
}
