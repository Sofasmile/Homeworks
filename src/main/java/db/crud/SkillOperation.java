package db.crud;

import db.model.Skill;
import db.util.CloseConnection;
import db.util.JdbcConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillOperation {
    private static final String SELECT_ID = "SELECT * FROM skills WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM skills";
    private static final String INSERT = "INSERT INTO developers(industry, level) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE developers SET industry = ?, level = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM skills WHERE id = ?";

    public Skill selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Skill skill = createSkill(resultSet);

        CloseConnection.close(resultSet, connection, preparedStatement);
        return skill;
    }

    public List<Skill> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Skill> result = new ArrayList<>();
        while (resultSet.next()) {
            result.add(createSkill(resultSet));
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

    public void insert(Skill object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, object.getIndustry());
        preparedStatement.setString(2, object.getLevel());

        CloseConnection.close(connection, preparedStatement);
    }

    public void update(Skill object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, object.getIndustry());
        preparedStatement.setString(2, object.getLevel());
        preparedStatement.setInt(3, object.getId());

        CloseConnection.close(connection, preparedStatement);
    }

    private Skill createSkill(ResultSet resultSet) throws SQLException {
        return new Skill(resultSet.getInt("id"),
                resultSet.getString("industry"),
                resultSet.getString("level"));

    }
}
