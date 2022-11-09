package db;

import org.postgresql.Driver;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DbHelper<T> {

    private final static String jdbcURL = "jdbc:postgresql://surus.db.elephantsql.com:5432/wtwmsyke?currentSchema=slaughter_house";

    private final static String username = "wtwmsyke";      //Indsæt dine datebase loginoplysninger her Ole :)

    private final static String password = "rV40CIlTHBJQ2PnZ4NTiILx1gb1M5tp4";

    public DbHelper() {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RuntimeException("No JDBC driver", e);
        }
    }
    protected Connection getConnection() throws SQLException {
        if (username == null) {
            return DriverManager.getConnection(jdbcURL);
        } else {
            return DriverManager.getConnection(jdbcURL, username, password);
        }
    }

    private PreparedStatement prepare(Connection connection, String sql, Object[] parameters) throws SQLException {
        PreparedStatement stat = connection.prepareStatement(sql);
        for(int i = 0; i < parameters.length; i++) {
            stat.setObject(i + 1, parameters[i]);
        }
        return stat;
    }

    public int executeUpdate(String sql, Object... parameters){
        try (Connection connection = getConnection()) {
            PreparedStatement stat = prepare(connection, sql, parameters);
            return stat.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public T mapSingle(DataMapper<T> mapper, String sql, Object... parameters) {
        try (Connection connection = getConnection()) {
            PreparedStatement stat = prepare(connection, sql, parameters);
            ResultSet rs = stat.executeQuery();
            if(rs.next()) {
                return mapper.create(rs);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public List<T> map(DataMapper<T> mapper, String sql, Object... parameters) {
        try (Connection connection = getConnection()) {
            PreparedStatement stat = prepare(connection, sql, parameters);
            ResultSet rs = stat.executeQuery();
            LinkedList<T> allAnimals = new LinkedList<>();
            while(rs.next()) {
                allAnimals.add(mapper.create(rs));
            }
            return allAnimals;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
