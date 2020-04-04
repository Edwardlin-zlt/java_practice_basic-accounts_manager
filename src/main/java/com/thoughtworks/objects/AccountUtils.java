package com.thoughtworks.objects;

import com.thoughtworks.LoginParsedInfo;
import com.thoughtworks.SignUpParsedInfo;
import com.thoughtworks.UserInputParser;
import com.thoughtworks.exceptions.FieldIllegalException;
import com.thoughtworks.exceptions.UserInputFormatException;
import com.thoughtworks.exceptions.loginexcps.AccountNotExistException;
import com.thoughtworks.exceptions.loginexcps.TooManyLoginAttemptsException;
import com.thoughtworks.exceptions.loginexcps.WrongPasswordException;
import com.thoughtworks.utils.dao.AccountRepository;
import com.thoughtworks.utils.dao.LoginRecordRepository;

import java.util.List;
import java.util.Objects;

public final class AccountUtils {
    private AccountUtils() {
    }

    public static Account signUpAndGetAccount(String userRawInput) throws UserInputFormatException, FieldIllegalException {
        SignUpParsedInfo signUpParsedInfo = UserInputParser.signUpInfoParse(userRawInput);
        return AccountUtils.createNewAccount(signUpParsedInfo);
    }

    public static Account createNewAccount(SignUpParsedInfo signUpParsedInfo) throws FieldIllegalException {
        Account account = new Account();
        account.setUserName(signUpParsedInfo.getUserName());
        account.setPhoneNumber(signUpParsedInfo.getPhoneNumber());
        account.setEmail(signUpParsedInfo.getEmail());
        account.setPassword(signUpParsedInfo.getPassword());
        AccountRepository.save(account);
        return account;
    }

    public static Account loginAndGetAccount(String userRawInput) throws TooManyLoginAttemptsException, WrongPasswordException, UserInputFormatException, AccountNotExistException {
        LoginParsedInfo loginParsedInfo = UserInputParser.loginInfoParse(userRawInput);
        Account account = AccountRepository.queryByUserName(loginParsedInfo.getUserName());
        if (account == null) {
            throw new AccountNotExistException();
        }
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

    public static LoginRecord getLastRecords(Account account) {
        List<LoginRecord> loginRecords;
        loginRecords = LoginRecordRepository.queryByUserId(account.getId());
        if (loginRecords == null) {
            LoginRecordRepository.accountFirstRecord(account);
            loginRecords = LoginRecordRepository.queryByUserId(account.getId());
        }
        return loginRecords.get(loginRecords.size() - 1);
    }
}
