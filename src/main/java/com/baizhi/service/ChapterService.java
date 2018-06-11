package com.baizhi.service;

import com.baizhi.entity.Chapter;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ChapterService {
    public void insertChapter(MultipartFile addFile, String albumId, HttpServletRequest request);

}
