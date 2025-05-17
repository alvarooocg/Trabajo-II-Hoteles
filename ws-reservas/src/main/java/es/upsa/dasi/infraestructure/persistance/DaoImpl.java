package es.upsa.dasi.infraestructure.persistance;

import es.upsa.dasi.adapters.output.persistance.Dao;


import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Reserva;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.*;
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
    public List<Reserva> findHoteles() throws HotelesAppException
    {
        final String SQL = """
                SELECT id, id_cliente, id_habitacion, fecha_entrada, fecha_salida
                FROM Reservas
                """;

        List<Reserva> reservas = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SQL))
        {
            while (resultSet.next())
            {
                reservas.add(toReserva(resultSet));
            }
            return reservas;
        } catch (SQLException e) {
            throw toReservasAppException(e);
        }
    }

    @Override
    public List<Reserva> findReservasByIds(List<String> ids) throws HotelesAppException {
        final String sql = """
                SELECT r.id, r.id_cliente, r.id_habitacion, r.fecha_entrada, r.fecha_salida
                FROM Reservas r
                WHERE r.id = ANY(?)
                """;
        List<Reserva> reservas = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            Array arrayIds = connection.createArrayOf("VARCHAR", ids.toArray());
            preparedStatement.setArray(1, arrayIds);

            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                    reservas.add(toReserva(resultSet));
                }
            }

        } catch (SQLException e) {
            throw toReservasAppException(e);
        }
        return reservas;
    }

    @Override
    public Optional<Reserva> findReservaById(String id) throws HotelesAppException
    {
        final String SQL = """
                SELECT r.id, r.id_cliente, r.id_habitacion, fecha_entrada, r.fecha_salida
                FROM Reservas r
                WHERE r.id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL))
        {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                return (!resultSet.next()) ? Optional.empty() : Optional.of(toReserva(resultSet));
            }

        } catch (SQLException e) {
            throw toReservasAppException(e);
        }
    }

    @Override
    public Reserva insertReserva(Reserva reserva) throws HotelesAppException
    {
        final String SQL = """
                INSERT INTO Reservas(id,            id_cliente, id_habitacion, fecha_entrada, fecha_salida)
                VALUES (nextval     ('seq_reservas'), ?,            ?,              ?,              ?)
                """;

        final String[] fields = {"id"};

        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields))
        {
            preparedStatement.setString(1, reserva.getId_cliente());
            preparedStatement.setString(2, reserva.getId_habitacion());
            preparedStatement.setDate(3, Date.valueOf(reserva.getFecha_entrada()));
            preparedStatement.setDate(4, Date.valueOf(reserva.getFecha_salida()));
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                resultSet.next();
                String id = resultSet.getString(1);
                return reserva.withId(id);
            }
        } catch (SQLException e) {
            throw toReservasAppException(e);
        }
    }

    @Override
    public Optional<Reserva> updateReserva(Reserva reserva) throws HotelesAppException
    {
        final String SQL = """
                UPDATE Reservas
                SET    id_cliente = ?,
                       id_habitacion = ?,
                       fecha_entrada = ?,
                       fecha_salida = ?
                WHERE id = ?
                """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL))
        {
            preparedStatement.setString(1, reserva.getId_cliente());
            preparedStatement.setString(2, reserva.getId_habitacion());
            preparedStatement.setDate(3, Date.valueOf(reserva.getFecha_entrada()));
            preparedStatement.setDate(4, Date.valueOf(reserva.getFecha_salida()));

            int count = preparedStatement.executeUpdate();
            return (count == 0) ? Optional.empty() : Optional.of(reserva);

        } catch (SQLException e) {
            throw toReservasAppException(e);
        }

    }

    @Override
    public void deleteReservaById(String id) throws HotelesAppException
    {
        final String SQL = """
                           DELETE 
                           FROM reservas
                           WHERE id = ?  
                           """;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL))
        {
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if (count == 0) throw new ReservaNotFoundException();

        } catch (SQLException e)
        {
            throw toReservasAppException(e);
        }
    }


    private Reserva toReserva(ResultSet resultSet) throws SQLException
    {
        return Reserva.builder()
                .withId(resultSet.getString(1))
                .withId_cliente(resultSet.getString(2))
                .withId_habitacion(resultSet.getString(3))
                .withFecha_entrada(resultSet.getDate(4).toLocalDate())
                .withFecha_salida(resultSet.getDate(5).toLocalDate())
                .build();
    }

    private HotelesAppException toReservasAppException(SQLException e)
    {
        String message = e.getMessage();

        if (message.contains("NN_RESERVAS.ID_CLIENTE"))return new HotelesAppException("Debe contener ID de cliente");
        else if (message.contains("NN_RESERVAS.ID_HABITACION")) return new HotelesAppException("Debe contener ID de habitacion");
        else if (message.contains("NN_RESERVAS.FECHA_ENTRADA")) return new HotelesAppException("Debe contener fecha entrada");
        else if (message.contains("NN_RESERVAS.FECHA_SALIDA")) return new HotelesAppException("Debe contener fecha salida");


        return new SQLHotelesAppException(e);
    }
}
