package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    public Integer queryCount();

    public List<Album> queryAll(Integer page, Integer rows);

    public void saveAlbum(Album album);

    //查询详细信息和对应专辑
    public Map query(String id,String uid);
}
