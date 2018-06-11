package com.baizhi.controller;

import com.baizhi.aop.LogAnnotation;
import com.baizhi.entity.Album;
import com.baizhi.entity.FileName;
import com.baizhi.service.AlbumService;
import com.baizhi.utils.UploadFileUtils;
import org.apache.commons.fileupload.UploadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //查询所有的专辑和章节；
    @RequestMapping("queryAll")
    @ResponseBody
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        Integer count = albumService.queryCount();
        List<Album> albums = albumService.queryAll(page, rows);
        map.put("total",count);
        map.put("rows",albums);
        return map;
    }
    //添加专辑
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public void save(MultipartFile image, Album album, HttpServletRequest request){
        //获取上传的url   newName
        FileName fileName= UploadFileUtils.uploadFile(image,UploadFileUtils.PHOTO,request);
        String url = fileName.getUrl();
        String newName = fileName.getNewName();
        album.setCoverImg(url+"/"+newName);
        albumService.saveAlbum(album);
    }
    /*添加章节*/
    @RequestMapping(value="saveChapter",method = RequestMethod.POST)
    public void saveChapter(MultipartFile addFile,String id){
    }
}
