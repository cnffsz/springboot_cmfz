package com.baizhi.dao;

import com.baizhi.entity.Pictures;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PicturesDAO {
    //总页条数
    Integer queryCount();

    //分页查询
    List<Pictures> queryAllPictures(@Param("start") int start, @Param("end") int end);

    //修改数据
    public void modifyStatus(@Param("id") Integer id, @Param("sta") String sta);

    //删除数据
    public void reomveRow(Integer id);

    //添加
    public void save(Pictures pictures);

    //查询所有活跃状态的图片
    public List<Pictures> queryAll();
}
