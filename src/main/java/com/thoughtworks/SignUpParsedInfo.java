package com.thoughtworks;

import java.util.Objects;

public class SignUpParsedInfo {
    private String userName;
    private String phoneNumber;
    private String email;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignUpParsedInfo() {
    }

    public SignUpParsedInfo(String userName, String phoneNumber, String email, String password) {
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "SignUpParsedInfo{" +
            "userName='" + userName + '\'' +
            ", phoneNumber=" + phoneNumber +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SignUpParsedInfo that = (SignUpParsedInfo) o;
        return phoneNumber == that.phoneNumber &&
            Objects.equals(userName, that.userName) &&
            Objects.equals(email, that.email) &&
            Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, phoneNumber, email, password);
    }
}
