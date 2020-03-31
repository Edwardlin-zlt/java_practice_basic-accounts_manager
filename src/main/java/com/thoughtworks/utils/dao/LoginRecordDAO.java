package com.thoughtworks.utils.dao;

import com.thoughtworks.objects.Account;
import com.thoughtworks.objects.LoginRecord;
import com.thoughtworks.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginRecordDAO {
    public static Connection connection;

    static {
        try {
            connection = JDBCUtils.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<LoginRecord> queryByUserId(int userId) {
        String sql = "SELECT id, user_id, login_time, lock_flag, failure_count FROM login_record WHERE user_id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<LoginRecord> loginRecords = new ArrayList<>();
            if (resultSet.next()) {
                resultSet.previous();
            } else {
                return null;
            }
            while (resultSet.next()) {
                LoginRecord loginRecord = new LoginRecord();
                loginRecord.setId(resultSet.getInt("id"));
                loginRecord.setUserId(resultSet.getInt("user_id"));
                loginRecord.setLoginTime(resultSet.getDate("login_time"));
                loginRecord.setLockFlag(resultSet.getBoolean("lock_flag"));
                loginRecord.setFailureCount(resultSet.getInt("failure_count"));
                loginRecords.add(loginRecord);
            }
            resultSet.close(); // TODO::Question RS应该在finally中close吗？怎么才能做到
            return loginRecords;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void accountFirstRecord(Account account) {
        String sql = "INSERT INTO login_record(user_id)" +
            "VALUES (?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, account.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newRecord(int userId, int failureCount) {
        String sql = "INSERT INTO login_record(user_id,failure_count)" +
            "VALUES (?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, failureCount);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newRecord(int userId, int failureCount, boolean lockFlag) {
        String sql = "INSERT INTO login_record(user_id,failure_count, lock_flag)" +
            "VALUES (?, ?, ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, failureCount);
            preparedStatement.setBoolean(3, lockFlag);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
