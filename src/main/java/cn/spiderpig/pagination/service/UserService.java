package cn.spiderpig.pagination.service;

import java.util.List;

import cn.spiderpig.pagination.dao.UserDao;
import cn.spiderpig.pagination.entity.Result;
import cn.spiderpig.pagination.entity.User;

public class UserService {

    UserDao userDao = new UserDao();

    public Result addUser(String username, String password) {

        return userDao.addUser(username, password);

    }

    public Result<List> getAll() {
        return userDao.getAll();
    }

    public Result findUser(Integer currentPage, Integer pageSize) {
        // TODO Auto-generated method stub
        return userDao.findUser(currentPage,pageSize);
    }

}
