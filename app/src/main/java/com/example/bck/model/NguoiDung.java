package com.example.bck.model;

public class NguoiDung {
    public   String userName,fullName,pass,email;

    public NguoiDung() {
    }

    public NguoiDung(String userName, String fullName, String pass, String email) {
        this.userName = userName;
        this.fullName = fullName;
        this.pass = pass;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
