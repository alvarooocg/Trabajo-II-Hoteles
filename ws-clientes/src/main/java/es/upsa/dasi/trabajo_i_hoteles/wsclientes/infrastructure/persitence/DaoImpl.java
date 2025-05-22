package es.upsa.dasi.trabajo_i_hoteles.wsclientes.infrastructure.persitence;

import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.FieldRequiredSQLException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.SQLHotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.adapters.output.persitence.Dao;
import es.upsa.dasi.trabajo_i_hoteles.domain.entities.Cliente;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.HotelesAppException;
import es.upsa.dasi.trabajo_i_hoteles.domain.exceptions.ClienteNotFoundException;
import es.upsa.dasi.trabajo_i_hoteles.wsclientes.domain.exceptions.ClienteHasReservasSQLException;
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
    public List<Cliente> selectAll() throws HotelesAppException
    {
        final String SQL = """
                            SELECT c.id, c.nombre, c.email, c.telefono
                                FROM clientes c
                            """;

        List<Cliente> clientes = new ArrayList<>();

        try ( Connection connection = dataSource.getConnection();
              Statement statement = connection.createStatement();
              ResultSet resultSet = statement.executeQuery(SQL) )
        {
            while ( resultSet.next() )
            {
                Cliente cliente = toCliente(resultSet);
                clientes.add(cliente);
            }
            return clientes;
        }
        catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public List<Cliente> selectByIds(List<String> ids) throws HotelesAppException
    {
        final String SQL = """
                            SELECT c.id, c.nombre, c.email, c.telefono
                            FROM clientes c
                             WHERE c.id = ANY(?)
                            """;
        List<Cliente> clientes = new ArrayList<>();

        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL) )
        {
            Array arrIds = connection.createArrayOf("VARCHAR", ids.toArray());
            preparedStatement.setArray(1, arrIds);

            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                while (resultSet.next())
                {
                   clientes.add( toCliente(resultSet) );
                }
            }
        }
        catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> selectById(String id) throws HotelesAppException
    {
        final String SQL = """
                            SELECT c.id, c.nombre, c.email, c.telefono
                                FROM clientes c
                             WHERE c.id = ?
                            """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery())
            {
                return ( !resultSet.next() )? Optional.empty() : Optional.of( toCliente(resultSet) );
            }

        } catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public Cliente insert(Cliente cliente) throws HotelesAppException
    {
        final String SQL = """
                            INSERT INTO clientes (id, nombre, email, telefono)
                            VALUES (nextval('seq_clientes'), ?, ?, ?)
                           """;
        final String[] fields = {"id"};

        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL, fields)
        )
        {
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getTelefono());
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys())
            {
                resultSet.next();
                String id = resultSet.getString(1);
                return cliente.withId(id);
            }

        }catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);
        }
    }

    @Override
    public Optional<Cliente> update(Cliente cliente) throws HotelesAppException
    {
        final String SQL = """
                             UPDATE clientes 
                             SET nombre = ?, 
                                 email = ?, 
                                 telefono = ?
                             WHERE id = ?
                           """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getTelefono());
            preparedStatement.setString(4, cliente.getId());
            int count = preparedStatement.executeUpdate();
            return (count == 0)? Optional.empty() : Optional.of(cliente);

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
                             FROM clientes
                            WHERE id = ?
                           """;
        try ( Connection connection = dataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SQL)
        )
        {
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if (count == 0) throw new ClienteNotFoundException();

        } catch (SQLException sqlException)
        {
            throw toHotelesAppException(sqlException);

        }
    }

    private HotelesAppException toHotelesAppException (SQLException sqlException)
    {
        String message = sqlException.getMessage();

        if (message.contains("NN_CLIENTES.NOMBRE")) return new FieldRequiredSQLException("nombre");
        if (message.contains("NN_CLIENTES.EMAIL")) return new FieldRequiredSQLException("email");
        if (message.contains("NN_CLIENTES.TELEFONO")) return new FieldRequiredSQLException("telefono");
        if (message.contains("FK_RESERVAS_CLIENTES")) return new ClienteHasReservasSQLException();

        return new SQLHotelesAppException(sqlException);
    }

    private Cliente toCliente (ResultSet resultSet) throws SQLException
    {
        return Cliente.builder()
                .withId(resultSet.getString(1))
                .withNombre(resultSet.getString(2))
                .withEmail(resultSet.getString(3))
                .withTelefono(resultSet.getString(4))
                .build();
    }
}
