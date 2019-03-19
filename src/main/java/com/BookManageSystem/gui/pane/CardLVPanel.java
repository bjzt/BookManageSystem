package com.BookManageSystem.gui.pane;

import javax.swing.*;
import java.awt.*;

public class CardLVPanel extends JPanel {
    private static CardLVPanel cardLVPanel = new CardLVPanel();
    JButton VIPUser;
    JButton user;
    JButton manage;
    public static CardLVPanel getCardLVPanel(){
        return cardLVPanel;
    }
    private CardLVPanel(){
        this.setSize(600,450);
//        this.setLayout(null);
        Font labelFont = new Font("宋体",Font.BOLD,18);
        JLabel label = new JLabel("用户等级设置");
        /**
         * 本系统有四种用户等级：
         * 普通用户、vip用户、管理员、超级管理员
         */
        JLabel label1= new JLabel("用户");
        label1.setFont(labelFont);
        label1.setBounds(120,120,150,25);

        JLabel label2= new JLabel("vip用户");
        label2.setFont(labelFont);

        JLabel label3= new JLabel("管理员");
        label3.setFont(labelFont);

        JLabel label4= new JLabel("超级管理员");
        label4.setFont(labelFont);
        this.add(label);
        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
    }
}
