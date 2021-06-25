package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static Util util = Util.getInstance();

    @Override
    public void createUsersTable() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("create table if not exists Users (" +
                    "ID bigint primary key auto_increment," +
                    "Name varchar(15) not null," +
                    "LastName varchar(15)," +
                    "Age tinyint)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("drop table if exists Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into Users (Name, LastName, Age) values (?, ?, ?)";
        try (Connection connection = util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "delete from Users where ID = ?";
        try (Connection connection = util.getConnection()) {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
            preparedStatement1.setLong(1, id);
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Users");
            while (resultSet.next()) {
                User tUser = new User();
                tUser.setId(resultSet.getLong("ID"));
                tUser.setName(resultSet.getString("Name"));
                tUser.setLastName(resultSet.getString("LastName"));
                tUser.setAge(resultSet.getByte("Age"));
                list.add(tUser);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = util.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("truncate table Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

