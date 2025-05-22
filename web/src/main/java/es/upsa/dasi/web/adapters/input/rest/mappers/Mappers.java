package es.upsa.dasi.web.adapters.input.rest.mappers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.web.adapters.input.rest.dtos.ClienteForm;
import es.upsa.dasi.web.adapters.input.rest.dtos.HabitacionForm;

public class Mappers
{
    public static ClienteDto toClienteDto (ClienteForm clienteForm)
    {
        return ClienteDto.builder()
                         .withNombre(clienteForm.getNombre())
                         .withEmail(clienteForm.getEmail())
                         .withTelefono(clienteForm.getTelefono())
                         .build();
    }
    public static Cliente toCliente (ClienteForm clienteForm)
    {
        return Cliente.builder()
                      .withNombre(clienteForm.getNombre())
                      .withEmail(clienteForm.getEmail())
                      .withTelefono(clienteForm.getTelefono())
                      .build();
    }

    public static HabitacionDto toHabitacionDto (HabitacionForm habitacionForm)
    {
        return HabitacionDto.builder()
                .withId_hotel(habitacionForm.getId_hotel())
                .withNumero(habitacionForm.getNumero())
                .withTipo(habitacionForm.getTipo())
                .withPrecio(habitacionForm.getPrecio())
                .withDisponible(habitacionForm.getDisponible())
                .build();

    }
    public static Habitacion toHabitacion (HabitacionForm habitacionForm)
    {
        return Habitacion.builder()
                .withId_hotel(habitacionForm.getId_hotel())
                .withNumero(habitacionForm.getNumero())
                .withTipo(habitacionForm.getTipo())
                .withPrecio(habitacionForm.getPrecio())
                .withDisponible(habitacionForm.getDisponible())
                .build();


    }
}
