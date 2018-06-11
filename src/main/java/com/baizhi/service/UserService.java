package com.baizhi.service;

import com.baizhi.entity.User;
import com.baizhi.entity.UserMap;

import java.util.List;

public interface UserService {

    //随机查询五个道友  除过自己
    public List<User> queryMember(String id);

    //修改用户信息
    public User modify(User user);

    //注册用户
    public User register(String phone,String password);

    public User queryUserByPhone(String phone,String password);

    //查询所有性别为男的用户
    public List<UserMap> findAllUser(String sex);

    //查询所有性为女的用户
    public List<UserMap> findAllUserFmen(String sex);

    //查询一周内的注册量
    public int findUser1();

    //查询两周内的注册量
    public int findUser2();

    //查询三周内的注册量
    public int findUser3();

    //查询所有
    public List<User> findAll();

    //批量插入
    public void insert(List<User> list);
}
