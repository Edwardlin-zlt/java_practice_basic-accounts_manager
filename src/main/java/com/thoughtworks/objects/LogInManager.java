package com.thoughtworks.objects;

import com.thoughtworks.exceptions.loginexcps.AccountNotExistException;
import com.thoughtworks.exceptions.loginexcps.TooManyLoginAttemptsException;
import com.thoughtworks.exceptions.loginexcps.WrongPasswordException;
import com.thoughtworks.exceptions.UserInputFormatException;
import com.thoughtworks.utils.dao.AccountRepository;
import com.thoughtworks.utils.dao.LoginRecordRepository;

import java.util.List;
import java.util.Objects;

public class LogInManager {
    private String userInput;
    private String userName;
    private String password;
    // TODO 不要需要改变de 字段,不用更新自己的数据

    public void parseUserInput(String userInput) throws UserInputFormatException {
        // TODO 解析方法抽成工具类
        String[] userRawInfo = userInput.split(",");
        if (userRawInfo.length == 2) {
            this.userInput = userInput;
            userName = userRawInfo[0];
            password = userRawInfo[1];
        } else {
            throw new UserInputFormatException();
        }
    }


//    public Account logInAndGetAccount(userName, paswd) throws AccountNotExistException, TooManyLoginAttemptsException, WrongPasswordException {
    public Account logInAndGetAccount() throws AccountNotExistException, TooManyLoginAttemptsException, WrongPasswordException {
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
            LoginRecordRepository.newRecord(account.getId(), 0);
            return account;
        } else {
            failureCount += 1;
            if (failureCount == 3) {
                LoginRecordRepository.newRecord(account.getId(), failureCount, true);
            } else {
                LoginRecordRepository.newRecord(account.getId(), failureCount);
                throw new WrongPasswordException(); // 长
            }
        }
        return null;
    }

    private LoginRecord getLastRecords(Account account) {
        List<LoginRecord> loginRecords;
        loginRecords = LoginRecordRepository.queryByUserId(account.getId());
        if (loginRecords == null) {
            LoginRecordRepository.accountFirstRecord(account);
            loginRecords = LoginRecordRepository.queryByUserId(account.getId());
        }
        assert loginRecords != null; //　TODO assert的使用
        return loginRecords.get(loginRecords.size()-1);
    }

    private Account getAccount() throws AccountNotExistException {
        Account account = AccountRepository.queryByUserName(userName);
        if (account == null) {
            throw new AccountNotExistException(); // 用户不存在
        }
        return account;
    }
}
