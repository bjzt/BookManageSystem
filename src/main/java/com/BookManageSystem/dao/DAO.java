package com.BookManageSystem.dao;
  
import java.util.List;

public interface DAO<T>{
	//得到表数据的条数
	public int getTotal();
    //增加
    public void add(T t);
    //修改
    public void update(T t);
    //删除
    public boolean delete(int id);
    //获取
    public T get(int id);
    //查询
    public List<T> list();
    //分页查询
    public List<T> list(int start, int count);
}