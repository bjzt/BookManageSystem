package com.BookManageSystem.entity.user;

public interface UserPower {
    final static int SUPERMANAGER=10;
    final static int MANAGER=7;
    final static int VIPUSER=5;
    final static int USER=1;
    public int getPower();
}
