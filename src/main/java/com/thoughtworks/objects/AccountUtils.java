package com.thoughtworks.objects;

import com.thoughtworks.SignUpParsedInfo;
import com.thoughtworks.exceptions.FieldIllegalException;
import com.thoughtworks.utils.dao.AccountRepository;


public class AccountUtils {
    public Account createNewAccount(SignUpParsedInfo signUpParsedInfo) throws FieldIllegalException {
        Account account = new Account();
        account.setUserName(signUpParsedInfo.getUserName());
        account.setPhoneNumber(signUpParsedInfo.getPhoneNumber());
        account.setEmail(signUpParsedInfo.getEmail());
        account.setPassword(signUpParsedInfo.getPassword());
        AccountRepository.save(account);
        return account;
    }
}
