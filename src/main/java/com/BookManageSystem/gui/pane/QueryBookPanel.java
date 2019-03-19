package com.BookManageSystem.gui.pane;

import com.BookManageSystem.entity.Book;
import com.BookManageSystem.service.BookService;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class QueryBookPanel extends JPanel {
    public JTextField searchBox = new JTextField(15);
    public JButton search = new JButton("点击搜索");
    JTable table;
    DefaultTableModel taM;
    final Object[] title = {"编号","书籍名称","书籍作者","库存数","书籍类型","语种","出版社","定价","备注"};
    public JButton update = new JButton("跳到页面");
    public JButton previous = new JButton("上一页");
    public JTextField pageNum = new JTextField("1",5);
    public JButton next = new JButton("下一页");
    private List<Book> books;

    public static QueryBookPanel getQueryBookPanel(){
        return queryBookPanel;
    }
    private static QueryBookPanel queryBookPanel = new QueryBookPanel();
    private QueryBookPanel(){
        this.setLayout(new FlowLayout());
        this.add(searchBox);
        this.add(search);
        this.addTable();

        this.setSize(632,428);
        this.setVisible(true);
    }
    private void addTable(){
//        Object[][] date = new Object[10][9];

        taM = new DefaultTableModel(this.dateInfo(this.getPageDate()), title);
        table = new JTable(taM);
        table.setPreferredScrollableViewportSize(new Dimension(600, 275));
        table.setRowHeight (25);
        JPanel dateTable = new JPanel();
        dateTable.setLayout(new GridLayout (0, 1));
        dateTable.setPreferredSize(new Dimension(600,280));

        dateTable.add(new JScrollPane(table));
        //添加表格
        this.add(dateTable);

        this.add(previous);
        this.add(pageNum);
        this.add(update);
        this.add(next);

        this.addListener();//添加事件
        this.updateUI();
    }
    private Object[][] dateInfo(List<Book> books) {//BookService.list()
        Object[][] date = new Object[10][9];
        this.books = books;
        for (int j = 0; j <= (books.size() / 10) && (books.size() % 10) != 0; j++) {
            for (int i = 0; i < 10; i++) {
                if((10*j+i)>=books.size()){
                    break;
                }
                Book book = books.get(i);
                date[i%9][0] = book.getId();
                date[i%9][1] = book.getBookName();
                date[i%9][2] = book.getAuthorName();
                date[i%9][3] = book.getBookNumber();
                date[i%9][4] = book.getBooksType();
                date[i%9][5] = book.getLanguageType();
                date[i%9][6] = book.getBookConcern();
                date[i%9][7] = book.getMoney();
                date[i%9][8] = book.getRemark();
            }
        }
        return date;
    }

    /**
     * 为当前的页面组件添加监听事件
     */
    private void addListener(){
        search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String search = searchBox.getText();
                taM.setRowCount(0);
                Object[][] date = QueryBookPanel.this.dateInfo(BookService.queryBook(search));
                for (int i=0;i<date.length;i++){
                    taM.addRow(date[i]);
                }
                QueryBookPanel.this.table.updateUI();
            }
        });
        previous.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int page = Integer.valueOf(pageNum.getText());
                if(page!=1){
                    page--;
                    pageNum.setText(""+page);
                    taM.setRowCount(0);
                    Object[][] date = QueryBookPanel.this.dateInfo(getPageDate());
                    for (int i=0;i<date.length;i++){
                        taM.addRow(date[i]);
                    }
                    QueryBookPanel.this.table.updateUI();
                }
            }
        });
        update.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int page = Integer.valueOf(pageNum.getText());
                if((page<=(BookService.getTotal()/10.0+1))&&(page!=1)){

                    taM.setRowCount(0);
                    Object[][] date = QueryBookPanel.this.dateInfo(getPageDate());
                    for (int i=0;i<date.length;i++){
                        taM.addRow(date[i]);
                    }
                    QueryBookPanel.this.table.updateUI();
                }
            }
        });
        next.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int page = Integer.valueOf(pageNum.getText());
                if((page+1)<=(BookService.getTotal()/10.0+1)){
                    page++;
                    pageNum.setText(""+page);
                    taM.setRowCount(0);
                    Object[][] date = QueryBookPanel.this.dateInfo(getPageDate());
                    for (int i=0;i<date.length;i++){
                        taM.addRow(date[i]);
                    }
                    QueryBookPanel.this.table.updateUI();
                }
            }
        });
    }

    /**
     * 实现分页查询
     * @return 当前页面的查询数据
     */
    private List<Book> getPageDate(){
        int page = Integer.valueOf(pageNum.getText());
        return  BookService.list((page-1)*9,page*9);
    }
}
