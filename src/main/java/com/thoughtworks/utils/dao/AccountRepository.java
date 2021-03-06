package com.thoughtworks.utils.dao;

import com.thoughtworks.exceptions.FieldIllegalException;
import com.thoughtworks.objects.Account;
import com.thoughtworks.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository {
    private static Connection connection;

    static {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // TODO::Question JDBC作业中是成员方法，但是我感觉写成类方法会不会更合适一点? ↲
    // 这个类就是我目前理解的DAO层，让他成为一个工具类，调用静态方法使用会不会更合适一点？
    public static void save(Account account) {
        String sql = "INSERT INTO account(user_name, phone_number, email, password)" +
            "VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getUserName());
            preparedStatement.setString(2, account.getPhoneNumber());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Account queryByUserName(String userName) {
        String sql = "SELECT id, user_name, phone_number, email, password " +
            "FROM account " +
            "WHERE user_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            // TODO::Question 如果明知道resultSet只有一行数据，可以不用while(rs.next())吗？
            if (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUserName(resultSet.getString("user_name"));
                account.setPhoneNumber(resultSet.getString("phone_number"));
                account.setEmail(resultSet.getString("email"));
                account.setPassword(resultSet.getString("password"));
                return account;
            }
        } catch (SQLException | FieldIllegalException e) {
            e.printStackTrace();
        }
        return null;
    }
}
