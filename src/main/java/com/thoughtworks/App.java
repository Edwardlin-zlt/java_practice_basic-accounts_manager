package com.thoughtworks;

import com.thoughtworks.exceptions.RegisterException;
import com.thoughtworks.exceptions.UserNameIllegalException;
import com.thoughtworks.exceptions.userInputFormatException;
import jdk.internal.jline.console.UserInterruptException;

import java.util.Objects;
import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static AccountRegister accountRegister = new AccountRegister();

    public static void main(String[] args) {
        initInfo();
        String userCommand = scanner.next();
        if (Objects.equals(userCommand, "1")) {
            System.out.println("请输入注册信息(格式：用户名,手机号,邮箱,密码)： ");
            getUserRawInfo();
            createNewAccount();
        }
    }

    private static void createNewAccount() {
        try {
            Account newAccount = accountRegister.createNewAccount();
            newAccount.save();
            System.out.println(newAccount.getUserName() + ", 恭喜你注册成功!");
        } catch (RegisterException e) {
            System.out.println(e.getMessage());
            System.out.println("请输入合法的注册信息：");
            getUserRawInfo();
            createNewAccount();
        }
    }

    private static void getUserRawInfo() {
        try {
            String userInput = scanner.next(); // next()后接nextLine会读入next()留下的空白字符,导致问题
//            accountRegister.setUserInput(scanner.nextLine());
            accountRegister.setUserInput(userInput);
        } catch (userInputFormatException e) {
            System.out.println("格式错误");
            System.out.println("请按正确格式输入注册信息：");
            getUserRawInfo();
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
