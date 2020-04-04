package com.thoughtworks;

import com.thoughtworks.exceptions.FieldIllegalException;
import com.thoughtworks.exceptions.UserInputFormatException;
import com.thoughtworks.objects.Account;
import com.thoughtworks.objects.AccountUtils;
import com.thoughtworks.objects.LogInManager;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static LogInManager logInManager = new LogInManager();

    public static void main(String[] args) {
        while (true) {
            initInfo();
            String userCommand = scanner.next();
            switch (userCommand) {
                case "1":
                    signUpProcess();
                    break;
                case "2":
                    System.out.println("请输入用户名和密码(格式：用户名,密码)： ");
//                    logInRawInfo();
//                    loginTillSuccessOrBlock();
                    break;
                case "3":
                    System.exit(0);
            }
        }
    }

    public static void signUpProcess() {
        System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)： ");
        while (true) {
            String userInput = scanner.next();
            try {
                Account account = AccountUtils.signUpAndGetAccount(userInput);
                System.out.println(account.getUserName() + ", 恭喜你注册成功!");
                break;
            } catch (UserInputFormatException e) {
                System.out.println("格式错误");
                System.out.println("请按正确格式输入注册信息：");
            } catch (FieldIllegalException e) {
                System.out.println(e.getMessage());
                System.out.println("请输入合法的注册信息：");
            }
        }
    }

//    @Deprecated
//    private static void loginTillSuccessOrBlock() {
//        try {
//            Account curAccount = logInManager.logInAndGetAccount();
//            System.out.println(curAccount.getUserName() + ", 欢迎回来！");
//            System.out.println("您的手机号是" + curAccount.getPhoneNumber() + ", 邮箱是" + curAccount.getEmail());
//        } catch (TooManyLoginAttemptsException e) {
//            System.out.println(e.getMessage());
//        } catch (LoginException e) {
////            System.out.println(e.getMessage());
//            System.out.println("密码或用户名错误");
//            System.out.println("请重新输入用户名和密码： ");
//            logInRawInfo();
//            loginTillSuccessOrBlock();
//        }
//    }
//
//    private static void logInRawInfo() {
//        try {
//            String userInput = scanner.next();
//            logInManager.parseUserInput(userInput);
//        } catch (UserInputFormatException e) {
//            System.out.println("格式错误");
//            System.out.println("请按正确格式输入注册信息：");
//            logInRawInfo();
//        }
//    }

    private static void initInfo() {
        System.out.println("1. 注册");
        System.out.println("2. 登录");
        System.out.println("3. 退出");
        System.out.println("请输入你的选择(1~3)： ");
    }
}
