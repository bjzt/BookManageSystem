package com.BookManageSystem.gui.action;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.BookManageSystem.gui.module.*;
import com.BookManageSystem.gui.pane.*;

import javax.swing.*;

public class SwitchAction extends MouseAdapter {
    private JPanel pane;
    @Override
    public void mouseClicked(MouseEvent e) {
        //将原有的面板删掉
        MainPanel.getMainPanel().getContentPane().removeAll();
        MainPanel.getMainPanel().getLayeredPane().removeAll();
        //接下来根据按钮事件来添加功能面板
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().addBook){
            MainPanel.getMainPanel().setTitle("正在添加图书...");
            pane = AddBookPanel.getAddBookPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().queryBook){
            MainPanel.getMainPanel().setTitle("查询图书");
            pane = QueryBookPanel.getQueryBookPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().oldBook){
            System.out.println("oldBook");
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().enrollCard){
            MainPanel.getMainPanel().setTitle("用户注册");
            pane = EnrollCardPanel.getEnrollCardPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().CardLV){
            MainPanel.getMainPanel().setTitle("用户等级设置");
            pane = CardLVPanel.getCardLVPanel();
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().person){
            System.out.println("person");
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().libraryInfo){
            System.out.println("libraryInfo");
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().todayIncome){
            System.out.println("todayIncome");
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().lendQuery){
            System.out.println("lendQuery");
        }
        if (e.getSource() == FoldableMenuItem.getFoldableMenuItem().reviseCode){
            MainPanel.getMainPanel().setTitle("修改密码");
            pane=ReviseCodePanel.getReviseCodePane();
        }
        MainPanel.getMainPanel().getLayeredPane().add(pane);
//        MainPanel.getMainPanel().getContentPane().add(pane);
        MainPanel.getMainPanel().updateUI();
    }
}
