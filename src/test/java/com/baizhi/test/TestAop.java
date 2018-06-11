package com.baizhi.test;

import com.baizhi.aop.LogAnnotation;

public class TestAop {
   @LogAnnotation(name="我是自定义注解")
   public static void print(){
       System.out.println("你好");
       System.out.println(LogAnnotation.class.getName());
       LogAnnotation annotation = (LogAnnotation) LogAnnotation.class.getAnnotation(LogAnnotation.class);
       //获取LogAnnotation中的内容  如果没有返回空
    if(annotation != null){
        String name = annotation.name();
        System.out.println(name);
    }else {
        System.out.println("dddd");
    }
   }

}
