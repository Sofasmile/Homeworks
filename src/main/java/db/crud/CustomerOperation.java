package db.crud;

import db.model.Customer;
import db.util.JdbcConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerOperation {
    private static final Logger LOGGER = Logger.getLogger(CustomerOperation.class.getName());
    private static final String SELECT_ID = "SELECT * FROM customers WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM customers";
    private static final String INSERT = "INSERT INTO customers(name, age) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE customers SET name = ?, age = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM customers WHERE id = ?";

    public Customer selectById(int id) {
        try (Connection connection = JdbcConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            assert connection != null;
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Customer customer = createCustomer(resultSet);
            resultSet.close();
            return customer;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<Customer> selectAll() {
        try (Connection connection = JdbcConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            assert connection != null;
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            List<Customer> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createCustomer(resultSet));
            }
            resultSet.close();
            return result;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public void deleteById(int id) {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void insert(Customer object){
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getAge());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void update(Customer object) {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setInt(2, object.getAge());
            preparedStatement.setInt(3, object.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Customer createCustomer(ResultSet resultSet) throws SQLException {
        return new Customer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"));
    }
}
