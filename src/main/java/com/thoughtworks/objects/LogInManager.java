package com.thoughtworks.objects;

import com.thoughtworks.exceptions.userInputFormatException;
import com.thoughtworks.utils.dao.AccountRepository;
import com.thoughtworks.utils.dao.LoginRecordDAO;

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

    public Account logIn() {
        Account account = AccountRepository.queryByUserName(userName);
        if (account == null) {
            throw new
        }
        LoginRecordDAO.queryByUserId(account.getId());
        // login_record record
        if (Objects.equals(account.getPassword(), password)) {

        }

    }
}
