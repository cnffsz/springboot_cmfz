package com.baizhi.controller;

import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
     @Autowired
    private ChapterService chapterService;
    //添加章节
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public void addChapter(MultipartFile addFile, String albumId, HttpServletRequest request){
        chapterService.insertChapter(addFile,albumId,request);
    }
    //文件下载
    @RequestMapping(value="down",method = RequestMethod.GET)
    public void downFile(String url, String title , HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取当前存储的位置
        String realPath = request.getSession().getServletContext().getRealPath("/");
        //获取当前路径的上一级目录
        File file = new File(realPath);
        String parent = file.getParent();
        String filePath = parent+url;
        File downPath = new File(filePath);
        //设置响应类型
        String fileName=null;
        try {
            fileName= new String(title.getBytes("UTF-8"),"ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //告诉浏览器是音频文件
        response.setHeader("content-disposition", "attachment;fileName=" + fileName);
        response.setContentType("audio/mpeg");
        //下载  告诉去哪找
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(FileUtils.readFileToByteArray(downPath));
        System.out.println(title);

    }

}
