package com.BookManageSystem.entity.user;


public class VIPUser extends User {
    private final int power=VIPUSER;

    public VIPUser(){}
    public VIPUser(String userName,String password){
        setUserName(userName);
        setPassword(password);
    }
    public VIPUser(String userName,String password,int id){
        this(userName,password);
        setId(id);
    }

    @Override
    public int getPower() {
        return this.power;
    }
}