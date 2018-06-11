package com.baizhi.serviceimpl;

import com.baizhi.dao.MenuDAO;
import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class MenuServiceImpl implements MenuService {
    //注入dao接口  接口名首字母小写 可以直接从工厂中取
    @Autowired
    private MenuDAO menuDAO;
    @Transactional(readOnly = true) //提高查询效率
    public List<Menu> queryAllFirst() {
     return menuDAO.queryFirstMenu();
    }
}
