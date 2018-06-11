package com.baizhi.test;

import com.baizhi.dao.*;
import com.baizhi.entity.*;
import com.baizhi.service.ArroundService;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MenuTest {
    @Test
    public void test1() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        MenuDAO dao = (MenuDAO) ctx.getBean("menuDAO");
        List<Menu> menu = dao.queryFirstMenu();
        System.out.println(menu);
    }

    @Test
    public void test2() {
        for (int i = 0; i < 10; i++) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println(uuid);
        }
    }

    @Test
    public void test3() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDAO dao = (UserDAO) ctx.getBean("userDAO");
        User user = dao.queryUserByPhoneNum("18295511893");
        System.out.println(user);

    }

    @Test
    public void test4() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AdminDAO dao = (AdminDAO) ctx.getBean("adminDAO");
        Admin admin = dao.queryByUsername("xuhui");
        System.out.println(admin);
    }

    @Test
    public void test5() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PicturesDAO dao = (PicturesDAO) ctx.getBean("picturesDAO");
        List<Pictures> pictures = dao.queryAllPictures(1, 3);
        System.out.println(pictures);
    }

    @Test
    public void test6() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PicturesDAO dao = (PicturesDAO) ctx.getBean("picturesDAO");
        dao.modifyStatus(1, "n");
    }

    @Test
    public void test7() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        PicturesDAO dao = (PicturesDAO) ctx.getBean("picturesDAO");
        Pictures pt = new Pictures();
        pt.setTitle("佛说");
        pt.setImgPath("/img/5.gif");
        pt.setIns("f放下屠刀，立地成佛");
        pt.setSta("y");
        dao.save(pt);
    }

    @Test
    public void test8() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AlbumDAO dao = (AlbumDAO) ctx.getBean("albumDAO");
        List<Album> albums = dao.queryAll(0, 5);
        System.out.println(albums);
    }

    @Test
    public void test9() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDAO dao = (UserDAO) ctx.getBean("userDAO");
        List<UserMap> m = dao.queryAllUser("m");
        System.out.println(m);
    }

    @Test
    public void test10() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDAO dao = (UserDAO) ctx.getBean("userDAO");
        System.out.println(dao.queryUser1());
        System.out.println(dao.queryUser2());
        System.out.println(dao.queryUser3());
    }

    // xls数据导入
    @Test
    public void importFile() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDAO dao = (UserDAO) ctx.getBean("userDAO");
        List<UserMap> list = dao.queryAllUser("m");

        // 导出数据
        // 获取工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //获取工作表  并确定表名
        HSSFSheet sheet = workbook.createSheet("用户信息");
        //创建起始行/标题行  参数：起始行下标
        HSSFRow row = sheet.createRow(0);
        String[] cellTitle = {"编号", "姓名", "sex", "注册时期"};
        //创建单元格
        HSSFCell cell = null;
        for (int i = 0; i < cellTitle.length; i++) {
            String s = cellTitle[i];
            //创建单元格 从下表零开始
            cell = row.createCell(i);
            cell.setCellValue(s);
        }
        //处理日期格式
        //创建日期格式对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //获取日期格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        cellStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd"));

        //处理数据行
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getProvince());
            row1.createCell(1).setCellValue(list.get(i).getCount());
            row1.createCell(2).setCellValue("男" + i);
            HSSFCell cell1 = row1.createCell(3);
            cell1.setCellValue(new Date());
            //设置格式
            sheet.setColumnWidth(3, 13 * 256);
            cell1.setCellStyle(cellStyle);
        }
        //用流写出去
        try {
            workbook.write(new File("e:/用户.xls"));
            //关闭资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //导入xls中的数据
    @Test
    public void InsertPoi() {
        //获取数据Excel表
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("e:/用户.xls"));
            //获取工作表
            HSSFSheet sheet = workbook.getSheet("用户信息");
            //声明行对象
            HSSFRow row = null;
            for (int i = 0; i < sheet.getLastRowNum(); i++) {
                 row = sheet.getRow(i+1);
                System.out.println(row.getCell(0).getStringCellValue()+" " +
                        ""+row.getCell(1).getStringCellValue()+""+
                        row.getCell(2).getNumericCellValue()+""+row.getCell(3).getDateCellValue()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test11() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        UserDAO dao = (UserDAO) ctx.getBean("userDAO");
        List<User> userList = dao.queryAll();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            System.out.println(user);

        }
    }
}