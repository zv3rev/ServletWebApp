package repository;

import entity.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ProfileRepository {
    public static final String SELECT_BY_ID_QUERY = "SELECT id, username, password, email, age FROM Profile WHERE id = ?";
    public static final String INSERT_QUERY = "INSERT INTO profile (username, password, email, age) VALUES (?,?,?,?)";
    public static final String DELETE_QUERY = "DELETE FROM profile WHERE id = ?";
    public static final String UPDATE_BY_ID_QUERY = "UPDATE profile SET username = ?, password = ?, email = ?, age =? WHERE id = ?";
    public static final String SELECT_PASSWORD_BY_USERNAME = "SELECT id, username, password, email, age FROM profile WHERE username = ?;";


    public Profile get(Long id) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Profile profile = new Profile();
                profile.setId(resultSet.getLong("id"));
                profile.setUsername(resultSet.getString("username"));
                profile.setPassword(resultSet.getString("password"));
                profile.setEmail(resultSet.getString("email"));
                profile.setAge(resultSet.getByte("age"));
                return profile;
            }
            return null;
        }
    }

    public Profile getByUsername(String username) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_PASSWORD_BY_USERNAME);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapToProfile(resultSet);
            }
            return null;
        }
    }

    private Profile mapToProfile(ResultSet rs) throws SQLException {
        Profile result = new Profile(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("email"),
                rs.getByte("age")
        );
        result.setId(rs.getLong("id"));
        return result;
    }

    public boolean insert(Profile profile) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, profile.getUsername());
            statement.setString(2, profile.getPassword());
            statement.setString(3, profile.getEmail());
            statement.setByte(4, profile.getAge());
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }
    }

    public void update(Profile profile) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_BY_ID_QUERY);
            statement.setString(1, profile.getUsername());
            statement.setString(2, profile.getPassword());
            statement.setString(3, profile.getEmail());
            statement.setByte(4, profile.getAge());
            statement.setLong(5, profile.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }


}
