package es.upsa.dasi.web.adapters.input.rest.mappers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.web.adapters.input.rest.dtos.ClienteForm;
import es.upsa.dasi.web.adapters.input.rest.dtos.HabitacionForm;
import es.upsa.dasi.web.adapters.input.rest.dtos.HotelForm;
import es.upsa.dasi.web.adapters.input.rest.dtos.ReservaForm;

import java.time.LocalDate;

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

    public static ReservaDto toReservaDto(ReservaForm reservaForm)
    {
        return ReservaDto.builder()
                .withId_habitacion(reservaForm.getId_habitacion())
                .withId_cliente(reservaForm.getId_cliente())
                .withFecha_entrada(LocalDate.parse(reservaForm.getFecha_entrada()))
                .withFecha_salida(LocalDate.parse(reservaForm.getFecha_salida()))
                .build();
    }

    public static Reserva toReserva(ReservaForm reservaForm)
    {
        return Reserva.builder()
                .withId_habitacion(reservaForm.getId_habitacion())
                .withId_cliente(reservaForm.getId_cliente())
                .withFecha_entrada(LocalDate.parse(reservaForm.getFecha_entrada()))
                .withFecha_salida(LocalDate.parse(reservaForm.getFecha_salida()))
                .build();
    }

    public static HotelDto toHotelDto(HotelForm hotelForm)
    {
        return HotelDto.builder()
                .withNombre(hotelForm.getNombre())
                .withCiudad(hotelForm.getCiudad())
                .withDescripcion(hotelForm.getDescripcion())
                .withEstrellas(hotelForm.getEstrellas())
                .build();
    }

    public static Hotel toHotel(HotelForm hotelForm)
    {
        return Hotel.builder()
                .withNombre(hotelForm.getNombre())
                .withCiudad(hotelForm.getCiudad())
                .withDescripcion(hotelForm.getDescripcion())
                .withEstrellas(hotelForm.getEstrellas())
                .build();
    }
}
