package com.baizhi.serviceimpl;

import com.baizhi.aop.LogAnnotation;
import com.baizhi.service.ArroundService;
import org.springframework.stereotype.Service;

@Service
public class ArroundServiceImpl implements ArroundService {
    @LogAnnotation(name = "这是一个保存操作")
    public String save() {
        System.out.println("我是 保存");
        return "arround";
    }
}
