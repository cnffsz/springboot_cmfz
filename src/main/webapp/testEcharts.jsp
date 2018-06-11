<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <!-- 引入 ECharts 文件 -->
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
        var goEasy=new GoEasy({
            appkey: "BC-2460f93c17514ad18d20be7572c108c0"
        });
        var myChart = echarts.init(document.getElementById('tongji_main'));
            //GoEasy-OTP可以对appkey进行有效保护，详情请参考 7.GoEasy-OTP
          goEasy.subscribe({
              channel: "my_channel",
              onMessage:function(message){
                //json串转为js对象
                  console.log(message)
                  var data = JSON.parse(message);
                  // 基于准备好的dom，初始化echarts实例

                  // 指定图表的配置项和数据
                  var option = {
                      title: {
                          text: 'ECharts 入门示例',
                          subtext: '这是副标题',
                      },
                      /*提示框组件*/
                      tooltip: {},
                      /*选项卡*/
                      legend: {
                          data: ['柱状', "折线"]
                      },
                      xAxis: {
                          data: ["衬衫1", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
                      },
                      yAxis: {},
                      series: [{
                          name: '柱状',
                          type: 'bar',
                          data: data.data
                      }, {
                          name: '折线',
                          type: 'line',
                          data: data.data
                      }]
                  }
                  // 使用刚指定的配置项和数据显示图表
                  myChart.setOption(option);
                6}
            })
        });
    </script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="tongji_main" style="width: 600px;height:400px;margin: 0 auto"></div>
</body>

