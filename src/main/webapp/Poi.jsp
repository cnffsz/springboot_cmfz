
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <script type="text/javascript">
        $(function(){
                    $('#excel-dg').edatagrid({
                        url:'##',
                        toolbar:[{
                            text:'自定义导出',
                            iconCls:'icon-arrow_out',
                            handler:function(){
                                $("#export_dd").dialog('open');

                            }
                        },{
                            text:'导入',
                            iconCls:'icon-add',
                            handler:function(){
                                $.ajax({
                                    url:"${pageContext.request.contextPath}/poiUser/importFile",
                                    type:"get",
                                    success:function () {
                                        alert("导入成功");
                                    }
                                })
                            }
                        }]
                    /*columns:[[
                    {field:'code',title:'Code',width:100},
                    {field:'name',title:'Name',width:100},
                    {field:'price',title:'Price',width:100,align:'right'}
                    ]]*/
                })
            $("#customer_btn").click(function(){
                var text= $("#customer_cc").combotree('getText');
                var values = $("#customer_cc").combotree('getValues');
                console.log(text)
                console.log(values)
                var ef="";
                $.each(values ,function (index, value) {
                    if( values.length-1 != index ){
                        ef += value+",";
                    }else{
                        ef += value;
                    }

                })

                $("#customer_form").form('submit',{
                    url:'${pageContext.request.contextPath}/poiUser/export',
                    queryParams:{"titles":text,"files":ef}
                })

            })
        });
    </script>
    <table id="excel-dg"></table>
    <div id="export_dd" class="easyui-dialog" title="自定义导出" style="width:300px;height:200px;"
         data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
        <form id="customer_form" method="post">
        <select id="customer_cc" class="easyui-combotree" style="width:200px;"
                data-options="url:'${pageContext.request.contextPath}/title.json',checkbox:true,onlyLeafCheck:true,multiple:true"></select>
        </form>
        <a id="customer_btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a>
    </div>

