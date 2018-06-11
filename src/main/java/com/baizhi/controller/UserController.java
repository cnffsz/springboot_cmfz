package com.baizhi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.baizhi.entity.User;
import com.baizhi.entity.UserMap;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    //查询所有男用户
    @RequestMapping(value = "queryAllMen")
    @ResponseBody
    public List<JSONObject> queryAll(String sex) {
        List<UserMap> allUser = userService.findAllUser(sex);
        ArrayList<JSONObject> objects = new ArrayList<JSONObject>();
        for (UserMap users : allUser) {
            JSONObject object = new JSONObject();
            object.put("name", users.getProvince());
            object.put("value", users.getCount());
            objects.add(object);
        }
        return objects;
    }

    //查询所有女用户
    @RequestMapping(value = "queryAllFmen")
    @ResponseBody
    public List<JSONObject> queryAllFmen(String sex) {
        List<UserMap> allUser = userService.findAllUserFmen(sex);
        List<JSONObject> objects = new ArrayList<JSONObject>();
        for (UserMap users : allUser) {
            JSONObject object = new JSONObject();
            object.put("name", users.getProvince());
            object.put("value", users.getCount());
            objects.add(object);
        }
        return objects;
    }

    //查询一周、两周、三周内注册的用户
    @RequestMapping("queryDay")
    @ResponseBody
    public Map<String, Object> queryDay() {
        int day1 = userService.findUser1();
        int day2 = userService.findUser2();
        int day3 = userService.findUser3();
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<String> list2 = new ArrayList<String>();
        Map<String, Object> map = new HashMap<String, Object>();
        list1.add(day1);
        list1.add(day2);
        list1.add(day3);
        list2.add("一周");
        list2.add("两周");
        list2.add("三周");
        map.put("intervals", list2);
        map.put("counts", list1);
        return map;
    }

    //用户登录接口
    //@RequestMapping(value="login",method = RequestMethod.POST)
    @RequestMapping(value = "login")
    @ResponseBody
    public JSONObject login(String phone, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            if (phone != null) {
                User user = userService.queryUserByPhone(phone, password);
                jsonObject.put("id", user.getId());
                jsonObject.put("phone", user.getPhoneNum());
                jsonObject.put("password", user.getPassword());
                jsonObject.put("salt", user.getSalt());
                jsonObject.put("headPic", user.getHeadPic());
                jsonObject.put("username", user.getUsername());
                jsonObject.put("sex", user.getSex());
                jsonObject.put("province", user.getProvince());
                jsonObject.put("city", user.getCity());
                jsonObject.put("sign", user.getSign());
                jsonObject.put("status", user.getStatus());
                jsonObject.put("dhamaName", user.getDhamaName());
            } else {
                jsonObject.put("error", "-200");
                jsonObject.put("errmsg", "用户名不能为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("error", "-200");
            jsonObject.put("errmsg", "密码错误");
        }
        return jsonObject;
    }

    //注册用户
    @RequestMapping("regist")
    @ResponseBody
    public Map<String, String> register(String phone, String password) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            User user = userService.register(phone, password);
            map.put("phone", user.getPhoneNum());
            map.put("password", user.getPassword());
            map.put("uid", user.getId());
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "-200");
            map.put("error_msg", "该手机号已存在");
        }
        return map;
    }

    //修改
    @RequestMapping(value = "modify")
    @ResponseBody
    public JSONObject modify(User user) {    //  上传文件  MultipartFile photo
        JSONObject jsonObject = new JSONObject();
        try {
            User modifyUser = userService.modify(user);
            jsonObject.put("id", modifyUser.getId());
            jsonObject.put("phone", modifyUser.getPhoneNum());
            jsonObject.put("password", modifyUser.getPassword());
            jsonObject.put("salt", modifyUser.getSalt());
            jsonObject.put("headPic", modifyUser.getHeadPic());
            jsonObject.put("username", modifyUser.getUsername());
            jsonObject.put("sex", modifyUser.getSex());
            jsonObject.put("province", modifyUser.getProvince());
            jsonObject.put("city", modifyUser.getCity());
            jsonObject.put("sign", modifyUser.getSign());
            jsonObject.put("status", modifyUser.getStatus());
            jsonObject.put("dhamaName", modifyUser.getDhamaName());
        } catch (Exception e) {
            e.printStackTrace();
            jsonObject.put("error", "200");
            jsonObject.put("error_msg", "该手机号已经存在");
        }
        return jsonObject;
    }
    //获取短信验证码接口
    @RequestMapping("obtain")
    public void obtain(String phone) throws ClientException {
        //设置超时时间-可自行调整
        System.setProperty("sun.net.client.defaultConnectTimeout", "9000");
        System.setProperty("sun.net.client.defaultReadTimeout", "9000");
        //初始化ascClient需要的几个参数
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        //替换成你的AK
        final String accessKeyId = "LTAI6dYcUSZfw8bu";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "UUbqWCdLjjygbIzB8z52eIWWtcEbxZ";//你的accessKeySecret，参考本文档步骤2
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为00+国际区号+号码，如“0085200000000”
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName("我是xh");
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode("SMS_136745107");
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        Random random = new Random();
        String codes = "1234567890";
        String pcode="";
        for (int i=0;i<6;i++){
            int i1 = random.nextInt(codes.length());
            char c = codes.charAt(i1);
            pcode += c;
        }
        /*
         *   到jar包  commons-pool2jar   key - value 形式存储
             Jedis jedis = new Jedis("host","port");
             jedis.select(0);
             设置过期时长 60 秒
             jedis.setex("code",60,pcode)
        * */
        request.setTemplateParam("{\"code\":\""+pcode+"\"}");
        //可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode(pcode);
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //请求失败这里会抛ClientException异常
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
        //请求成功
        }
    }
    // 短信验证
    @RequestMapping("check")
    @ResponseBody
    public JSONObject check(String phone,String code){
        JSONObject jsonObject = new JSONObject();
        /**
            取出 redis 数据库中存储的短信验证码进行比对
            Jedis jedis = new Jedis("host","port");
            String c = jedis.get("code");*/
            String c = "";
        if(code.equals(c)){
            jsonObject.put("result","success");
        }else{
            jsonObject.put("result","file");
        }
        return jsonObject;
    }
    //查询获取会员列表
    @RequestMapping(value = "member")
    @ResponseBody
    public List<User> queryMember(String id){
        return userService.queryMember(id);
    }
}
