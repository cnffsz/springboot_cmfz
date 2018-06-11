<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
    <script type="text/javascript">
      $(function(){
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('tongji_main'));
          // 异步加载统计信息
          $.post("${pageContext.request.contextPath }/user/queryDay",function(data){
              console.log(data);
              // 使用刚指定的配置项和数据显示图表。
              myChart.setOption({
                  xAxis: {
                      data: data.intervals
                  },
                  series: [{
                      // 根据名字对应到相应的系列
                      name: '活跃用户',
                      data: data.counts
                  }]
              });
          },"json");

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '持名法州App活跃用户'
            },
            color:'#00d8ff',
            tooltip: {},
            legend: {
                data:['活跃用户']
            },
            xAxis: {
                data: []
            },
            yAxis: {},
            series: [{
                name: '人数',
                type: 'bar',
                data: []
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
      })
    </script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="tongji_main" style="width: 600px;height:400px;margin: 0 auto"></div>

