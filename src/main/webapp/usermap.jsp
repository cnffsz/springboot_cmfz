
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
            // 指定图表的配置项和数据
            option = {
                title: {
                    text: '持名法州APP用户分布图',
                    subtext: '2018年6月15日 最新数据',
                    left: 'center'
                },
                tooltip : {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data:['男','女']
                },
                visualMap: {
                    min: 0,
                    max: 2500,
                    left: 'left',
                    top: 'bottom',
                    text:['高','低'],           // 文本，默认为数值文本
                    calculable : true
                },
                toolbox: {
                    show: true,
                    orient : 'vertical',
                    left: 'right',
                    top: 'center',
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [{
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        label: {
                        normal: {
                                show: false
                        },
                            emphasis: {
                                show: true
                            }
                        },
                        data:[]
                    },{
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        }, data:[]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            $(function(){
                $.post("${pageContext.request.contextPath}/user/queryAllMen?sex=m",function (data) {
                    myChart.setOption({
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '男',
                            data: data
                        }]
                    });
                }, "json");

                $.post("${pageContext.request.contextPath}/user/queryAllFmen?sex=f", function (data) {
                    myChart.setOption({
                        series: [{
                            // 根据名字对应到相应的系列
                            name: '女',
                            data: data
                        }]
                    });
                }, "json");
        });
    </script>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;margin: 0 auto"></div>
