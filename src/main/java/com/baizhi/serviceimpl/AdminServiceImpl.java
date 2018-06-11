package com.baizhi.serviceimpl;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDAO adminDAO;
    @Transactional(readOnly = true)
    public Admin findByAdmin(Admin admin) {
        try {
            Admin admin1 = adminDAO.queryByUsername(admin.getUsername());
            if (admin1==null) throw new RuntimeException("用户名不存在");
            if (!admin.getPassword().equals(admin1.getPassword())) throw new RuntimeException("密码错误");
            return admin1;
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
