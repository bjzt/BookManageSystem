package com.BookManageSystem.gui.frame;

import com.BookManageSystem.entity.user.User;
import com.BookManageSystem.gui.module.FoldableMenuBar;
import com.BookManageSystem.gui.pane.MainPanel;
import com.BookManageSystem.util.*;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame mainFrame = new MainFrame();

    private Container c = this.getContentPane();
    public JMenuBar menuBar;
    public JMenu help;
    public JMenu util;
    public JMenu about;
    public JMenuItem author;

    public static MainFrame getInstance(){
        return mainFrame;
    }

    private MainFrame(){
        init();
    }

    private void init(){
        String power = null;
        switch (Login.getIuser().getPower()){
            case User.USER: power = "用户";break;
            case User.VIPUSER: power = "尊敬的vip用户";break;
            case User.MANAGER: power = "管理员";break;
            case User.SUPERMANAGER: power = "超级管理员";break;
        }
        String moreTitle = power+" "+Login.getIuser().getUserName();

        this.setTitle("阅览室图书管理系统 "+moreTitle);
        this.setLayout(new BorderLayout());
        this.addFoldableMenuBar();//加入菜单栏面板
        this.addContentPanel();//加入内容面板
        this.setSize(800,500);
        PanelUtil.SetCenter(this);
        this.setJMenuBar(MenuBar());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private JMenuBar MenuBar(){
        menuBar = new JMenuBar();
        help = new JMenu("帮助");
        util = new JMenu("系统工具");
        about = new JMenu("关于作者");
        menuBar.add(help);
        menuBar.add(util);
        menuBar.add(about);
        author =new JMenuItem("周天");
        about.add(author);

        return menuBar;
    }
    //增加折叠菜单栏
    private void addFoldableMenuBar(){
        c.add(FoldableMenuBar.getFoldableMenuBar(),BorderLayout.WEST);
    }
    //增加内容面板
    private void addContentPanel(){
        c.add(MainPanel.getMainPanel());
    }

}
