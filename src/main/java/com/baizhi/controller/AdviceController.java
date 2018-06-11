package com.baizhi.controller;

import com.baizhi.entity.Advice;
import com.baizhi.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("advice")
public class AdviceController {
    @Autowired
    private AdviceService adviceService;


    @RequestMapping("query")
    @ResponseBody
    public Map queryAll(Integer page,Integer rows){
        HashMap<String, Object> map = new HashMap<String, Object>();
        int count = adviceService.queryCount();
        List<Advice> advices = adviceService.queryALl(page, rows);
        map.put("total",count);
        map.put("rows",advices);
        return map;
    }
}
