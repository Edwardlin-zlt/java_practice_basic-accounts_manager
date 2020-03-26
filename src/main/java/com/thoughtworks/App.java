package com.thoughtworks;

import com.thoughtworks.exceptions.registerexcps.RegisterException;
import com.thoughtworks.exceptions.userInputFormatException;
import com.thoughtworks.objects.Account;
import com.thoughtworks.objects.LogInManager;
import com.thoughtworks.objects.SignUpManager;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static SignUpManager signUpManager = new SignUpManager();
    private static LogInManager logInManager = new LogInManager();

    public static void main(String[] args) {
//        initInfo();
//        String userCommand = scanner.next();
//        if (Objects.equals(userCommand, "1")) {
//            System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)： ");
//            getUserRawInfo();
//            createNewAccount();
//        }
        while (true) {
            initInfo();
            String userCommand = scanner.next();
            switch (userCommand) {
                case "1":
                    System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)： ");
                    signInRawInfo();
                    createNewAccount();
                    break;
                case "2":
                    System.out.println("请输入用户名和密码(格式：用户名,密码)： ");
                    logInRawInfo();
                    logInTillSuccess();
                    break;

            }
        }
    }

    private static void logInTillSuccess() {
        try {
            Account curAccount = logInManager.logIn();
            System.out.println(curAccount.getUserName() + ", 欢迎回来！");
            System.out.println("您的手机号是" + curAccount.getPhoneNumber() + ", 邮箱是" + curAccount.getEmail());
        }
        // catch 帐号不存在
        // catch 密码错误

    }

    private static void logInRawInfo() {
        try {
            String userInput = scanner.next();
            logInManager.parseUserInput(userInput);
        } catch (userInputFormatException e) {
            System.out.println("格式错误");
            System.out.println("请按正确格式输入注册信息：");
            logInRawInfo();
        }
    }

    private static void createNewAccount() {
        try {
            Account newAccount = signUpManager.createNewAccount();
            System.out.println(newAccount.getUserName() + ", 恭喜你注册成功!");
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
            System.out.println("请输入合法的注册信息：");
            signInRawInfo();
            createNewAccount();
        }
    }

    private static void signInRawInfo() {
        try {
            String userInput = scanner.next(); // next()后接nextLine会读入next()留下的空白字符,导致问题
//            accountRegister.setUserInput(scanner.nextLine());
            signUpManager.parseUserInput(userInput);
        } catch (userInputFormatException e) {
            System.out.println("格式错误");
            System.out.println("请按正确格式输入注册信息：");
            signInRawInfo();
        }
    }

    private static void initInfo() {
        System.out.println("1. 注册");
        System.out.println("2. 登录");
        System.out.println("3. 退出");
        System.out.println("请输入你的选择(1~3)： ");
        // TODO print 不打印信息 要执行到下一个println才会打印东西？
    }
}
