package com.baizhi.serviceimpl;

import com.baizhi.aop.LogAnnotation;
import com.baizhi.dao.PicturesDAO;
import com.baizhi.entity.Pictures;
import com.baizhi.service.PicturesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class PicturesServiceImpl implements PicturesService {
    @Autowired
    private PicturesDAO picturesDAO;
    public List<Pictures> queryAll(int page,int rows) {
        /*计算起始条数结束条数*/
        int start = (page-1)*rows;

      return  picturesDAO.queryAllPictures(start,rows);
    }
    public Integer queryCount (){
        return picturesDAO.queryCount();
    }
    //修改
    @LogAnnotation(name="修改轮播图状态")
    public void modifyStatus(Integer id, String sta) {
        picturesDAO.modifyStatus(id,sta);
    }
    //删除
    @LogAnnotation(name="删除轮播图")
    public void removeRow(Integer id){
        picturesDAO.reomveRow(id);
    }

    public void save(Pictures pictures) {
        picturesDAO.save(pictures);
    }

}
