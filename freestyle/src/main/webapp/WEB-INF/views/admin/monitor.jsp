<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/layout/taglib.jsp" %>
<!DOCTYPE html>
<html><!-- InstanceBegin template="/Templates/template.dwt" codeOutsideHTMLIsLocked="false" -->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- InstanceBeginEditable name="doctitle" -->
	<title>服务器监控 - 随变网</title>
	<!-- InstanceEndEditable -->
    <link rel="stylesheet" href="${staticPath}/commons/layer/layui/css/layui.css" media="all">
	<link href="${staticPath}/admin/css/index.css" rel="stylesheet" type="text/css" />

	<script type="text/javascript" src="${staticPath}/commons/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="${staticPath}/commons/layer/layui/layui.js"></script>
	<!-- InstanceBeginEditable name="head" -->
    <script src="${staticPath}/commons/echarts/echarts.js" type="text/javascript"></script>
	<!-- InstanceEndEditable -->
</head>

<body>

<div class="g-hd">
    <div style="position:absolute;color:#AAA;font-size:25px;line-height:70px;left:20px;width:500px;">随变网管理后台</div>
    <div style="position:absolute;line-height:70px;right:30px;">
    	您好，<span>admin</span>
         <a href="javascript:logout()"> 退出</a>
    </div>
</div>
<div class="g-sd">
    <ul class="layui-nav layui-nav-tree" lay-filter="test">
    <!-- 侧边导航: <ul class="layui-nav layui-nav-tree layui-nav-side"> -->
      <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">应用管理</a>
        <dl class="layui-nav-child">
          <dd><a href="${ctx}/admin/account">用户管理</a></dd>
          <dd><a href="${ctx}/admin/mood">心情管理</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item layui-nav-itemed">
        <a href="javascript:;">服务器管理</a>
        <dl class="layui-nav-child">
          <dd><a href="${ctx}/admin/monitor">服务器监控</a></dd>
        </dl>
      </li>
    </ul>
</div>
<div class="g-mn">
	
    <!-- InstanceBeginEditable name="main" -->
    	<div>
        <div style="background:white;line-height:50px;border-bottom:1px solid #DDD;padding-left:20px;color:#888;margin-bottom:20px;">服务器监控</div>
        <div style="width:600px;float:left;">
        	<div style="width:300px;float:left;height:300px;text-align:center;position:relative;">
                <div id="cpu" style="width:100%;height:300px;"></div>
                <span style="bottom:40px;left:40%;position:absolute;">CPU使用率</span>
            </div>
            <div style="width:300px;float:left;height:300px;text-align:center;position:relative;">
                <div id="memory" style="width:100%;height:300px;"></div>
                <span style="bottom:40px;left:35%;position:absolute;">内存使用率</span>
            </div>
        </div>
        <%-- <div style="position:absolute;right:0;left:320px;bottom:0px;top:150px;">
        	网卡：<select id="ifname">
        			<c:forEach items="${ifnames}" var="ifname">
        				<option value="${ifname}">${ifname}</option>
        			</c:forEach>
        	</select>
        	<div id="net" style="width:100%;height:100%;"></div>
        </div> --%>
        <div>
        
        
        <script type="text/javascript">
			var myChart = echarts.init(document.getElementById('cpu'));
			var memoryChart = echarts.init(document.getElementById('memory'));
			/* var netChart = echarts.init(document.getElementById('net')); */
			
			option = {
				tooltip : {
					formatter: "{a} <br/>{b} : {c}%"
				},
				series: [
					{
						name: '当前CPU使用情况',
						type: 'gauge',
						detail: {formatter:'{value}%'},
						data: [{value: 50, name: ''}]
					}
				]
			};
			myChart.setOption(option, true);
			setInterval(function () {
				$.get('${ctx}/admin/monitor/get-cpu-used',function(r) {
					if(r.code == 'OK') {
						option.series[0].data[0].value = r.attributes.used;
						myChart.setOption(option, true);
					}
				},'json');
			},2000);
			
			
			memoryChartOption = {
				tooltip : {
					formatter: "{a} <br/>{b} : {c}%"
				},
				series: [
					{
						name: '当前内存使用情况',
						type: 'gauge',
						detail: {formatter:'{value}%'},
						data: [{value: 50, name: ''}]
					}
				]
			};
			memoryChart.setOption(memoryChartOption, true);
			setInterval(function () {
				$.get('${ctx}/admin/monitor/get-mem-used',function(r) {
					if(r.code == 'OK') {
						memoryChartOption.series[0].data[0].value = r.attributes.used;
						memoryChart.setOption(memoryChartOption, true);
					}
				},'json');
			},2000);
			
			

			/* function randomData() {
			    now = new Date(+now + oneDay);
			    value = value + Math.random() * 21 - 10;
			    return {
			        name: now.toString(),
			        value: [
			            [now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
			            Math.round(value)
			        ]
			    }
			}
			
			var data = [];
			var now = +new Date(2016, 12, 27);
			var oneDay = 24 * 3600 * 1000;
			var value = Math.random() * 1000;
			 for (var i = 0; i < 1000; i++) {
			    data.push(randomData());
			}
			
			netOption = {
				title: {
					text: '网络流量监控'
				},
				tooltip: {
					trigger: 'axis',
					formatter: function (params) {
						console.log(params);
						params = params[0];
						var date = new Date(params.name);
						return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
					},
					axisPointer: {
						animation: false
					}
				},
				xAxis: {
					type: 'value',
					splitLine: {
						show: false
					}
				},
				yAxis: {
					type: 'value',
					boundaryGap: [0, '100%'],
					splitLine: {
						show: false
					}
				},
				series: [{
					name: '总接收字节数',
					type: 'line',
					showSymbol: false,
					hoverAnimation: false,
					data: data
				}]
			};
			netChart.setOption(netOption);
			setInterval(function () {
				for (var i = 0; i < 5; i++) {
					var ifname = $('#ifname').val();
					$.get('${ctx}/admin/monitor/get-rx-bytes/' + ifname,function(r) {
						if(r.code == 'OK') {
							now = new Date();
							value = r.attributes.rxBytes;
							var rx = {
								name: now.toString(),
								value: [
									[now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
									Math.round(value)
								]
							};
							data.shift();
							data.push(rx);
							
						}
					},'json');
				}
				netChart.setOption({
			        series: [{
			            data: data
			        }]
			    });
			}, 1000);
 */
			
		</script>
	<!-- InstanceEndEditable -->
</div>



	

	<script type="text/javascript">
	function logout() {
		layer.confirm('您确定要继续退出吗？', {
		skin: '#227dc5',
		  btn: ['让我走~','我再想想'] //按钮
		}, function(){
			window.location = '${ctx}/admin/extend/logout';
		}, function(){
		  
		});
	}
    	layui.use(['element','layer'], function(){
		  var element = layui.element();
		  
		  //一些事件监听
		  element.on('tab(demo)', function(data){
			console.log(data);
		  });
		});
    </script>
</body>
<!-- InstanceEnd --></html>
