package db.crud;

import db.model.Company;
import db.util.JdbcConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyOperation {
    private static final Logger LOGGER = Logger.getLogger(CompanyOperation.class.getName());
    private static final String SELECT_ID = "SELECT * FROM companies WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM companies";
    private static final String INSERT = "INSERT INTO companies(name, address) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE companies SET name = ?, address = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM companies WHERE id = ?";

    public Company selectById(int id) {
        try (Connection connection = JdbcConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            assert connection != null;
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Company company = createCompany(resultSet);
            resultSet.close();
            return company;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<Company> selectAll() {
        try (Connection connection = JdbcConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            assert connection != null;
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            List<Company> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createCompany(resultSet));
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

    public void insert(Company object) {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAddress());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void update(Company object) {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getAddress());
            preparedStatement.setInt(3, object.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Company createCompany(ResultSet resultSet) throws SQLException {
        return new Company(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("address"));
    }
}
