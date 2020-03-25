package com.thoughtworks;

import com.mysql.cj.core.exceptions.PasswordExpiredException;

import java.util.Objects;

public class Account {
    private int id;
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName.length() >= 2 && userName.length() <= 10) {
            this.userName = userName;
        } else {
            throw new UserNameIllegalException("The length of username must between 2 and 10");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("1") && phoneNumber.length() == 11) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new PhoneNumberIllegalException("Phone number must have 11 digits and starts with 1");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new EmailAddressIllegalException("Email address must contains '@'.");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        // TODO 使用一个正则表达式 check password
        if (password.length() >= 8 && password.length() <= 16
            && password.matches(".*?\\d+.*")
            && password.matches(".*?\\[A-Za-z]+.*")) {
            this.password = password;
        } else {
            throw new PasswordExpiredException("Illegal Password");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id &&
            Objects.equals(userName, account.userName) &&
            Objects.equals(phoneNumber, account.phoneNumber) &&
            Objects.equals(email, account.email) &&
            Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, phoneNumber, email, password);
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
