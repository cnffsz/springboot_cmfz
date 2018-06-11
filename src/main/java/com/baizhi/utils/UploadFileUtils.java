package com.baizhi.utils;

import com.baizhi.entity.FileName;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class UploadFileUtils {
    //定义一个标记     用来判断是音频还是图片
    public final static String FILE="FILE";
    public static final String PHOTO="PHOTO";

    public static FileName uploadFile(MultipartFile multipartFile, String flag, HttpServletRequest request){
        //获取当前项目文件路径
        String realPath = request.getSession(true).getServletContext().getRealPath("/");
        //获取上一级路径 webapps
        File file = new File(realPath);
        String fileParent = file.getParent();

        //判断是音频还是图片 并创建相应的文件夹
        File uploadPath;
        String url;
        if (flag.equals(FILE)){
            // 将文件路径转为文件对象
            uploadPath=new File(fileParent+"/audio");
            if(!uploadPath.isDirectory()){
                uploadPath.mkdir();
            }
            url="/audio";
        }else{
            uploadPath=new File(fileParent+"/upload");
            if(!uploadPath.isDirectory()){
                uploadPath.mkdir();
            }
            url="/upload";
        }
        //获取原始文件
        String oldName = multipartFile.getOriginalFilename();
        //获取后缀
        String extension = FilenameUtils.getExtension(oldName);
        //防止文件覆盖  创建新名字
        UUID uuid = UUID.randomUUID();
        String newName = uuid.toString()+"."+extension;
        //上传
        try {
            multipartFile.transferTo(new File(uploadPath.getPath(),newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileName fileName = new FileName();
        fileName.setOldName(oldName);
        fileName.setUrl(url);
        fileName.setNewName(newName);
        return fileName;
    }
}
