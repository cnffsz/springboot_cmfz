<%@ page language="java" contentType="text/html;charset=utf-8"  isELIgnored="false"  pageEncoding="utf-8"%>
<script type="text/javascript">
    $("#pic_EditableDataGrid").edatagrid({
        url:'${pageContext.request.contextPath}/pictures/queryAll',
        method:"post",
        pagination:true,
        singleSelect:true,
        fit:true,
        fitColumns:true,
        singleSelect:false,
        pageList:[3,5,10],
        saveUrl:'${pageContext.request.contextPath}/pictures/save',
        updateUrl:'${pageContext.request.contextPath}/pictures/update',
        destroyUrl:'${pageContext.request.contextPath}/pictures/remove',
        columns:[[
            {field:'id',title:'图片id',width:100,hidden:true},
            {field:'title',title:'标题',width:100,align:'center'},
            {field:'ins',title:'简述',width:100,align:'center'},
            {field:'sta',title:'状态',width:100,align:'center',editor:{type:"text",options:{required:true}}},
            {field:'date',title:'更新时间',width:100,align:'center'}
        ]],
        view: detailview,
        detailFormatter: function(rowIndex, rowData){
            return '<table><tr>' +
                '<td rowspan=2 style="border:0"><img src="'+rowData.imgPath+'" style="height:80px;width: 130px"></td>' +
                '<td style="border:0">' +
                '<p>标题: ' + rowData.title + '</p>' +
                '<p>描述: ' + rowData.ins +'&nbsp;&nbsp;轮播图id&nbsp;&nbsp;'+rowData.id+'</p>' +
                '</td>' +
                '</tr></table>';
        },
        toolbar: [{
            text:'增加',
            iconCls: 'icon-add',
            handler: function(){
                $("#dd").dialog("open");
            }
        },{
            text:'删除',
            iconCls: 'icon-cut',
            handler: function(){
                //获取选中行
                var row =  $('#pic_EditableDataGrid').edatagrid('getSelected');
                if(row==null){
                    $.messager.alert('我的消息','请选中要删除的行','info')
                }else{
                    //销毁所有选中行
                    $('#pic_EditableDataGrid').edatagrid('destroyRow');
                }

            }
        },{
            text:'修改',
            iconCls: 'icon-edit',
            handler: function(){
                var row =  $('#pic_EditableDataGrid').edatagrid('getSelected');
                if(row==null){
                    $.messager.alert('我的消息','请正确选中修改的行','info')
                }else{
                    $('#pic_EditableDataGrid').edatagrid('editRow', $('#pic_EditableDataGrid').edatagrid('getRowIndex',row));
                }
            }
        },{
            text:'保存',
            iconCls: 'icon-edit',
            handler: function(){
                $('#pic_EditableDataGrid').edatagrid('saveRow');
            }
        }],
        /*saveUrl:
        updateUrl:
        destroyUrl:*/
    });
</script>
<table id="pic_EditableDataGrid" ></table>
<div id="dd" class="easyui-dialog" title="上传轮播图" style="width:280px;height:280px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'添加',
				handler:function(){
				alert('确定添加')
                $('#ff').form('submit', {
                        url:'${pageContext.request.contextPath}/pictures/save',
                        success:function(){
                            $('#dd').dialog('close');
                            $('#dg').datagrid('load');
                        }
                    });
				}
			},{
				text:'取消',
				handler:function(){
                    $('#dd').dialog('close')
				}
			}]">
    <form id="ff" method="post" enctype="multipart/form-data">
        <div style="margin-top: 20px;margin-left:25px">
            <label for="name">标题:</label>
            <input id="name" class="easyui-validatebox" type="text" name="title" data-options="required:true" />
        </div>
        <div style="margin-top: 20px;margin-left:25px">
            <label for="ins">简述:</label>
            <input  id="ins" class="easyui-validatebox" type="text" name="ins" data-options="required:true" />
        </div>
        <div style="margin-top: 20px;margin-left:25px">
            <label for="status">状态:</label>
            <select id="status" class="easyui-combobox" name="sta" style="width:150px;">
                <option value="y">展示</option>
                <option value="n">不展示</option>
            </select>
        </div>
        <div style="margin-top: 20px;margin-left:25px">
            <label for="file">上传图片:</label>
            <input  id="file" name ="image" class="easyui-filebox" style="width:150px">
        </div>
    </form>
</div>


