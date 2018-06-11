package com.baizhi.dao;

import com.baizhi.entity.User;
import com.baizhi.entity.UserMap;

import java.util.List;

public interface UserDAO {

    //随机查询除过自己的其他五个会员
    public List<User> queryMember(String id);

    //根据id查询信息
    User queryById(String id);

    //修改用户信息
    public void modify(User user);

    // 注册用户
    public void registerUser(User user);

    //登录
    public User queryUserByPhoneNum(String phoneNum);

    //查询所有男用户
    public List<UserMap> queryAllUser(String sex);

    //查询所有女用户
    public List<UserMap> queryAllUserF(String sex);

    //查询一周内的数据
    public int queryUser1();

    //查询2周内的数据
    public int queryUser2();
    
    //查询三周内的数据
    public int queryUser3();

    //查询所有用户
    public List<User> queryAll();

    //批量插入用户
    public void save(List<User> list);

}
