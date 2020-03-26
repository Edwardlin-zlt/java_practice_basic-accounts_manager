package com.thoughtworks.objects;

import com.thoughtworks.utils.dao.JDBCUtils; // TODO::Question 文件结构目录与导包
// JDBCUtils明明在utils目录下, 而不是在utils.dao下
import com.thoughtworks.exceptions.registerexcps.EmailAddressIllegalException;
import com.thoughtworks.exceptions.registerexcps.PasswordIllegalException;
import com.thoughtworks.exceptions.registerexcps.PhoneNumberIllegalException;
import com.thoughtworks.exceptions.registerexcps.UserNameIllegalException;

import java.sql.Connection;
import java.sql.SQLException;

public class Account {
    private static Connection connection;
    private int id;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;

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
}
