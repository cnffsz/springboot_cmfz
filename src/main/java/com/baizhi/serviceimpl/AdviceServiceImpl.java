package com.baizhi.serviceimpl;

import com.baizhi.dao.AdviceDAO;
import com.baizhi.entity.Advice;
import com.baizhi.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdviceServiceImpl implements AdviceService {
    @Autowired
    private AdviceDAO adviceDAO;

    @Transactional(readOnly = true)
    public List<Advice> queryALl(Integer page, Integer rows) {
        int start = (page-1)*rows;
        List<Advice> advice = adviceDAO.queryAll(start, rows);
        return advice;
    }

    public int queryCount() {
        return adviceDAO.queryCount();
    }
}