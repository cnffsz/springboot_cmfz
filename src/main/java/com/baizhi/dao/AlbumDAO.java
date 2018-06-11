package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDAO {

    //查询所有
    List<Album> queryAll();

    //查询总条数
    public Integer queryCount();

    //分页展示所有
    public List<Album> queryAll(@Param("start") Integer start, @Param("rows") Integer rows);

    //添加专辑
    public void insertAlbum(Album album);

    //查询集数
    public String queryCountById(String id);

    //修改集数
    public void updateCount(@Param("count") String count, @Param("id") String id);

    //查询最新的六条专辑
    List<Album> queryNew();

    //查询专辑详情
    public Album queryById(String id);
}
