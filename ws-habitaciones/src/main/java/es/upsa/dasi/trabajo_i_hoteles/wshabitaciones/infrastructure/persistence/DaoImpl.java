package es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.infrastructure.persistence;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.FieldRequiredSQLException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.SQLHotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wshabitaciones.adapters.output.persistence.Dao;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Habitacion;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HabitacionNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao
{
    @Inject
    DataSource dataSource;

    @Override
    public List<Habitacion> selectAll() throws HotelesAppException
    {
        final String SQL = """
                                SELECT h.id, h.id_hotel, h.numero, h.tipo, h.precio, h.disponible
                                FROM habitaciones h
                           """;

        List<Habitacion> habitaciones = new ArrayList<>();

        try ( Connection connection = dataSource.getConnection();
              Statement statement = connection.createStatement();
              ResultSet resultSet = statement.executeQuery(SQL) )
        {
            while (resultSet.next())
            {
                Habitacion habitacion = toHabitacion(resultSet);
                habitaciones.add(habitacion);
            }
            return habitaciones;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Habitacion> selectByIds(List<String> ids) throws HotelesAppException
    {
        final String SQL = """
                            SELECT h.id, h.id_hotel, h.numero, h.tipo, h.precio, h.disponible
                                FROM habitaciones h
                             WHERE h.id = ANY(?)
                            """;
        List<Habitacion> habitaciones = new ArrayList<>();

        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL) )
        {
            Array arrIds = connection.createArrayOf("VARCHAR", ids.toArray());
            preparedStatement.setArray(1, arrIds);

            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    habitaciones.add( toHabitacion(resultSet) );
                }
            }
        }
        catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
        return habitaciones;
    }

    @Override
    public Optional<Habitacion> selectById(String id) throws HotelesAppException
    {
        final String SQL = """
                            SELECT h.id, h.id_hotel, h.numero, h.tipo, h.precio, h.disponible
                                FROM habitaciones h
                             WHERE h.id = ?
                            """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                return ( !resultSet.next() )? Optional.empty() : Optional.of( toHabitacion(resultSet) );
            }

        } catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public Habitacion insert(Habitacion habitacion) throws HotelesAppException
    {
        final String SQL = """
                            INSERT INTO habitaciones (id, id_hotel, numero, tipo, precio, disponible)
                            VALUES (nextval('seq_habitaciones'), ?, ?, ?, ?, ?)
                           """;
        final String[] fields = {"id"};

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields)
        )
        {
            preparedStatement.setString(1, habitacion.getId_hotel());
            preparedStatement.setString(2, habitacion.getNumero());
            preparedStatement.setString(3, habitacion.getTipo());
            preparedStatement.setDouble(4, habitacion.getPrecio());
            preparedStatement.setBoolean(5, habitacion.getDisponible());
            preparedStatement.executeUpdate();

            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                resultSet.next();
                String id = resultSet.getString(1);
                return habitacion.withId(id);
            }

        }catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public Optional<Habitacion> update(Habitacion habitacion) throws HotelesAppException
    {
        final String SQL = """
                             UPDATE habitaciones 
                               SET id_hotel = ?,
                                   numero = ?,
                                   tipo = ?,
                                   precio = ?,
                                   disponible = ?
                             WHERE id = ?
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {

            int count = preparedStatement.executeUpdate();
            return (count == 0)? Optional.empty() : Optional.of(habitacion);

        }catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public void deleteById(String id) throws HotelesAppException
    {
        final String SQL = """
                           DELETE 
                             FROM habitaciones
                            WHERE id = ?
                           """;
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if (count == 0) throw new HabitacionNotFoundException();

        } catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public List<Habitacion> findByHotelId(String hotelId) throws HotelesAppException {
        String sql = """
                SELECT id, id_hotel, numero, tipo, precio, disponible
                FROM HABITACIONES
                WHERE ID_HOTEL = ?
                """;

        List<Habitacion> habitaciones = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hotelId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Habitacion habitacion = Habitacion.builder()
                            .withId(rs.getString(1))
                            .withId_hotel(rs.getString(2))
                            .withNumero(rs.getString(3))
                            .withTipo(rs.getString(4))
                            .withPrecio(rs.getDouble(5))
                            .withDisponible(rs.getBoolean(6))
                            .build();

                    habitaciones.add(habitacion);
                }
            }

            return habitaciones;

        } catch (SQLException e) {
            throw new HotelesAppException("Error al buscar habitaciones por hotel ID: " + e.getMessage());
        }
    }



    private Habitacion toHabitacion (ResultSet resultSet) throws SQLException
    {
        return Habitacion.builder()
                         .withId(resultSet.getString(1))
                         .withId_hotel(resultSet.getString(2))
                         .withNumero(resultSet.getString(3))
                         .withTipo(resultSet.getString(4))
                         .withPrecio(resultSet.getDouble(5))
                         .withDisponible(resultSet.getBoolean(6))
                         .build();
    }

    private HotelesAppException toHotelesAppException (SQLException sqlException) {
        String message = sqlException.getMessage();

        if (message.contains("NN_HABITACIONES.ID_HOTEL")) return new FieldRequiredSQLException("id del hotel");
        if (message.contains("NN_HABITACIONES.NUMERO")) return new FieldRequiredSQLException("numero de la habitacion");
        if (message.contains("NN_HABITACIONES.TIPO")) return new FieldRequiredSQLException("tipo de habitacion");
        if (message.contains("NN_HABITACIONES.PRECIO")) return new FieldRequiredSQLException("precio de la habitacion");

        return new SQLHotelesAppException(sqlException);
    }
}
