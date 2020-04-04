package com.thoughtworks.objects;

import com.thoughtworks.LoginParsedInfo;
import com.thoughtworks.exceptions.loginexcps.AccountNotExistException;
import com.thoughtworks.exceptions.loginexcps.TooManyLoginAttemptsException;
import com.thoughtworks.exceptions.loginexcps.WrongPasswordException;
import com.thoughtworks.utils.dao.AccountRepository;
import com.thoughtworks.utils.dao.LoginRecordRepository;

import java.util.List;
import java.util.Objects;

public class LogInManager {
    public Account loginAndGetAccount(LoginParsedInfo loginParsedInfo) throws AccountNotExistException, TooManyLoginAttemptsException, WrongPasswordException {
        Account account = getAccount(loginParsedInfo.getUserName());
        LoginRecord lastRecord = getLastRecords(account);
        if (lastRecord.getFailureCount() >= 3) {
            throw new TooManyLoginAttemptsException("您已3次输错密码，账号被锁定");
        }
        if (Objects.equals(account.getPassword(), loginParsedInfo.getPassword())) {
            LoginRecordRepository.save(account.getId(), 0);
            return account;
        } else {
            LoginRecordRepository.save(account.getId(), lastRecord.getFailureCount() + 1);
            throw new WrongPasswordException();
        }
    }

    private LoginRecord getLastRecords(Account account) {
        List<LoginRecord> loginRecords;
        loginRecords = LoginRecordRepository.queryByUserId(account.getId());
        if (loginRecords == null) {
            LoginRecordRepository.accountFirstRecord(account);
            loginRecords = LoginRecordRepository.queryByUserId(account.getId());
        }
        assert loginRecords != null; //　TODO assert的使用
        return loginRecords.get(loginRecords.size() - 1);
    }

    private Account getAccount(String userName) throws AccountNotExistException {
        Account account = AccountRepository.queryByUserName(userName);
        if (account == null) {
            throw new AccountNotExistException(); // 用户不存在
        }
        return account;
    }
}
