package com.baizhi.controller;

import com.baizhi.entity.Pictures;
import com.baizhi.service.PicturesService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("pictures")
public class PictController {
    Logger logger = LoggerFactory.getLogger(PictController.class);
    @Autowired
    private PicturesService picturesService;
    @RequestMapping(value="/queryAll",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryAll(int page,int rows){
        Map<String, Object> map = new HashMap<String, Object>();
        //总条数
        Integer count = picturesService.queryCount();
        List<Pictures> pictures = picturesService.queryAll(page, rows);
        logger.error("查询轮播图");
        map.put("total",count);
        map.put("rows",pictures);
        return map;
    }
    //修改
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public void modify(Integer id,String sta){
        picturesService.modifyStatus(id,sta);
    }
    //删除
    @RequestMapping(value="remove",method = RequestMethod.POST)
    public void removeRow(Integer id){
        picturesService.removeRow(id);
    }
    //添加
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public void  save(MultipartFile image, Pictures pictures, HttpServletRequest request){
       //获取当前项目的路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File file = new File(realPath);
        //获取当前项目的上一级目录 wepapps
        String fileParent = file.getParent();
        //获取文件上传的路径
        File uploadePath = new File(fileParent + "/uploade");
        //判断该文件加是否存在  如果不存在新建一个
        if(!uploadePath.isDirectory()){
            uploadePath.mkdir();
        }
        //获取原始文件名
        String filename = image.getOriginalFilename();
        //避免相同的文件名覆盖 搞个新名字用UUID + 源文件名后缀
        String  newFileName= UUID.randomUUID().toString()+"."+ FilenameUtils.getExtension(filename);
        try {
            image.transferTo(new File(uploadePath.getPath(),newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
       pictures.setImgPath("/uploade/"+newFileName);
        picturesService.save(pictures);

    }
}
