package com.BookManageSystem.entity.user;

public class SuperManager extends Manager {
    private final int power=SUPERMANAGER;

    public SuperManager(){}
    public SuperManager(String userName,String password){
        setUserName(userName);
        setPassword(password);
    }
    public SuperManager(String userName,String password,int id){
        this(userName,password);
        setId(id);
    }

    @Override
    public int getPower() {
        return this.power;
    }
}