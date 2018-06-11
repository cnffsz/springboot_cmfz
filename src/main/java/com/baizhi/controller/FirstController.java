package com.baizhi.controller;


import com.baizhi.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("cmfz")
public class FirstController {
    @Autowired
    private FirstService firstService;

    @RequestMapping("first_page")
    @ResponseBody
    public Map firstPage(String uid,String type,String sub_type){
       return firstService.queryFirstPage(uid, type, sub_type);
    }
}
