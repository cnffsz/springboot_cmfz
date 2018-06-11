package com.baizhi.serviceimpl;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.ChapterDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private ChapterDAO chapterDAO;


    public Integer queryCount() {
        return albumDAO.queryCount();
    }

    //查询所有的专辑和章节
    public List<Album> queryAll(Integer page, Integer rows) {
        int start = (page - 1) * rows;
        return albumDAO.queryAll(start, rows);
    }

    public void saveAlbum(Album album) {
        String id = UUID.randomUUID().toString();
        album.setId(id);
        albumDAO.insertAlbum(album);
    }
    //查询专辑详情和对应的所有章节
    public Map query(String id, String uid) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //查询详情
            Album album = albumDAO.queryById(id);
            //查询该专辑对应的所有章节
            List<Chapter> chapters = chapterDAO.queryByAlbumId(id);

            map.put("introduction",album);
            map.put("list",chapters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
