package com.baizhi.dao;

import com.baizhi.entity.Advice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdviceDAO {
    int deleteByPrimaryKey(Integer id);

    int insert(Advice record);

    int insertSelective(Advice record);

    Advice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advice record);

    int updateByPrimaryKey(Advice record);

    List<Advice> queryAll(@Param("start")Integer start,@Param("rows")Integer rows);

    int queryCount();
}