package com.BookManageSystem.gui.pane;

import com.BookManageSystem.entity.Book;
import com.BookManageSystem.service.BookService;
import com.BookManageSystem.util.PanelUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddBookPanel extends JPanel {
    public JTextField bookName = new JTextField();
    public JComboBox<String> bookType;
    public JTextField bookTypeBox= new JTextField();
    public JTextField booksNameValue = new JTextField();
    public JTextField bookNumber =new JTextField();
    public JTextField author = new JTextField();
    public JComboBox<String> languageType;
    public JTextField languageTypeBox =new JTextField();
    public JTextField sum = new JTextField() ;
    public JComboBox<String> bookConcern;
    public JTextField money = new JTextField();
    public JTextArea remark = new JTextArea(20,5);
    public JButton save;
    public JButton reset;
    public JTextField Concern = new JTextField();

    private static AddBookPanel addBookPanel = new AddBookPanel();
    public static AddBookPanel getAddBookPanel(){
        return addBookPanel;
    }
    private AddBookPanel(){
        this.setLayout(null);

        JLabel name = new JLabel("图书名称：");
        name.setBounds(30,20,70,25);
        bookName.setBounds(100,20,170,25);

        JLabel typeName = new JLabel("图书类型：");
        typeName.setBounds(300,20,70,25);
        String [] types = {"计算机科学","人文自然","旅游地理","各国语言"};
        bookType = new JComboBox<>(types);
        bookType.setBounds(370,20,100,25);
        bookTypeBox.setBounds(470,20,70,25);

        JLabel booksName = new JLabel("书集系列名：");
        booksName.setBounds(30,60,90,25);
        booksNameValue.setBounds(120,60,420,25);

        JLabel bookNumberLb = new JLabel("分卷编号：");
        bookNumberLb.setBounds(30,100,70,25);
        bookNumber.setBounds(95,100,100,25);

        JLabel authorLabel = new JLabel("编著者：");
        authorLabel.setBounds(200,100,70,25);
        author.setBounds(250,100,190,25);

        JLabel language = new JLabel("语种：");
        language.setBounds(450,100,70,25);
        String lty[] = {"中文","英语","阿拉伯语","法语","俄语","..."};
        languageType = new JComboBox<>(lty);
        languageType.setBounds(490,100,70,25);
        languageTypeBox.setBounds(560,100,65,25);

        JLabel bookConcernLab = new JLabel("选择出版社：");
        bookConcernLab.setBounds(30,140,90,25);
        String[] temp = {"清华大学出版社","北京大学出版社","吉林大学出版社","商务出版社","..."};
        bookConcern = new JComboBox<>(temp);
        bookConcern.setBounds(120,140,120,25);
        Concern.setBounds(240,140,110,25);
//        Concern.setText((String)bookConcern.getSelectedItem());

        JLabel sumLab = new JLabel("本次录入本数：");
        sumLab.setBounds(360,140,90,25);
        sum.setBounds(450,140,80,25);

        JLabel moneyLab = new JLabel("书籍定价:");
        moneyLab.setBounds(30,180,80,25);
        money.setBounds(100,180,80,25);
        JLabel yuan = new JLabel("元");
        yuan.setBounds(180,180,20,25);

        JLabel remarkLab = new JLabel("备注");
        remarkLab.setBounds(240,180,50,25);
        remark.setLineWrap(true);
        remark.setBounds(240,220,300,125);

        save = new JButton("保存");
        save.setBounds(30,310,80,30);
        reset = new JButton("重置");
        reset.setBounds(120,310,80,30);
        this.add(name);
        this.add(bookName);
        this.add(typeName);
        this.add(bookType);
        this.add(bookTypeBox);
        this.add(booksName);
        this.add(booksNameValue);
        this.add(bookNumberLb);
        this.add(bookNumber);
        this.add(authorLabel);
        this.add(author);
        this.add(language);
        this.add(languageType);
        this.add(languageTypeBox);
        this.add(sumLab);
        this.add(sum);
        this.add(bookConcernLab);
        this.add(bookConcern);
        this.add(Concern);
        this.add(moneyLab);
        this.add(money);
        this.add(yuan);
        this.add(remarkLab);
        this.add(remark);
        this.add(save);
        this.add(reset);
        addListener();
        this.setSize(650,450);
        this.setVisible(true);
    }
    private void addListener(){
        save.addMouseListener(buttonAction);
        reset.addMouseListener(buttonAction);
        bookConcern.addActionListener(comboBoxAction);
        bookType.addActionListener(comboBoxAction);
        languageType.addActionListener(comboBoxAction);
    }
    private Boolean isNull(JTextField ... inputBox){
        for (JTextField input:inputBox){
            if(PanelUtil.isNull(input)==true){
                return true;
            }
        }
        return false;
    }
    MouseAdapter buttonAction = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource()==save){
                Book book = new Book();
                if(!isNull(bookName,bookTypeBox,author,languageTypeBox,Concern,sum,money)){
                    book.setBookName(bookName.getText());
                    book.setBooksType(bookTypeBox.getText());
                    book.setAuthorName(author.getText());
                    book.setLanguageType(languageTypeBox.getText());
                    book.setBookConcern(Concern.getText());
                    book.setBookNumber(Integer.valueOf(sum.getText()));
                    book.setMoney(Double.valueOf(money.getText()));
                    book.setRemark(remark.getText());
//                    System.out.println(book);
                    //存入数据库
                    BookService.add(book);
                }
            }
            if(e.getSource()==reset){
                bookName.setText("");
                bookTypeBox.setText("");
                author.setText("");
                languageTypeBox.setText("") ;
                Concern.setText("");
                sum.setText("");
                money.setText("");
                remark.setText("");
                booksNameValue.setText("");
                bookNumber.setText("");
//                AddBookPanel.this.updateUI();
            }
        }
    };
    ActionListener comboBoxAction = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==bookConcern){
                Concern.setText((String)bookConcern.getSelectedItem());
            }
            if (e.getSource()==languageType){
                languageTypeBox.setText((String)languageType.getSelectedItem());
            }
            if (e.getSource()==bookType){
                bookTypeBox.setText((String)bookType.getSelectedItem());
            }
        }
    };
}