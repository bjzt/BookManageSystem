package com.BookManageSystem.gui.module;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;

public class FoldableMenuBar extends JPanel{
    protected ImageIcon img = new ImageIcon("./logo.png");
    protected JLabel logo = new JLabel();
    protected JButton BookManage = new JButton("图书管理");
    protected JButton libraryCard = new JButton("借书证管理");
    protected JButton borrower = new JButton("外借人管理");
    protected JButton library = new JButton("图书馆管理");
    protected JButton finance = new JButton("财务管理");
    protected JButton query = new JButton("查询管理");
    protected JButton system = new JButton("系统设置");
    protected ArrayList<JButton> buttons = new ArrayList<>();
    protected ArrayList<JButton> temp=new ArrayList<>();
    private static FoldableMenuBar foldableMenuBar = new FoldableMenuBar();
    public static FoldableMenuBar getFoldableMenuBar(){
        return foldableMenuBar;
    }
    private FoldableMenuBar(){
        this.setLayout(new GridLayout(11,1));
        this.setPreferredSize(new Dimension(150, 600));

        img.setImage(img.getImage().getScaledInstance(150, 50,Image.SCALE_FAST));



        logo.setIcon(img);
        this.add(logo,0);
        this.add(BookManage,1);
        buttons.add(BookManage);
        this.add(libraryCard,2);
        buttons.add(libraryCard);
        this.add(borrower,3);
        buttons.add(borrower);
        this.add(library,4);
        buttons.add(library);
        this.add(finance,5);
        buttons.add(finance);
        this.add(query,6);
        buttons.add(query);
        this.add(system,7);
        buttons.add(system);
        this.addListener();
    }

    private void addListener(){
        FoldableMenuAction action = new FoldableMenuAction();
        BookManage.addMouseListener(action);
        libraryCard.addMouseListener(action);
        borrower.addMouseListener(action);
        library.addMouseListener(action);
        finance.addMouseListener(action);
        query.addMouseListener(action);
        system.addMouseListener(action);
    }
}

