package db.util;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class CloseConnection {
    public static void close(Connection connection, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
        connection.commit();
        preparedStatement.close();
        connection.close();
    }

    public static void close(ResultSet resultSet, Connection connection, PreparedStatement preparedStatement) throws SQLException {
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }

    public static void close(ResultSet resultSet, Connection connection, Statement statement) throws SQLException {
        resultSet.close();
        statement.close();
        connection.close();
    }
}
