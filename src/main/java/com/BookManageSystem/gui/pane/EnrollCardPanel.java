package com.BookManageSystem.gui.pane;

import com.BookManageSystem.entity.user.User;
import com.BookManageSystem.service.UserService;
import com.BookManageSystem.util.PanelUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnrollCardPanel extends JPanel {
    private JTextField userName = new JTextField();
    private JPasswordField password1 = new JPasswordField();
    private JPasswordField password2 = new JPasswordField();
    private JButton save = new JButton("创建用户");
    private JButton cancel = new JButton("取消");
    private JLabel point;
    private JLabel pointPassword;
    private JLabel jlabel = new JLabel();

    private static EnrollCardPanel enrollCardPanel = new EnrollCardPanel();
    public static EnrollCardPanel getEnrollCardPanel(){
        return enrollCardPanel;
    }
    private EnrollCardPanel(){
        this.setLayout(null);
        JLabel label = new JLabel("用户注册");
        label.setBounds(250,50,150,50);
        label.setFont(new Font("宋体",Font.BOLD,24));

        Font labelFont = new Font("宋体",Font.BOLD,18);

        JLabel label1 = new JLabel("请输入用户名");
        label1.setFont(labelFont);
        label1.setBounds(120,120,150,25);
        userName.setBounds(260,120,200,25);
        point = new JLabel();
        point.setBounds(465,120,100,25);

        JLabel label2 = new JLabel("请输入密码");
        label2.setFont(labelFont);
        label2.setBounds(120,170,150,25);
        password1.setBounds(260,170,200,25);

        JLabel label3 = new JLabel("请确认密码");
        label3.setFont(labelFont);
        label3.setBounds(120,220,150,25);
        password2.setBounds(260,220,200,25);
        pointPassword =new JLabel();
        pointPassword.setBounds(265,250,100,25);

        save.setBounds(150,280,80,30);
        cancel.setBounds(350,280,80,30);

        jlabel.setBounds(200,250,100,25);
        this.setSize(600,450);

        this.add(label);
        this.add(label1);
        this.add(userName);
        this.add(point);

        this.add(label2);
        this.add(password1);
        this.add(label3);
        this.add(password2);
        this.add(pointPassword);
        this.add(save);
        this.add(cancel);
        this.add(jlabel);


        this.addListener();
        this.setVisible(true);
    }
    private void addListener(){
        save.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //判断该用户名是否重名
                if(!PanelUtil.isSameName(userName.getText())){
                    if (!PanelUtil.isNull(userName)) {
                        if (!PanelUtil.isNull(password2)) {
                            //判断这两个密码框输入是否相同
                            String password1_1 = String.valueOf(password1.getPassword());
                            String password2_2 = String.valueOf(password2.getPassword());
                            if (password1_1.equals(password2_2)) {
                                //创建一个新用户
                                User newUser = new User();
                                newUser.setUserName(userName.getText());
                                newUser.setPassword(String.valueOf(password1.getPassword()));
                                //存储到用户表
                                UserService.add(newUser);
                                jlabel.setText("创建用户成功");
                            } else pointPassword.setText("密码不相同");
                            pointPassword.setText("");
                        }else pointPassword.setText("密码不能为空");
                        point.setText("");
                    }else point.setText("用户名不能为空");
                    point.setText("");
                }else point.setText("用户名已存在");
            }
        });
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userName.setText("");
                password1.setText("");
                password2.setText("");
                point.setText("");
                pointPassword.setText("");
            }
        });
    }
}
