package db.crud;

import db.model.Company;
import db.util.CloseConnection;
import db.util.JdbcConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyOperation {
    private static final String SELECT_ID = "SELECT * FROM companies WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM companies";
    private static final String INSERT = "INSERT INTO companies(name, address) VALUES(?, ?)";
    private static final String UPDATE = "UPDATE companies SET name = ?, address = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM companies WHERE id = ?";

    public Company selectById(int id) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ID);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Company company = createCompany(resultSet);
        CloseConnection.close(resultSet, connection, preparedStatement);
        return company;
    }

    public List<Company> selectAll() throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECT_ALL);
        List<Company> result = new ArrayList<>();
        while (resultSet.next()){
            result.add(createCompany(resultSet));
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

    public void insert(Company object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getAddress());

        CloseConnection.close(connection, preparedStatement);
    }

    public void update(Company object) throws SQLException {
        Connection connection = JdbcConnectionUtil.getConnection();
        assert connection != null;
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, object.getName());
        preparedStatement.setString(2, object.getAddress());
        preparedStatement.setInt(3, object.getId());

        CloseConnection.close(connection, preparedStatement);
    }

    private Company createCompany(ResultSet resultSet) throws SQLException {
        return new Company(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("address"));
    }
}
