package com.BookManageSystem.dao;

import com.BookManageSystem.entity.user.Manager;
import com.BookManageSystem.entity.user.SuperManager;
import com.BookManageSystem.entity.user.User;
import com.BookManageSystem.entity.user.VIPUser;
import com.BookManageSystem.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {

    @Override
    public int getTotal() {
        int total = 0;
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement();) {

            String sql = "select count(*) from user";

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
    public void add(User user) {
        String sql = "insert into user(username,password,power) values(?,?,?)";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getPower());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        String sql = "update user set username=?, password=? , power=? where id = ?";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getPower());
            ps.setInt(4,user.getId());
            //执行SQL语句
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(int id) {
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "delete from user where id = " + id;
            s.execute(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User get(int id) {
        User user = null;

        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {

            String sql = "select * from user where id = " + id;

            ResultSet rs = s.executeQuery(sql);

            if (rs.next()) {
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                int power = rs.getInt("power");

                user = this.getUser(power);
                user.setUserName(userName);
                user.setPassword(password);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> list() {
        return list(0, Short.MAX_VALUE);
    }

    @Override
    public List<User> list(int start, int count) {
        List<User> users = new ArrayList<>();

        String sql = "select * from user order by id desc limit ?,? ";

        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User user;
                int id = rs.getInt("id");
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                int power = rs.getInt("power");
                user = this.getUser(power);
                user.setUserName(userName);
                user.setPassword(password);
                user.setId(id);
                users.add(user);
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
        return users;
    }
    private User getUser(int power){
        User user = null;
        switch (power){
            case User.USER: user = new User(); break;
            case User.VIPUSER: user = new VIPUser(); break;
            case User.MANAGER: user = new Manager(); break;
            case User.SUPERMANAGER: user = new SuperManager(); break;
        }
        return user;
    }
}
