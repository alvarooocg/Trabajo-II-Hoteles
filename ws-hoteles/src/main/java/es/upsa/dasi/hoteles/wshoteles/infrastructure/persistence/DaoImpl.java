package es.upsa.dasi.hoteles.wshoteles.infrastructure.persistence;

import es.upsa.dasi.hoteles.wshoteles.adapters.output.persistence.Dao;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Hotel;

import javax.sql.DataSource;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class DaoImpl implements Dao {
    @Inject
    DataSource dataSource;

    @Override
    public List<Hotel> findHoteles() throws HotelesAppException {
        final String SQL = """
                           SELECT h.id, h.nombre, h.ciudad, h.estrellas, h.descripcion
                           FROM hoteles h
                           """;
        List<Hotel> hoteles = new ArrayList<>();

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL)
        ) {
            while (resultSet.next()) {
                Hotel hotel = toHotel(resultSet);
                hoteles.add(hotel);
            }
            return hoteles;
        } catch (SQLException sqlException) {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public Optional<Hotel> findHotelById(String id) throws HotelesAppException {
        final String SQL = """
                           SELECT h.id, h.nombre, h.ciudad, h.estrellas, h.descripcion
                           FROM hoteles h
                           WHERE h.id = ?
                           """;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL))
        {
            preparedStatement.setString(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                return (!resultSet.next()) ? Optional.empty() : Optional.of(toHotel(resultSet));
            }
        } catch (SQLException sqlException) {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public List<Hotel> findHotelesByIds(List<String> ids) throws HotelesAppException {
        final String SQL = """
                           SELECT h.id, h.nombre, h.ciudad, h.estrellas, h.descripcion
                           FROM hoteles h
                           WHERE h.id = ANY(?)
                           """;
        List<Hotel> hoteles = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL))
        {
            Array arrayIds = connection.createArrayOf("VARCHAR", ids.toArray());
            preparedStatement.setArray(1, arrayIds);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    hoteles.add(toHotel(resultSet));
                }
            }
        } catch (SQLException sqlException) {
            throw toHotelesAppException(sqlException);
        }
        return hoteles;
    }

    @Override
    public Hotel insertHotel(Hotel hotel) throws HotelesAppException {
        final String SQL = """
                           INSERT INTO hoteles(id,                     nombre, ciudad, estrellas, descripcion)
                                       VALUES (nextval('seq-hoteles'),      ?,      ?,         ?,           ?)
                           """;
        final String[] fields = {"id"};
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields)) {
            preparedStatement.setString(1, hotel.getNombre());
            preparedStatement.setString(2, hotel.getCiudad());
            preparedStatement.setInt(3, hotel.getEstrellas());
            preparedStatement.setString(4, hotel.getDescripcion());
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                resultSet.next();
                String id = resultSet.getString(1);
                return hotel.withId(id);
            }
        }catch (SQLException sqlException) {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public Optional<Hotel> updateHotel(Hotel hotel) throws HotelesAppException {
        final String SQL = """
                           UPDATE hoteles
                              SET nombre = ?,
                              ciudad = ?,
                              estrellas = ?,
                              descripcion = ?
                           WHERE id = ?
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL))
        {
            preparedStatement.setString(1, hotel.getNombre());
            preparedStatement.setString(2, hotel.getCiudad());
            preparedStatement.setInt(3, hotel.getEstrellas());
            preparedStatement.setString(4, hotel.getDescripcion());
            int count = preparedStatement.executeUpdate();
            return (count == 0) ? Optional.empty() : Optional.of(hotel);
        } catch (SQLException sqlException) {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public void deleteHotelById(String id) throws HotelesAppException {
        final String SQL = """
                           DELETE 
                             FROM hoteles
                           WHERE id = ?  
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if (count == 0) throw new HotelNotFoundAppException();
        } catch (SQLException sqlException) {
            throw toHotelesAppException(sqlException);
        }
    }

    private Hotel toHotel(ResultSet resultSet) throws SQLException {
        return Hotel.builder()
                    .withId(resultSet.getString(1))
                    .withNombre(resultSet.getString(2))
                    .withCiudad(resultSet.getString(3))
                    .withEstrellas(resultSet.getInt(4))
                    .withDescripcion(resultSet.getString(5))
                    .build();
    }

    private HotelesAppException toHotelesAppException(SQLException sqlException) {
        String message = sqlException.getMessage();
        if (message.contains("NN_HOTELES.NOMBRE")) return new FieldRequiredSQLException("nombre");
        else if(message.contains("NN_HOTELES.CIUDAD")) return new FieldRequiredSQLException("ciudad");
        else if(message.contains("NN_HOTELES.ESTRELLAS")) return new FieldRequiredSQLException("estrellas");
        else if(message.contains("CH_HOTELES.ESTRELLAS")) return new FieldBetweenSQLException("estrellas", "0", "5");

        return new SQLHotelesAppException(sqlException);
    }
}
