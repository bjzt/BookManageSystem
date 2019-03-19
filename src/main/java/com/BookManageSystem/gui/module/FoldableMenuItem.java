package com.BookManageSystem.gui.module;

import com.BookManageSystem.util.StyleUtil;
import com.BookManageSystem.gui.action.*;
import javax.swing.*;

public class FoldableMenuItem {

    //新的菜单项
    public JButton addBook = new JButton("添加图书");
    public JButton queryBook = new JButton("查询图书");
    public JButton oldBook = new JButton("旧书处理");

    public JButton enrollCard = new JButton("借书证注册");
    public JButton CardLV = new JButton("借书证等级");

    public JButton person = new JButton("借书人登记");

    public JButton libraryInfo = new JButton("图书馆信息介绍");

    public JButton todayIncome = new JButton("今日收入");

    public JButton lendQuery = new JButton("查询已借出图书");

    public JButton reviseCode = new JButton("修改密码");

    private static FoldableMenuItem menuItem = new FoldableMenuItem();
    public static FoldableMenuItem getFoldableMenuItem(){
        return menuItem;
    }
    private FoldableMenuItem(){
        this.buttonStyle();
        this.addAction(addBook,queryBook,oldBook,enrollCard,CardLV,
                person,libraryInfo,todayIncome,lendQuery,reviseCode);
    }
    private void buttonStyle(){
        //使按钮透明
        StyleUtil.buttonStyle(addBook,queryBook,oldBook,enrollCard,CardLV,
                person,libraryInfo,todayIncome,lendQuery,reviseCode);
    }

    private void addAction(JButton ... buttons){
        SwitchAction switchAction =new SwitchAction();
        for ( JButton button: buttons) {
            button.addMouseListener(switchAction);
        }
    }
}
