package com.baizhi.test;

import com.alibaba.fastjson.JSONObject;
import io.goeasy.GoEasy;
import org.junit.Test;

import java.util.Random;

public class TestGoEasy {
    @Test
    public void test1(){
        Random random = new Random();
        int[] rm = {random.nextInt(10),random.nextInt(5),random.nextInt(20),random.nextInt(30),random.nextInt(60)};
        JSONObject object = new JSONObject();
        object.put("data",rm);
        //转成json串
        String jsonString = object.toJSONString();
        //实时更新数据    采用服务器发送请求
        GoEasy goEasy = new GoEasy("http:// rest-hangzhou.goeasy.io", "BC-2460f93c17514ad18d20be7572c108c0");
        //将数据放入管道中
        goEasy.publish("my_channel", jsonString);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
