package com.thoughtworks;

import com.thoughtworks.exceptions.registerexcps.PasswordIllegalException;
import com.thoughtworks.exceptions.registerexcps.PhoneNumberIllegalException;
import com.thoughtworks.exceptions.registerexcps.UserNameIllegalException;
import com.thoughtworks.exceptions.userInputFormatException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LogIn {
    private static String userInput;
    private static String userName;
    private static String password;
    private static Connection connection;

    static {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void parseUserInput(String userInput) throws userInputFormatException {
        String[] userRawInfo = userInput.split(",");
        if (userRawInfo.length == 2) {
            this.userInput = userInput;
            userName = userRawInfo[0];
            password = userRawInfo[1];
        } else {
            throw new userInputFormatException();
        }
    }

    public Account logIn() {
        String sql = "SELECT id, user_name, phone_number, email, password" +
            "FROM account" +
            "WHERE user_name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            String truePassword = resultSet.getString("password");
            if (password.equals(truePassword)) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUserName(resultSet.getString("user_name"));
                account.setPhoneNumber(resultSet.getString("phone_number"));
                account.setPassword(resultSet.getString("password"));
                return account;
            }
        } catch (SQLException | UserNameIllegalException | PhoneNumberIllegalException | PasswordIllegalException e) {
            e.printStackTrace();
        }
        return null; //TODO 写在try语句中的return不能算作本方法已经有return 语句了吗？
    }
}
