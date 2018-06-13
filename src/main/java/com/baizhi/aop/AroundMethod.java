package com.baizhi.aop;

import com.baizhi.dao.AdviceDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
@Aspect
@Configuration
public class AroundMethod{
    //获取日志对象  logback
    Logger logger = LoggerFactory.getLogger(AroundMethod.class);
    @Autowired
    private AdviceDAO adviceDAO;
    @Pointcut("@annotation(com.baizhi.aop.LogAnnotation)")
    public void point(){}
    @Around(value = "point()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //什么人
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();
        Admin admin = (Admin) session.getAttribute("admin1");
        System.out.println(String.valueOf(admin));
        //什么时间
        Date date = new Date();
        //干了什么事   基于注解的环绕通知
        // 获得目标对象
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获得目标方法
        Method method = signature.getMethod();
        //获取该方法的注解
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        String name = annotation.name();
        logger.error(name);
        //执行结果
        Object proceed= null;
        boolean flag = false;
        try {
            //原始方法返回值
            proceed = proceedingJoinPoint.proceed();
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
