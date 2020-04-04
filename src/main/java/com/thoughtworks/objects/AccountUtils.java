package com.thoughtworks.objects;

import com.thoughtworks.SignUpParsedInfo;
import com.thoughtworks.UserInputParser;
import com.thoughtworks.exceptions.FieldIllegalException;
import com.thoughtworks.exceptions.UserInputFormatException;
import com.thoughtworks.utils.dao.AccountRepository;

public final class AccountUtils {
    private AccountUtils() {
    }

    public static Account signUpAndGetAccount(String userRowInput) throws UserInputFormatException, FieldIllegalException {
        SignUpParsedInfo signUpParsedInfo = UserInputParser.signupInfoParse(userRowInput);
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

}
