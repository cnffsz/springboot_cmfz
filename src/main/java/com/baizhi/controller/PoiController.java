package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.ArroundService;
import com.baizhi.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("poiUser")
public class PoiController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArroundService arroundService;

    @RequestMapping("around")
    public void around(){
     arroundService.save();
    }

    @RequestMapping("export")
    @ResponseBody
    public void export(String titles, String files, HttpServletRequest request){
        String[] title = titles.split(",");
        String[] EFiles = files.split(",");
        //创建工作簿  设置格式
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建表
        HSSFSheet sheet = workbook.createSheet("用户信息表");
        //设置格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        //创建单元格
        for (int i = 0; i < title.length; i++) {
            String s = title[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(s);
        }
        //查询所有的用户
        List<User> userList = userService.findAll();
        //创建数据行
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            HSSFRow sheetRow = sheet.createRow(i + 1);
            //创建单元格
            for (int j = 0; j < EFiles.length; j++) {
                String eFile = EFiles[j];
                HSSFCell cell = sheetRow.createCell(j);
                //填充单元格   EFiles 中的属性
                // 获取 类对象
                Class<? extends User> userClass = user.getClass();
                //拼接获取get 方法的 方法名
                String method = "get"+eFile.substring(0,1).toUpperCase()+eFile.substring(1);
                //反射获取方法名并调用方法
                try {
                    Object invoke = userClass.getDeclaredMethod(method, null).invoke(user, null);
                    //判断是否是日期
                    if(invoke instanceof Date){
                        //设置表格的格式
                        sheet.setColumnWidth(j,20*256);
                        cell.setCellStyle(cellStyle);
                        //转为日期
                        cell.setCellValue((Date) invoke);
                    }else{
                        cell.setCellValue(invoke.toString());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        //输出表格
        try {
            workbook.write(new File("e:/用户信息统计表格.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("importFile")
    public void importFle(){
        //获取工作簿
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("e:/用户信息统计表格.xls"));
            List<User> list = new ArrayList<User>();
            //获取表
            HSSFSheet sheet = workbook.getSheet("用户信息表");
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                User user = new User();
                HSSFRow row = sheet.getRow(i);

                String id = row.getCell(0).getStringCellValue();
                user.setId(id);
                //获取当前行的每一个单元格  并获取里面得内容
                String phoneNum = row.getCell(1).getStringCellValue();
                user.setPhoneNum(phoneNum);

                String password = row.getCell(2).getStringCellValue();
                user.setPassword(password);

                String salt = row.getCell(3).getStringCellValue();
                user.setSalt(salt);

                String dhamaName = row.getCell(4).getStringCellValue();
                user.setDhamaName(dhamaName);

                String username = row.getCell(5).getStringCellValue();
                user.setUsername(username);

                String sex = row.getCell(6).getStringCellValue();
                user.setSex(sex);

                String province = row.getCell(7).getStringCellValue();
                user.setProvince(province);

                String city = row.getCell(8).getStringCellValue();
                user.setCity(city);

                String sign = row.getCell(9).getStringCellValue();
                user.setSign(sign);

                String status = row.getCell(10).getStringCellValue();
                user.setStatus(status);

                Date date = row.getCell(11).getDateCellValue();
                /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String format = simpleDateFormat.format(date);*/
                user.setDate(date);
                list.add(user);
                System.out.println(user);
            }
            //调用批量插入的方法
            userService.insert(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
