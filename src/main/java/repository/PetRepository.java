package repository;

import entity.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetRepository {
    public static final String SELECT_BY_ID_QUERY = "SELECT id, nickname, birthday, type, owner_id FROM pet WHERE id = ?";
    public static final String SELECT_BY_OWNER_ID_QUERY = "SELECT id, nickname, birthday, type, owner_id FROM pet WHERE owner_id = ?";
    public static final String INSERT_QUERY = "INSERT INTO pet (nickname, birthday, type, owner_id) VALUES (?,?,?,?);";
    public static final String UPDATE_QUERY = "UPDATE pet SET nickname = ?, birthday = ?, type = ? WHERE id = ?";


    public Pet get(Long id) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getPet(resultSet);
            }
            return null;
        }
    }

    public List<Pet> getByOwnerId(Long ownerId) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_OWNER_ID_QUERY);
            statement.setLong(1,ownerId);
            ResultSet resultSet = statement.executeQuery();
            List<Pet> result = new ArrayList<Pet>();
            while (resultSet.next()){
                result.add(getPet(resultSet));
            }
            return result;
        }
    }

    private Pet getPet(ResultSet resultSet) throws SQLException {
        Pet pet = new Pet();
        pet.setId(resultSet.getLong("id"));
        pet.setNickname(resultSet.getString("nickname"));
        pet.setBirthday(resultSet.getDate("birthday"));
        pet.setType(resultSet.getString("type"));
        pet.setOwner_id(resultSet.getLong("owner_id"));
        return pet;
    }

    public void insert(Pet pet) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, pet.getNickname());
            statement.setDate(2, pet.getBirthday());
            statement.setString(3, pet.getType());
            statement.setLong(4, pet.getOwnerId());
            statement.executeUpdate();
        }
    }

    public void update(Pet pet) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            statement.setString(1, pet.getNickname());
            statement.setDate(2, pet.getBirthday());
            statement.setString(3, pet.getType());
            statement.setLong(4,pet.getId());
            statement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM pet WHERE id =?");
            statement.setLong(1,id);
            statement.executeUpdate();
        }
    }

}
