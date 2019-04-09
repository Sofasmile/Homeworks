package db.crud;

import db.model.Developer;
import db.util.CloseConnection;
import db.util.JdbcConnectionUtil;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperOperation {
    private String pathToTheQuery = "src/main/resources/queries/";
    private static final Logger LOGGER = Logger.getLogger(JdbcConnectionUtil.class.getName());
    private static final String SELECT_ID = "SELECT * FROM developers WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM developers";
    private static final String INSERT = "INSERT INTO developers(name, age, gender, salary) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE developers SET name = ?, age = ?, gender = ?, salary = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM developers WHERE id = ?";

    public Developer selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Developer developer = createDeveloper(resultSet);

        CloseConnection.close(resultSet, connection, preparedStatement);
        return developer;
    }

    public List<Developer> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Developer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createDeveloper(resultSet));
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

    public void insert(Developer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setString(3, object.getGender());
        preparedStatement.setDouble(4, object.getSalary());

        CloseConnection.close(connection, preparedStatement);
    }

    public void update(Developer object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setInt(2, object.getAge());
        preparedStatement.setString(3, object.getGender());
        preparedStatement.setDouble(4, object.getSalary());
        preparedStatement.setInt(5, object.getId());

        CloseConnection.close(connection, preparedStatement);
    }

    public List<Developer> getlistOfDevelopersByProject(String projectName) throws SQLException {
        return selectAllByCondition(projectName, pathToTheQuery + "listOfDevelopersByProject.sql");
    }

    public List<Developer> getListOfDevelopersBySkillLevel(String skillLevel) throws SQLException {
        return selectAllByCondition(skillLevel, pathToTheQuery + "listOfMiddleDevelopers.sql");
    }

    public List<Developer> getListOfDevelopersByIndustry(String branchDevelopment) throws SQLException {
        return selectAllByCondition(branchDevelopment, pathToTheQuery + "listOfJavaDevelopers.sql");
    }

    public double getSalaryOfDevelopersByProject(String projectName) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        String sql = null;
        try {
            sql = new Scanner(new File(pathToTheQuery + "salaryOfDevelopersBySeparateProject.sql"))
                    .useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, projectName);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        double salary = resultSet.getDouble(1);

        CloseConnection.close(resultSet, connection, preparedStatement);
        return salary;
    }

    private List<Developer> selectAllByCondition(String conditionalField, String pathToSql) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        String sql = null;
        try {
            sql = new Scanner(new File(pathToSql)).useDelimiter("\\A").next();
        } catch (FileNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, conditionalField);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Developer> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createDeveloper(resultSet));
        }

        CloseConnection.close(resultSet, connection, preparedStatement);
        return result;
    }

    private Developer createDeveloper(ResultSet resultSet) throws SQLException {
        return new Developer(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("age"),
                resultSet.getString("gender"),
                resultSet.getDouble("salary"));
    }
}
