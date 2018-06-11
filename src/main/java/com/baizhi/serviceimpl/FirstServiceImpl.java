package com.baizhi.serviceimpl;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.dao.PicturesDAO;
import com.baizhi.dao.TextDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Album;
import com.baizhi.entity.Pictures;
import com.baizhi.entity.Text;
import com.baizhi.entity.User;
import com.baizhi.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class FirstServiceImpl implements FirstService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PicturesDAO picturesDAO;
    @Autowired
    private AlbumDAO albumDAO;
    @Autowired
    private TextDAO textDAO;

    public Map queryFirstPage(String uid, String type, String sub_type) {
        /**
         * 1. type : all 查询首页展示的是三个模块  轮播图 最新六条专辑 用户自己上师的最新两篇文章
         * 2. type: wen  查询展示所有的专辑
         * 3. type： si   判断   1.如果是上师言教ssyj 查询该用户所对应的上师的所有文章
         *                      2.如果是显密法要 xmfy 查询除过自己上师剩余所有上师的文章
         */
        Map<String, Object> map = new HashMap<String, Object>();
        //查询该用户对应上师id
        User user = userDAO.queryById(uid);
        try {
            if (type.equals("all")) {
                //查询活跃状态的轮播图
                List<Pictures> pictures = picturesDAO.queryAll();
                //查询最新的六条专辑
                List<Album> albums = albumDAO.queryNew();
                //查询所对应的上师最新的两篇文章
                List<Text> texts = textDAO.queryNewText(user.getGreatId());
                map.put("header", pictures);
                map.put("album", albums);
                map.put("artical", texts);
            } else if (type.equals("wen")) {
                //查询所有的专辑
                List<Album> albums = albumDAO.queryAll();
                map.put("album", albums);
            } else {
                if (sub_type.equals("ssyj")) {
                    //查询该用户对应的上师的所有的文章
                    List<Text> texts = textDAO.queryByGreatId(user.getGreatId());
                    map.put("artical", texts);
                } else {
                    //查询除了该上师所有上师的文章
                    List<Text> texts = textDAO.queryAll(user.getGreatId());
                    map.put("artical", texts);
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
