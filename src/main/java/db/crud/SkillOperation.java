package db.crud;

import db.model.Skill;
import db.util.JdbcConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SkillOperation {
    private static final Logger LOGGER = Logger.getLogger(SkillOperation.class.getName());
    private static final String SELECT_ID = "SELECT * FROM skills WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM skills";
    private static final String INSERT = "INSERT INTO developers(industry, level) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE developers SET industry = ?, level = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM skills WHERE id = ?";

    public Skill selectById(int id) {
        try (Connection connection = JdbcConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID)) {
            assert connection != null;
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Skill skill = createSkill(resultSet);
            resultSet.close();
            return skill;
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public List<Skill> selectAll() {
        try (Connection connection = JdbcConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            assert connection != null;
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            List<Skill> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(createSkill(resultSet));
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

    public void insert(Skill object) {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setString(1, object.getIndustry());
            preparedStatement.setString(2, object.getLevel());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void update(Skill object) {
        try (Connection connection = JdbcConnectionUtil.getConnection()) {
            assert connection != null;
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, object.getIndustry());
            preparedStatement.setString(2, object.getLevel());
            preparedStatement.setInt(3, object.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private Skill createSkill(ResultSet resultSet) throws SQLException {
        return new Skill(resultSet.getInt("id"),
                resultSet.getString("industry"),
                resultSet.getString("level"));
    }
}
