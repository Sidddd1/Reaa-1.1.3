package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/1.1.3?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "xpnkcv5rt12";
    private static Connection connection;

    public Connection getConnection() {

        if (connection == null) {
            try {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Соединение с БД не установлено");
                e.printStackTrace();
            }

        }
        return connection;
    }
}