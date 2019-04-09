package db.crud;

import db.model.Customer;
import db.util.CloseConnection;
import db.util.JdbcConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerOperation {
    private static final String SELECT_ID = "SELECT * FROM companies WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM companies";
    private static final String INSERT = "INSERT INTO companies(name, address) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE companies SET name = ?, address = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM companies WHERE id = ?";

    public Customer selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM customers WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Customer customer = createCustomer(resultSet);

        CloseConnection.close(resultSet, connection, preparedStatement);
        return customer;
    }

    public List<Customer> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        String sql = "SELECT * FROM customers";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Customer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createCustomer(resultSet));
        }

        CloseConnection.close(resultSet, connection, statement);
        return result;
    }

    public void deleteById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "DELETE FROM customers WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        CloseConnection.close(connection, preparedStatement);
    }

    public void insert(Customer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "INSERT INTO customers(name, age) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());

        CloseConnection.close(connection, preparedStatement);
    }

    public void update(Customer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        String sql = "UPDATE customers SET name = ?, age = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setInt(3, object.getId());

        CloseConnection.close(connection, preparedStatement);
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"));
    }
}
