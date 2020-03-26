package com.thoughtworks.utils.dao;

import com.thoughtworks.objects.LoginRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginRecordDAO {
    public static Connection connection;
    static {
        try {
            connection = com.thoughtworks.utils.dao.JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static LoginRecord queryByUserId(int userId) {
        String sql = "SELECT id, user_id, login_time, lock_flag, failure_count\n" +
            "FROM login_record\n" +
            "WHERE user_id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            LoginRecord loginRecord = new LoginRecord();
            loginRecord.setId(resultSet.getInt("id"));
            loginRecord.setUserId(resultSet.getInt("user_id"));
            loginRecord.setLoginTime(resultSet.getDate("login_time"));
            loginRecord.setLockFlag(resultSet.getBoolean("lock_flag"));
            loginRecord.setFailureCount(resultSet.getInt("failure_count"));
            resultSet.close();
            return loginRecord;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void update(int id, LoginRecord loginRecord) {
        String sql = "UPDATE login_record" +
            "SET user_id=?,login_time=?,lock_flag=?,failure_count=?" +
            "WHERE id = ?;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, loginRecord.getUserId());
            preparedStatement.setDate(2, loginRecord.getLoginTime());
            preparedStatement.setBoolean(3, loginRecord.isLockFlag());
            preparedStatement.setInt(4, loginRecord.getFailureCount());
            preparedStatement.setInt(5, loginRecord.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
