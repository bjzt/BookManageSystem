package com.BookManageSystem.gui.pane;

import com.BookManageSystem.gui.frame.Login;
import com.BookManageSystem.service.UserService;
import com.BookManageSystem.util.PanelUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ReviseCodePanel extends JPanel {
    private JPasswordField password1 = new JPasswordField();
    private JPasswordField password2 = new JPasswordField();
    private JPasswordField newPassword = new JPasswordField();
    private JButton submit = new JButton("提交修改");
    private JButton cancel = new JButton("取消修改");
    private JLabel point = new JLabel();
    private static ReviseCodePanel reviseCodePane = new ReviseCodePanel();
    public static ReviseCodePanel getReviseCodePane(){
        return reviseCodePane;
    }
    private ReviseCodePanel(){
        super();
        this.setSize(600,450);
        this.setLayout(null);
        JLabel label1 = new JLabel("请输入原密码：");
        label1.setBounds(150,70,100,25);
        password1.setBounds(250,70,200,25);
        JLabel label2 = new JLabel("请确认密码：");
        label2.setBounds(150,150,100,25);
        password2.setBounds(250,150,200,25);
        JLabel label3 = new JLabel("请输入新密码：");
        label3.setBounds(150,220,100,25);
        newPassword.setBounds(250,220,200,25);
        submit.setBounds(200,300,80,30);
        cancel.setBounds(320,300,80,30);
        point.setBounds(230,260,100,25);
        this.add(label1);
        this.add(password1);
        this.add(label2);
        this.add(password2);
        this.add(label3);
        this.add(newPassword);
        this.add(submit);
        this.add(cancel);
        this.add(point);
        this.addListener();
    }
    private void addListener(){
        submit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!PanelUtil.isNull(password1) && !PanelUtil.isNull(password1)) {
                    String password1_1 = String.valueOf(password1.getPassword());
                    String password2_2 = String.valueOf(password2.getPassword());
                    //判断这两个密码框输入是否相同
                    if (password1_1.equals(password2_2)) {
                        //判断当前用户的密码是否输入正确
                        if(Login.getIuser().getPassword().equals(password2_2)) {
                            if (!PanelUtil.isNull(newPassword)){
                                //更改当前的用户信息
                                Login.getIuser().setPassword(String.valueOf(newPassword.getPassword()));
                                //更新数据库用户表信息
                                UserService.update(Login.getIuser());
                                point.setText("密码修改成功");
                            }else {//提示：修改成功
                                point.setText("密码不能为空");
                            }
                        }else {//提示：密码输入错误
                            point.setText("密码输入错误");
                        }
                    }else {//提示：密码输入不一致
                        point.setText("密码输入不一致");
                    }
                }else {//提示：密码不能为空白
                    point.setText("密码不能为空白");
                }
            }
        });
        cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                password1.setText("");
                password2.setText("");
                newPassword.setText("");
            }
        });
    }

}
