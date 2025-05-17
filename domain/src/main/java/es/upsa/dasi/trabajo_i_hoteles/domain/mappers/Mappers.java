package es.upsa.dasi.trabajo_i_hoteles.domain.mappers;

import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ClienteDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HabitacionDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.HotelDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.dtos.ReservaDto;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;

public class Mappers
{
    public static Cliente toCliente (ClienteDto clienteDto)
    {
        return Cliente.builder()
                      .withId(null)
                      .withNombre( clienteDto.getNombre() )
                      .withEmail( clienteDto.getEmail() )
                      .withTelefono( clienteDto.getTelefono() )
                      .build();
    }

    public static Hotel toHotel(HotelDto hotelDto) {
        return Hotel.builder()
                .withId(null)
                .withNombre(hotelDto.getNombre())
                .withCiudad(hotelDto.getCiudad())
                .withEstrellas(hotelDto.getEstrellas())
                .withDescripcion(hotelDto.getDescripcion())
                .build();
    }

    public static Reserva toReserva(ReservaDto reservaDTO)
    {
        return Reserva.builder()
                .withId(null)
                .withId_cliente(reservaDTO.getId_cliente())
                .withId_habitacion(reservaDTO.getId_habitacion())
                .withFecha_entrada(reservaDTO.getFecha_entrada())
                .withFecha_salida(reservaDTO.getFecha_salida())
                .build();
    }

    public static Habitacion toHabitacion (HabitacionDto habitacionDto)
    {
        return Habitacion.builder()
                .withId(null)
                .withId_hotel(habitacionDto.getId_hotel())
                .withNumero(habitacionDto.getNumero())
                .withTipo(habitacionDto.getTipo())
                .withPrecio(habitacionDto.getPrecio())
                .withDisponible(habitacionDto.getDisponible())
                .build();
    }
}
