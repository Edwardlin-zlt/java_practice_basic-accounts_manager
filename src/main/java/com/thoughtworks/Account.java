package com.thoughtworks;

import com.thoughtworks.exceptions.EmailAddressIllegalException;
import com.thoughtworks.exceptions.PasswordIllegalException;
import com.thoughtworks.exceptions.PhoneNumberIllegalException;
import com.thoughtworks.exceptions.UserNameIllegalException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;

public class Account {
    private static int id;
    private static String userName;
    private static String phoneNumber;
    private static String email;
    private static String password;
    private static Connection connection;

    static {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) throws UserNameIllegalException {
        if (userName.length() >= 2 && userName.length() <= 10) {
            this.userName = userName;
        } else {
            throw new UserNameIllegalException("用户名不合法");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws PhoneNumberIllegalException {
        if (phoneNumber.startsWith("1") && phoneNumber.length() == 11) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new PhoneNumberIllegalException("电话号码设置不合法");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailAddressIllegalException {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new EmailAddressIllegalException("邮箱设置不合法");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordIllegalException {
        // TODO 使用一个正则表达式 check password
        if (password.length() >= 8 && password.length() <= 16
            && password.matches(".*?\\d+.*")
            && password.matches(".*?[A-Za-z]+.*")) {
            this.password = password;
        } else {
            throw new PasswordIllegalException("密码设置不合法");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
    }

    public void save() {
        try {
            String sql = "INSERT INTO account(user_name, phone_number, email, password)" +
                "VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
