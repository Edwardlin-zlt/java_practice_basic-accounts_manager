package com.thoughtworks;

import com.thoughtworks.exceptions.PhoneNumberIllegalException;
import com.thoughtworks.exceptions.RegisterException;
import com.thoughtworks.exceptions.userInputFormatException;

import java.util.Scanner;

public class AccountRegister {
    private static Scanner scanner = new Scanner(System.in);
    private String userInput;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;

    public void setUserInput(String userInput) throws userInputFormatException {
        String[] userRawInfo = userInput.split(",");
        if (userRawInfo.length == 4) {
            this.userInput = userInput;
            userName = userRawInfo[0];
            phoneNumber = userRawInfo[1];
            email = userRawInfo[2];
            password = userRawInfo[3];
        } else {
            throw new userInputFormatException();
        }
    }

    public Account createNewAccount() throws RegisterException {
        if (userInput == null) {
            throw new RuntimeException("You must get info from user first.");
        }
        Account account = new Account();
        account.setUserName(userName);
        account.setPhoneNumber(phoneNumber);
        account.setEmail(email);
        account.setPassword(password);
        return account;
    }
}
