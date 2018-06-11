package com.baizhi.serviceimpl;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.entity.UserMap;
import com.baizhi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional(readOnly = true)
    public List<User> queryMember(String id) {
        return userDAO.queryMember(id);
    }

    //用户修改信息
    public User modify(User user) {
        //修改
        userDAO.modify(user);
        //查询
        return userDAO.queryById(user.getId());
    }

    //用户注册
    public User register(String phone, String password) {
        //判断用户是否存在
        User user = userDAO.queryUserByPhoneNum(phone);
        try {
            if (user != null) throw new RuntimeException("用户名已存在，请直接登录");
            //加盐  加密
            Random random = new Random();
            String salt = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890<>/,.?";
            String ss = "";
            for (int i = 0; i < 4; i++) {
                int i1 = random.nextInt(salt.length());
                char c = salt.charAt(i1);
                ss += c;
            }
            String password1 = password + ss;
            String pass = DigestUtils.md5Hex(password1);
            User user1 = new User();
            user1.setSalt(ss);
            user1.setPassword(pass);
            user1.setPhoneNum(phone);
            String s = UUID.randomUUID().toString();
            String uid = s.replace("-", "");
            user1.setId(uid);
            userDAO.registerUser(user1);
            return user1;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //用户登录模块
    @Transactional(readOnly = true)
    public User queryUserByPhone(String phone, String password) {
        try {
            User user1 = userDAO.queryUserByPhoneNum(phone);
            if (user1 == null) throw new RuntimeException("用户名不存在");
            if (!password.equals(user1.getPassword())) throw new RuntimeException("密码错误");
            return user1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    //查询所有性别为男
    @Transactional(readOnly = true)
    public List<UserMap> findAllUser(String sex) {
        return userDAO.queryAllUser(sex);
    }

    @Transactional(readOnly = true)
    public List<UserMap> findAllUserFmen(String sex) {
        return userDAO.queryAllUserF(sex);
    }

    @Transactional(readOnly = true)
    public int findUser1() {
        return userDAO.queryUser1();
    }

    @Transactional(readOnly = true)
    public int findUser2() {
        return userDAO.queryUser2();
    }

    @Transactional(readOnly = true)
    public int findUser3() {
        return userDAO.queryUser3();
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userDAO.queryAll();
    }

    public void insert(List<User> list) {
        userDAO.save(list);
    }
}
