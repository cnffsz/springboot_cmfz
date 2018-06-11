package com.baizhi.service;

import com.baizhi.entity.Pictures;

import java.util.List;

public interface PicturesService {
    List<Pictures> queryAll(int page,int rows);
    Integer queryCount();
    public void modifyStatus(Integer id,String sta);
    //删除
    public void removeRow(Integer id);
    //添加
    public void save(Pictures pictures);
}

