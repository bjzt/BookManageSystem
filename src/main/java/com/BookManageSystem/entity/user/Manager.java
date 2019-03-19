package com.BookManageSystem.entity.user;

public class Manager extends VIPUser {
    private final int power=MANAGER;

    public Manager(){}
    public Manager(String userName,String password){
        setUserName(userName);
        setPassword(password);
    }
    public Manager(String userName,String password,int id){
        this(userName,password);
        setId(id);
    }

    @Override
    public int getPower() {
        return this.power;
    }
}