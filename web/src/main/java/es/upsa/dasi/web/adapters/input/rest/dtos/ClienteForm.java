package es.upsa.dasi.web.adapters.input.rest.dtos;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.*;
import jakarta.ws.rs.FormParam;
import lombok.Data;

@Data
public class ClienteForm
{
    @FormParam("id")
    @MvcBinding
    private String id;

    @FormParam("nombre")
    @MvcBinding
    @NotNull
    @Size(min = 1, max = 100)
    @NotBlank
    private String nombre;

    @FormParam("email")
    @MvcBinding
    @NotNull
    @Size(min = 5, max = 100)
    @NotBlank
    private String email;

    @FormParam("telefono")
    @MvcBinding
    @NotNull
    @Size(min = 6, max = 15)
    @NotBlank
    private String telefono;
}
