package com.baizhi.dao;

import com.baizhi.entity.Text;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface TextDAO {
    //查询最新的两篇文章
    List<Text> queryNewText(String greatId);

    //查询某用户对应上师所有的文章
    List<Text> queryByGreatId(String greatId);

    //查询除了该上师所有上师的文章
    List<Text> queryAll(String gretatId);
}
