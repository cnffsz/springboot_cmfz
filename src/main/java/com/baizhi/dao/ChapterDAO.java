package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDAO {
    //添加章节
    public void insertChapter(Chapter chapter);

    // 查询该专辑对应的所有章节
    public List<Chapter> queryByAlbumId(String albumId);

}
