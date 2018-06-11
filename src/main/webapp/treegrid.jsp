<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<script type="text/javascript">
    var  toolbar=[{
        text:'专辑详情',
        iconCls: 'icon-edit',
        handler: function(){
            var row = $("#tree").treegrid('getSelected')
            console.log(row);
            if(row==null){
                $.messager.alert('我的消息','请选中专辑所对应的行','info')
            }else {
                if (row.downPath != null) {
                    $.messager.alert('我的消息','请选择相应的专辑','info')
                }else{
                    $("#album_dialog").dialog('open');
                    $("#album_ff").form('load', row);
                    $("#coverImg").prop('src',row.coverImg);

                }
            }
        }
    },{
        iconCls: 'icon-save',
        text:'添加专辑',
        handler: function(){
            $("#addAlbum_dialog").dialog('open');
        }
    },{
        iconCls: 'icon-save',
        text:'添加章节',
        handler: function(){
            var row = $("#tree").treegrid('getSelected')
            if(row==null){
                $.messager.alert('我的消息','请选择相应的专辑','info')
            }else {
                if(row.downPath != null){
                    $.messager.alert('我的消息','请选择相应的专辑','info')
                }else{
                    $("#album_id").textbox('setValue',row.id)
                    $("#album_id").next().hide();
                    $("#chapter_dialog").dialog('open')
                }

            }

        }
    },{
        iconCls: 'icon-redo',
        text:'下载音乐',
        handler: function(){
            var row = $("#tree").treegrid('getSelected')
            if(row==null){
                $.messager.alert('我的消息','请选择相应的章节','info')
            }else {
                if(row.downPath == null){
                    $.messager.alert('我的消息','请选择要下载的章节','info')
                }else{
                    location.href = "${pageContext.request.contextPath}/chapter/down?url=" + row.downPath + "&title=" + row.title;
                }

            }

        }
    }]
    $(function () {
        $('#tree').treegrid({
            url: '${pageContext.request.contextPath}/album/queryAll',
            method: 'get',
            lines: true,
            rownumbers: true,
            idField: 'id',
            treeField: 'title',
            fitColumns:true,
            fit:true,
            border : false,
            animate:true,
            pagination:true,
            pageList:[5,10,15,],
            toolbar:toolbar,
            onDblClickRow:function(row){  //双击播放按钮
                 var row1 = $("#tree").treegrid('getSelected')
                if(row.downPath==null){
                    $.messager.alert('我的消息','请选中要播放的章节','info')
                }else {
                    $("#album_audio").dialog("open");
                    $("#chapter_audio").prop('src',row.downPath);
                }
            },
            columns:[[
                {field:'title',title:'标题',width:180},
                {field:'downPath',title:'下载路径',width:60,align:'center'},
                {field:'musicSize',title:'章节大小',width:80,align:'center'},
                {field:'uploadDate',title:'章节时长',width:80,align:'center'},
            ]]
        });
    });

</script>
<table id="tree" style="width:600px;height:400px"></table>
<%--展示专辑详情--%>
<div id="album_dialog" class="easyui-dialog" title="展示专辑" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <form id="album_ff" >
    <div>
        <label for="title">标题:</label>
        <input id="title" class="easyui-validatebox" type="text" name="title" style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
    </div>
    <div>
        <label for="count">集数:</label>
        <input id="count" class="easyui-validatebox" type="text" name="count" style="width:80%;height:20px;margin-top:18px;margin-left:10px" />
    </div>
    <div>
        <label for="score">分数:</label>
        <input id="score" class="easyui-validatebox" type="text" name="score" style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
    </div>
    <div>
        <label for="author">作者:</label>
        <input id="author" class="easyui-validatebox" type="text" name="author" style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
    </div>
    <div>
        <label for="broadCast">播音:</label>
        <input id="broadCast" class="easyui-validatebox" name="broadCast" type="text" style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
    </div>
    <div>
        <label for="brief">简介:</label>
        <input id="brief"  name="brief" class="easyui-validatebox" type="text"  style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
    </div>
    <div>
        <label for="date">更新日期:</label>
        <input id="date" name="date" class="easyui-validatebox" type="text" style="width:80%;height:20px;margin-left:10px;margin-top:18px "  />
    </div>
    <div style="text-align: center">
        <p><label for="coverImg">封面:</label></p>
        <img id = "coverImg"  src="">
    </div>
   </form>
</div>
<%--播放的dialog--%>
<div id="album_audio" class="easyui-dialog" title="章节播放" style="width:300px;height:80px;text-align: center;background-color: #00ee00"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,">
    <audio id="chapter_audio" src="" controls="controls" autoplay="autoplay"></audio>
</div>

<%--添加章节--%>
<div id="chapter_dialog" class="easyui-dialog" title="上传章节" style="width:200px;height:130px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'保存',
				handler:function(){
                  $('#chapter_ff').form('submit',{
                  url:'${pageContext.request.contextPath}/chapter/add',
                  success:function(data){
                       $('#chapter_dialog').dialog('close');
                       $('#tree').treegrid('load');
                    }
                  })
				}
			},{
				text:'关闭',
				handler:function(){
                    $('#chapter_dialog').dialog('close');
				}
			}]">
    <form id="chapter_ff" method="post" enctype="multipart/form-data">
        <div>
            <label for="album_id"></label>   <%--专辑id--%>
            <input class="easyui-textbox" value="0" name="albumId" id="album_id"/>
        </div>
        <div>
            <label for="file">上传:</label>
            <input class="easyui-filebox" id="file" name="addFile"/>
        </div>
    </form>
</div>
<%--添加专辑--%>
<div id="addAlbum_dialog" class="easyui-dialog" title="添加专辑" style="width:400px;height:400px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{
				text:'添加',
				handler:function(){
                  $('#album_add').form('submit',{
                  url:'${pageContext.request.contextPath}/album/save',
                  success: function(){
                       $('#addAlbum_dialog').dialog('close');
                       $('#tree').treegrid('load');
                    }
                  })
				}
			},{
				text:'关闭',
				handler:function(){
                    $('#addAlbum_dialog').dialog('close');
				}
			}]">
    <form id="album_add" method="post" enctype="multipart/form-data" >
        <div>
            <label for="title2">标题:</label>
            <input id="title2" class="easyui-validatebox" data-options="required:true" type="text" name="title" style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
        </div>
        <div>
            <label for="count2">集数:</label>
            <input id="count2" class="easyui-validatebox" data-options="required:true"  type="text" name="count" style="width:80%;height:20px;margin-top:18px;margin-left:10px" />
        </div>
        <div>
            <label for="score2">分数:</label>
            <input id="score2" class="easyui-validatebox" data-options="required:true"  type="text" name="score" style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
        </div>
        <div>
            <label for="author2">作者:</label>
            <input id="author2" class="easyui-validatebox" data-options="required:true"  type="text" name="author" style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
        </div>
        <div>
            <label for="broadCast2">播音:</label>
            <input id="broadCast2" class="easyui-validatebox" data-options="required:true"  name="broadCast" type="text" style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
        </div>
        <div>
            <label for="brief2">简介:</label>
            <input id="brief2"  name="brief" data-options="required:true"  class="easyui-validatebox" type="text"  style="width:80%;height:20px;margin-top:18px;margin-left:10px"/>
        </div>
        <div style="width:80%;height:20px;margin-top:18px;margin-left:10px">
            <label for="coverImg1">封面:</label>
            <input class="easyui-filebox" id="coverImg1" name="image" style="width: 80%"/>
        </div>
    </form>
</div>
