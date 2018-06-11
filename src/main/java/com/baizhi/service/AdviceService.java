package com.baizhi.service;

import com.baizhi.entity.Advice;

import java.util.List;

public interface AdviceService {
    public List<Advice> queryALl(Integer page,Integer rows);

    int queryCount();

}
