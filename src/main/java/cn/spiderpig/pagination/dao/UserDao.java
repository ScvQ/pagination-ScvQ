package cn.spiderpig.pagination.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.spiderpig.pagination.entity.Result;
import cn.spiderpig.pagination.entity.User;
import cn.spiderpig.pagination.util.DBUtil;
import cn.spiderpig.pagination.util.ResultUtil;

public class UserDao {


    public Result<List> getAll() {

        Result result = ResultUtil.error(-1, "error");
        try {
            String sql = " SELECT * FROM user ";
            ResultSet resultSet = DBUtil.getConnection().prepareStatement(sql).executeQuery();
            List<User> list = new ArrayList<User>();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }
            if (list.size() > 0) {
                result = ResultUtil.success(list);
            } else {
                return result;
            }
        } catch (SQLException e) {
            System.out.println("创建声明失败");
        }
        return result;

    }

    public Result addUser(String username, String password) {

        Result result = ResultUtil.error(-1, "error");
        try {
            String sql = " INSERT INTO user(username,password) VALUES(?,?) ";
            PreparedStatement prepareStatement = DBUtil.getConnection().prepareStatement(sql);
            prepareStatement.setString(1, username);
            prepareStatement.setString(2, password);
            if (prepareStatement.executeUpdate() == 1) {
                result = ResultUtil.success();
            }
        } catch (SQLException e) {
            System.out.println("创建声明失败");
        }
        return result;

    }

    public Result findUser(Integer currentPage, Integer pageSize) {
        
        Result result = ResultUtil.error(-1, "error");
        try {
            String sql = " SELECT * FROM user LIMIT ?,? ";
            PreparedStatement prepareStatement = DBUtil.getConnection().prepareStatement(sql);
            prepareStatement.setInt(1, pageSize*(currentPage-1));
            prepareStatement.setInt(2, pageSize);
            ResultSet resultSet = prepareStatement.executeQuery();
            List<User> list = new ArrayList<User>();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                list.add(user);
            }
            if (list.size() > 0) {
                result = ResultUtil.success(list);
            } else {
                return result;
            }
        } catch (SQLException e) {
            System.out.println("创建声明失败");
        }
        return result;
    }


}
