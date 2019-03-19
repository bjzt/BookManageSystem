package com.BookManageSystem.gui.frame;

import com.BookManageSystem.entity.user.User;
import com.BookManageSystem.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    private static User Iuser;
    private Container c;
    private JLabel user;
    private JTextField username;
    private JLabel code;
    private JPasswordField password;
    private JButton login;
    private JButton reset;

    private static Login loginFrame = new Login();

    public static Login getInstance(){
        return loginFrame;
    }
    private Login(){
        init();
        action();
    }
    public static User getIuser(){
        return Iuser;
    }
    private void init(){
        this.setTitle("登录界面");
        c = this.getContentPane();
        c.setLayout(null);

        user = new JLabel("用户名：");
        user.setBounds(40,30,70,30);
        username = new JTextField(20);
        username.setBounds(100,30,200,30);
        code = new JLabel("密码：");
        code.setBounds(40,70,70,30);
        password = new JPasswordField(20);
        password.setBounds(100,70,200,30);
        reset = new JButton("重置");
        reset.setBounds(80,110,70,30);
        login = new JButton("登录");
        login.setBounds(220,110,70,30);
        try {
            //更改按钮系统样式
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        c.add(user);
        c.add(username);
        c.add(code);
        c.add(password);
        c.add(reset);
        c.add(login);
        this.setSize(350,220);
        //使用工具类来让这个窗口居中
        PanelUtil.SetCenter(this);//这个类必须要在该窗口设置了高宽后才能使用
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void action(){
        //重置
        reset.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                username.setText("");
                password.setText("");
            }
        });
        //登录实现
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(isUser()){//身份验证
                    MainFrame.getInstance();
                    Login.this.setVisible(false);
                }else {
                    //弹出提示框
                    new InputError(Login.this,"错误提示框",true);
                }
            }
        });
    }
    private boolean isUser(){
        String username = this.username.getText();//得到输入的用户名
        String password = String.valueOf(this.password.getPassword());//得到输入的密码
        //查询用户是否存在
        if(PanelUtil.getThisUser(username,password)==null){
            return false;
        }
        Iuser = PanelUtil.getThisUser(username,password);
        return true;
    }
}
