package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static final String CREATE_TBL = "CREATE TABLE IF NOT EXISTS Users " +
            "(id BIGSERIAL PRIMARY KEY, name TEXT, lastName TEXT, age INTEGER)";

    private static final String DROP_US_TBL = "DROP TABLE IF EXISTS Users";
    private static final String SAVE_US = "INSERT INTO Users (name, lastName, age) VALUES" + "(?, ?, ?)";
    private static final String REMOVE_US_BY_ID = "DELETE FROM Users WHERE id = ?";
    private static final String GET_ALL_US = "SELECT * FROM User";
    private static final String CLEAN_US_TBL = "DELETE FROM Users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Connection connection = Util.getconn()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CREATE_TBL);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void dropUsersTable() {

        try (Connection connection = Util.getconn()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(DROP_US_TBL);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getconn()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_US);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + "с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public void removeUserById(long id) {

        try (Connection connection = Util.getconn()) {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_US_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();

        try (Connection connection = Util.getconn()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_US);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
        return users;
    }

    public void cleanUsersTable() {

        try (Connection connection = Util.getconn()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(CLEAN_US_TBL);
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
