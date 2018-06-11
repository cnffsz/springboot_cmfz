package com.baizhi.aop;

import com.baizhi.dao.AdviceDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Advice;
import com.baizhi.entity.User;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AroundMethod implements MethodInterceptor{
    @Autowired
    private AdviceDAO adviceDAO;

    public Object invoke(MethodInvocation invocation) throws Throwable {
        //什么人
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();
        Admin admin = (Admin) session.getAttribute("admin1");
        System.out.println(String.valueOf(admin));
        //什么时间
        Date date = new Date();
        //干了什么事   基于注解的环绕通知
        Method method = invocation.getMethod();
        // 基于反射获取类对象
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        // 参数调用
        String name = logAnnotation.name();
        System.out.println(name);
        //执行结果
        Object proceed= null;
        boolean flag = false;

        try {
            //原始方法返回值
            proceed = invocation.proceed();
            flag = true;

        } catch (Throwable throwable) {
            throwable.printStackTrace();
            flag = false;
        }
        if(flag){
            Advice advice = new Advice(null,admin.getUsername(),name, new SimpleDateFormat("yyyy-MM-dd").format(date));
            adviceDAO.insert(advice);
        }

        return proceed;
    }
}
