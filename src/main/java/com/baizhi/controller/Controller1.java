package com.baizhi.controller;


import com.baizhi.entity.Admin;
import com.baizhi.entity.Menu;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.service.MenuService;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class Controller1 {
    /*菜单注入*/
    @Autowired
    private MenuService menuService;
    /*管理员注入*/
    @Autowired
    private AdminService adminService;
    /*
    *管理员登录
    * */
    @RequestMapping(value="adminLogin",method = RequestMethod.POST)
    public String adminLogin(Admin admin, HttpServletRequest request,String enCode){
        HttpSession session = request.getSession(true);
        if (session.getAttribute("code").equals(enCode)){
            try {
                Admin admin1 = adminService.findByAdmin(admin);
                session.setAttribute("admin1",admin1);
                return "redirect:/main/main.jsp";
            } catch (Exception e) {
                e.printStackTrace();
                return "redirect:/login.jsp";
            }
        }else{
            return "redirect:/login.jsp";
        }
    }
    /*
    *退出登录
    * */
    @RequestMapping("loginOut")
    public String loginOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/main/main.jsp";
    }
    /*
    *菜单查询
    */
    @RequestMapping(value="/query",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List queryAllFirst(){
        List<Menu> menuList = menuService.queryAllFirst();
        return menuList;
    }

    /*验证码*/
    @RequestMapping("sendImage")
    public void sendImage(HttpSession session,HttpServletResponse response) throws IOException {
        CreateValidateCode vCode = new CreateValidateCode();
        String code = vCode.getCode();
        session.setAttribute("code",code);
        vCode.write(response.getOutputStream());

    }
}
