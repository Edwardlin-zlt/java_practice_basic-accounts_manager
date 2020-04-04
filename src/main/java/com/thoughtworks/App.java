package com.thoughtworks;

import com.thoughtworks.exceptions.FieldIllegalException;
import com.thoughtworks.exceptions.UserInputFormatException;
import com.thoughtworks.exceptions.loginexcps.AccountNotExistException;
import com.thoughtworks.exceptions.loginexcps.TooManyLoginAttemptsException;
import com.thoughtworks.exceptions.loginexcps.WrongPasswordException;
import com.thoughtworks.objects.Account;
import com.thoughtworks.objects.AccountUtils;

import java.util.Scanner;

public class App {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            initInfo();
            String userCommand = scanner.next();
            switch (userCommand) {
                case "1":
                    signUpProcess();
                    break;
                case "2":
                    loginProcess();
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

    private static void loginProcess() {
        System.out.println("请输入用户名和密码(格式：用户名,密码)： ");
        while (true) {
            String userInput = scanner.next();
            try {
                Account account = AccountUtils.loginAndGetAccount(userInput);
                System.out.println(account.getUserName() + ", 欢迎回来！");
                System.out.println("您的手机号是" + account.getPhoneNumber() + ", 邮箱是" + account.getEmail());
            } catch (TooManyLoginAttemptsException e) {
                System.out.println(e.getMessage());
            } catch (WrongPasswordException | AccountNotExistException e) {
                System.out.println("密码或用户名错误");
                System.out.println("请重新输入用户名和密码： ");
            } catch (UserInputFormatException e) {
                System.out.println("格式错误");
                System.out.println("请按正确格式输入注册信息：");
            }
        }
    }

    private static void initInfo() {
        System.out.println("1. 注册");
        System.out.println("2. 登录");
        System.out.println("3. 退出");
        System.out.println("请输入你的选择(1~3)： ");
    }
}
