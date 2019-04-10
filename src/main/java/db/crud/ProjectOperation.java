package db.crud;

import db.model.Project;
import db.util.JdbcConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectOperation {
    private static final Logger LOGGER = Logger.getLogger(ProjectOperation.class.getName());
    private static final String SELECT_ID = "SELECT * FROM projects WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM projects";
    private static final String INSERT = "INSERT INTO projects(name, cost, date) VALUES(?, ?, ?))";
    private static final String UPDATE = "UPDATE projects SET name = ?, cost = ? date = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?";

    public Project selectById(int id) {
        try (Connection connection = JdbcConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            assert connection != null;
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Project project = createProject(resultSet);
            resultSet.close();
            return project;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<Project> selectAll() {
        try (Connection connection = JdbcConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            assert connection != null;
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            List<Project> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createProject(resultSet));
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

    public void insert(Project object) {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setDouble(2, object.getCost());
            preparedStatement.setDate(3, object.getDate());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void update(Project object) {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, object.getName());
            preparedStatement.setDouble(2, object.getCost());
            preparedStatement.setDate(3, object.getDate());
            preparedStatement.setInt(4, object.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Project createProject(ResultSet resultSet) throws SQLException {
        return new Project(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDouble("cost"),
                resultSet.getDate("date"));
    }
}
