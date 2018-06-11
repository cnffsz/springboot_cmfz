package com.baizhi.serviceimpl;

import com.baizhi.aop.LogAnnotation;
import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Chapter;
import com.baizhi.entity.FileName;
import com.baizhi.service.ChapterService;
import com.baizhi.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;
    @Autowired
    private AlbumDAO albumDAO;
    @LogAnnotation(name="添加章节")
    public void insertChapter(MultipartFile addFile, String albumId, HttpServletRequest request) {
        Chapter chapter = new Chapter();
        FileName fileName = UploadFileUtils.uploadFile(addFile, UploadFileUtils.FILE, request);
        String chapterId = UUID.randomUUID().toString();
        chapter.setId(chapterId);
        chapter.setTitle(fileName.getOldName());
        chapter.setDuration("3");
        chapter.setDownPath(fileName.getUrl()+"/"+fileName.getNewName());
        chapter.setAlbumId(albumId);
        String size = ((double)addFile.getSize()/1024000)+"";
        chapter.setMusicSize(size);
        chapterDAO.insertChapter(chapter);
        //改变集数
        String s = albumDAO.queryCountById(albumId);
        Integer count = Integer.parseInt(s);
        String count1 = (count+1)+"";
        albumDAO.updateCount(albumId,count1);
    }
}
