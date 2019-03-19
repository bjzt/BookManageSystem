package com.BookManageSystem.entity;

import java.util.Objects;

public class Book {
    private int id;
    private String bookName;
    private String authorName;
    private int bookNumber;//图书库存
    private String booksType;
    private String languageType;
    private String bookConcern;
    private double money;
    private String remark;//备注

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id;
    }

//    @Override
//    public int hashCode() {
//
//        return Objects.hash(id);
//    }

    public Book(){}
    public Book(String bookName,int bookNumber,String booksValue,String languageType,String bookConcern){
        setBookName(bookName);
        setBookNumber(bookNumber);
        setBooksType(booksValue);
        setLanguageType(languageType);
        setBookConcern(bookConcern);
    }
    public Book(String bookName,int bookNumber,String booksValue,String languageType,String bookConcern,String remark){
        this(bookName,bookNumber,booksValue,languageType,bookConcern);
        setRemark(remark);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getBooksType() {
        return booksType;
    }

    public void setBooksType(String booksType) {
        this.booksType = booksType;
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public String getBookConcern() {
        return bookConcern;
    }

    public void setBookConcern(String bookConcern) {
        this.bookConcern = bookConcern;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    @Override
    public String toString(){
        return "书名："+this.bookName+"，作者："+this.authorName+",数据类型："+this.booksType+",库存："+this.bookNumber
                +",定价："+this.money+",出版社："+this.bookConcern;
    }
}
