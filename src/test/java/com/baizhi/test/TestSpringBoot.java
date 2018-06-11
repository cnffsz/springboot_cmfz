package com.baizhi.test;

import com.baizhi.AppSpringBoot;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppSpringBoot.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestSpringBoot {
    @Autowired
    private UserDAO userDAO;
    @Test
    public void test1(){
        Logger logger = LoggerFactory.getLogger(TestSpringBoot.class);
        List<User> users = userDAO.queryAll();
        logger.error("查询所有",users);
        System.out.println(users);
    }
}
