package com.thoughtworks.objects;

import com.thoughtworks.exceptions.UserInputFormatException;

public final class UserInputParser {
    private UserInputParser() {

    }

    public static SignUpParsedInfo signUpInfoParse(String rawInfo) throws UserInputFormatException {
        String[] parsedInfo = rawInfo.split(",");
        SignUpParsedInfo signUpParsedInfo;
        if (parsedInfo.length == 4) {
            signUpParsedInfo = new SignUpParsedInfo(parsedInfo[0],
                parsedInfo[1], parsedInfo[2], parsedInfo[3]);
        } else {
            throw new UserInputFormatException();
        }
        return signUpParsedInfo;
    }

    public static LoginParsedInfo loginInfoParse(String rawInfo) throws UserInputFormatException {

        String[] parsedInfo = rawInfo.split(",");
        LoginParsedInfo loginParsedInfo;
        if (parsedInfo.length == 2) {
            loginParsedInfo = new LoginParsedInfo(parsedInfo[0], parsedInfo[1]);
        } else {
            throw new UserInputFormatException();
        }
        return loginParsedInfo;
    }
}
