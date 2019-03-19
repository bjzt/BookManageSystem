package com.BookManageSystem.dao;

import com.BookManageSystem.entity.Book;
import com.BookManageSystem.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements DAO<Book> {

    @Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select count(*) from book";

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt(1);
            }
//            System.out.println("total:" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public void add(Book book) {
        String sql = "insert into " +
                "book(BookName,AuthorName,BookNumber,BooksType,LanguageType,bookConcern,Money,remark) " +
                "values(?,?,?,?,?,?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthorName());
            ps.setInt(3, book.getBookNumber());
            ps.setString(4,book.getBooksType());
            ps.setString(5,book.getLanguageType());
            ps.setString(6,book.getBookConcern());
            ps.setDouble(7,book.getMoney());
            ps.setString(8,book.getRemark());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        String sql = "update book " +
                "set BookName= ?, AuthorName = ? , BookNumber = ?, bookType=? , LanguageType = ?," +
                " bookConcern = ?, Money=?, remark=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getAuthorName());
            ps.setInt(3, book.getBookNumber());
            ps.setString(4,book.getBooksType());
            ps.setString(5,book.getLanguageType());
            ps.setString(6,book.getBookConcern());
            ps.setDouble(7,book.getMoney());
            ps.setString(8,book.getRemark());
            ps.setInt(9,book.getId());
            //执行SQL语句
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "delete from book where id = " + id;

            s.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Book get(int id) {
        Book book = null;

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select * from book where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                book = new Book();
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                double money = rs.getDouble("Money");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setMoney(money);
                book.setRemark(remark);
                book.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> list() {
        return list(0, Short.MAX_VALUE);
    }

    @Override
    public List<Book> list(int start, int count) {
        List<Book> books = new ArrayList<>();

        String sql = "select * from book order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Book book = new Book();
//                System.out.println("bookName");
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                double money = rs.getDouble("Money");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setMoney(money);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return books;
    }
    public List<Book> queryFromName(String bookN){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where bookName like '%"+bookN+"%'";
        Book book;
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
//                System.out.println("bookName");
                book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                double money = rs.getDouble("Money");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setMoney(money);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> queryFromId(String no){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where id like '%"+no+"%'";
        Book book;
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
//                System.out.println("id");
                book = new Book();

                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                double money = rs.getDouble("Money");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setMoney(money);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> queryFromAuthorName(String authorN){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where authorName like '%"+authorN+"%'";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
//                System.out.println("authorName");
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                double money = rs.getDouble("Money");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setMoney(money);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> queryFromBooksType(String booksTy){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where booksType like '%"+booksTy+"%'";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
//                System.out.println("booksType");
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                double money = rs.getDouble("Money");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setMoney(money);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> queryFromLanguageType(String languageTy){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where languageType like '%"+languageTy+"%'";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
//                System.out.println("languageType");
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                double money = rs.getDouble("Money");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setMoney(money);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    public List<Book> queryFromBookConcern(String bookCon){
        List<Book> books = new ArrayList<>();
        String sql = "select * from book where bookConcern like '%"+bookCon+"%'";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
//                System.out.println("bookConcern");
                Book book = new Book();
                int id = rs.getInt("id");
                String bookName = rs.getString("bookName");
                String authorName = rs.getString("authorName");
                int bookNumber = rs.getInt("BookNumber");
                String booksType = rs.getString("BooksType");
                String languageType = rs.getString("LanguageType");
                String bookConcern = rs.getString("BookConcern");
                double money = rs.getDouble("Money");
                String remark = rs.getString("Remark");
                book.setBookName(bookName);
                book.setAuthorName(authorName);
                book.setBookNumber(bookNumber);
                book.setBooksType(booksType);
                book.setLanguageType(languageType);
                book.setBookConcern(bookConcern);
                book.setMoney(money);
                book.setRemark(remark);
                book.setId(id);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
