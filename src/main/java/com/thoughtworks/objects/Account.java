package com.thoughtworks.objects;

import com.thoughtworks.exceptions.FieldIllegalException;

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

    public void setUserName(String userName) throws FieldIllegalException {
        if (userName.length() >= 2 && userName.length() <= 10) {
            this.userName = userName;
        } else {
            throw new FieldIllegalException("UserName");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws FieldIllegalException {
        if (phoneNumber.startsWith("1") && phoneNumber.length() == 11) {
            this.phoneNumber = phoneNumber;
        } else {
            throw new FieldIllegalException("PhoneNumber");
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws FieldIllegalException {
        if (email.contains("@")) {
            this.email = email;
        } else {
            throw new FieldIllegalException("Email");
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws FieldIllegalException {
        // TODO 使用一个正则表达式 check password
        if (password.length() >= 8 && password.length() <= 16
            && password.matches(".*?\\d+.*")
            && password.matches(".*?[A-Za-z]+.*")) {
            this.password = password;
        } else {
            throw new FieldIllegalException("Password");
        }
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
