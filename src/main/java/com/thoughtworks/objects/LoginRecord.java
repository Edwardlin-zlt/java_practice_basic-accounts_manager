package com.thoughtworks.objects;

import java.sql.Date;

public class LoginRecord {
    private int id;
    private int userId;
    private Date loginTime;
    private boolean lockFlag;
    private int failureCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public boolean isLockFlag() {
        return lockFlag;
    }

    public void setLockFlag(boolean lockFlag) {
        this.lockFlag = lockFlag;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    @Override
    public String toString() {
        return "LoginRecord{" +
            "id=" + id +
            ", userId=" + userId +
            ", loginTime=" + loginTime +
            ", lockFlag=" + lockFlag +
            ", failureCount=" + failureCount +
            '}';
    }
}
