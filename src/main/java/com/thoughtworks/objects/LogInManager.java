package com.thoughtworks.objects;

import com.thoughtworks.exceptions.loginexcps.AccountNotExistException;
import com.thoughtworks.exceptions.loginexcps.TooManyLoginAttemptsException;
import com.thoughtworks.exceptions.loginexcps.WrongPasswordException;
import com.thoughtworks.exceptions.userInputFormatException;
import com.thoughtworks.utils.dao.AccountRepository;
import com.thoughtworks.utils.dao.LoginRecordDAO;

import java.util.List;
import java.util.Objects;

public class LogInManager {
    private String userInput;
    private String userName;
    private String password;

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

    public Account logIn() throws AccountNotExistException, TooManyLoginAttemptsException, WrongPasswordException {
        // 获取用户实例
        Account account = getAccount();
        // 获取用户对应的最后一条登录记录
        LoginRecord lastRecord = getLastRecords(account);
        boolean lockFlag = lastRecord.isLockFlag();
        if (lockFlag) {
            throw new TooManyLoginAttemptsException("您已3次输错密码，账号被锁定");
        }
        // 检查密码是否正确
        int failureCount = lastRecord.getFailureCount();
        if (Objects.equals(account.getPassword(), password)) {
            LoginRecordDAO.newRecord(account.getId(), 0);
            return account;
        } else {
            failureCount += 1;
            if (failureCount == 3) {
                LoginRecordDAO.newRecord(account.getId(), failureCount, true);
            } else {
                LoginRecordDAO.newRecord(account.getId(), failureCount);
                throw new WrongPasswordException("密码或用户名错误");
            }
        }
        return null;
    }

    private LoginRecord getLastRecords(Account account) {
        List<LoginRecord> loginRecords;
        loginRecords = LoginRecordDAO.queryByUserId(account.getId());
        if (loginRecords == null) {
            LoginRecordDAO.accountFirstRecord(account);
            loginRecords = LoginRecordDAO.queryByUserId(account.getId());
        }
        assert loginRecords != null; //　TODO assert的使用
        return loginRecords.get(loginRecords.size()-1);
    }

    private Account getAccount() throws AccountNotExistException {
        Account account = AccountRepository.queryByUserName(userName);
        if (account == null) {
            throw new AccountNotExistException("密码或用户名错误");
        }
        return account;
    }
}
