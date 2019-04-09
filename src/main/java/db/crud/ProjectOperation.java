package db.crud;

import db.model.Project;
import db.util.CloseConnection;
import db.util.JdbcConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectOperation {
    private static final String SELECT_ID = "SELECT * FROM projects WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM projects";
    private static final String INSERT = "INSERT INTO projects(name, cost, date) VALUES(?, ?, ?))";
    private static final String UPDATE = "UPDATE projects SET name = ?, cost = ? date = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM projects WHERE id = ?";

    public Project selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Project project = createProject(resultSet);

        CloseConnection.close(resultSet, connection, preparedStatement);
        return project;
    }

    public List<Project> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Project> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createProject(resultSet));
        }

        CloseConnection.close(resultSet, connection, statement);
        return result;
    }

    public void deleteById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setInt(1, id);

        CloseConnection.close(connection, preparedStatement);
    }

    public void insert(Project object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setDouble(2, object.getCost());
        preparedStatement.setDate(3, object.getDate());

        CloseConnection.close(connection, preparedStatement);
    }

    public void update(Project object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setDouble(2, object.getCost());
        preparedStatement.setDate(3, object.getDate());
        preparedStatement.setInt(4, object.getId());

        CloseConnection.close(connection, preparedStatement);
    }

    private Project createProject(ResultSet resultSet) throws SQLException {
        return new Project(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getDouble("cost"),
                resultSet.getDate("date"));
    }
}
