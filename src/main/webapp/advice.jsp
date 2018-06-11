<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/7
  Time: 8:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="utf-8" contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">

    $('#dg').datagrid({
        url:'${pageContext.request.contextPath}/advice/query',
        fitColumns:true,
        fit:true,
        pagination:true,
        columns:[[
        {field:'id',title:'序号',width:100},
        {field:'username',title:'姓名',width:100},
        {field:'adviceName',title:'通知',width:100,align:'right'},
        {field:'date',title:'时间',width:100,align:'right'}
        ]]
    });
</script>

<table id="dg"></table>