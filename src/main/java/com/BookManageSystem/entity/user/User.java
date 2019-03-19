package com.BookManageSystem.entity.user;

public class User implements UserPower {
    private final int power = USER;
    private int id;
    private String userName;
    private String password;

    public User(){}
    public User(String userName,String password){
        this.userName=userName;
        this.password=password;
    }
    public User(String userName,String password,int id){
        this(userName,password);
        this.id = id;
    }
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
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public int getPower() {
        return this.power;
    }
}
