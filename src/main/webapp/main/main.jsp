<%@ page language="java" contentType="text/html;charset=utf-8"  isELIgnored="false"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>持名法州主页</title>
<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">   
<link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
<link rel="stylesheet" type="text/css" href="../themes/icon.css">
<script type="text/javascript" src="../js/jquery.min.js"></script>
<script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../js/datagrid-detailview.js"></script>
<script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/js/china.js"></script>
<script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	<!--菜单处理-->
    $(function(){
       $.ajax({
            type:"post",
            url:"${pageContext.request.contextPath}/menu/query",
            dataTyp:"json",
            success:function(date){
                /*返回值date 是个list集合 采用$.each遍历 index遍历后的下标  first遍历后的数据*/
                $.each(date,function (index,first) {
                    var c="";
                    $.each(first.seconds,function(index,second){
                        console.log(second.title)
                        c += "<p style='text-align: center'><a href='##' class='easyui-linkbutton' data-options=\"iconCls:'"+second.iconCls+"'\" style='width: 100%' onclick=\"fun1('" + second.title + "','" + second.url + "','" + second.iconCls + "')\">"+second.title+"</a><p>"
                    });
                    $('#aa').accordion('add', {
                        title: first.title,
                        content: c,
                        selected: false,
                        iconCls:first.iconCls
                    });
                })
           }
       })
        //退出
        $('#exit').bind('click', function(){
            alert('easyui');
        });
    });
	/*初始化选项卡*/
	function fun1(title,url,iconCls ) {
	    console.log(url)
        var exists= $("#tt").tabs('exists',title)
        // 添加一个未选中状态的选项卡面板
        if(exists){
            $('#tt').tabs('select',title);
        }else{
            $('#tt').tabs('add',{
                title: title,
                selected: true,
                closable:true,
                href:"../"+url,
                iconCls:iconCls
            });
        }
    }

</script>

</head>
<body class="easyui-layout">   
    <div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    	<div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px" >持名法州后台管理系统</div>
    	<div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:${sessionScope.admin1.username} &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/menu/loginOut" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a></div>
    </div>   
    <div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    	<div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体" >&copy;百知教育 htf@zparkhr.com.cn</div>
    </div>   
       
    <div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    	<div id="aa" class="easyui-accordion" data-options="fit:true">
    		
		</div>  
    </div>   
    <div data-options="region:'center'">
    	<div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
		</div>  
    </div>   
</body> 
</html>