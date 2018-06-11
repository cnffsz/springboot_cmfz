package com.baizhi.controller;

import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("detail")
public class DetailController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("wen")
    @ResponseBody
    public Map wen(String id,String uid){
        //查询专辑详情和专辑对应的所有章节  同一个业务
        return  albumService.query(id,uid);
    }
}
