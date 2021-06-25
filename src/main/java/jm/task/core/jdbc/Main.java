package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserService user = new UserServiceImpl();

        user.createUsersTable();

        user.saveUser("Stas", "Matveev", (byte) 28);
        user.saveUser("Ivan", "Lobanov", (byte) 28);
        user.saveUser("Kristina", "Krulova", (byte) 19);
        user.saveUser("Vanel", "Bokal", (byte) 33);

        System.out.println(user.getAllUsers());

        user.cleanUsersTable();

        user.dropUsersTable();
    }
}
