package com.thoughtworks;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try {
            // TODO change absPath to classLoader to load jdbc settings
//            Properties properties = new Properties();
//            ClassLoader classLoader = JDBCUtils.class.getClassLoader();
//            URL resource = classLoader.getResource("jdbcsettings.properties");
//            properties.load(new FileReader(resource.getPath()));
            Properties properties = new Properties();
            properties.load(new FileReader("/home/edward/Documents/ThoughtWorks/mysql-homeworks/C2/P6-大作业/java_practice_basic-accounts_manager/src/resources/jdbcsettings.properties"));

            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            driver = properties.getProperty("driver");
            Class.forName(driver);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void close(Statement statement, ResultSet resultSet) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
