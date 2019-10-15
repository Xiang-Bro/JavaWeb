<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8"><link rel="icon" href="https://jscdn.com.cn/highcharts/images/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
			#datatable {
			border: 1px solid #ccc;
			border-collapse: collapse;
			border-spacing: 0;
			font-size: 12px; }
            td,th {
			border: 1px solid #ccc;
			padding: 4px 20px;}
        </style>
        <script src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
        <script src="https://code.highcharts.com.cn/highcharts/modules/data.js"></script>
        <script src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
    </head>
    <body>
        <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        <p>数据表格</p>
        <table id="datatable">
            <thead>
                <tr>
                    <th></th>
                    <th>语文</th>
                    <th>数学</th>
                    <th>英语</th>
                    <th>体育</th>
                    
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${list}" var="stu">
						<tr>
							<th> ${stu.name} </th>
							<td> ${stu.chinese} </td>
							<td> ${stu.math} </td>
							<td> ${stu.english} </td>
							<td> ${stu.pe} </td>
						</tr>
			</c:forEach>
			</tbody>
        </table>
        <script>
           var chart = Highcharts.chart('container', {
			    data: {
			        table: 'datatable'
			    },
			    chart: {
			        type: 'column'
			    },
			    title: {
			        text: '从 数据库中提取学生成绩'
			        // 该功能依赖 data.js 模块，详见https://www.hcharts.cn/docs/data-modules
			    },
			    yAxis: {
			        allowDecimals: false,
			        title: {
			            text: '分',
			            rotation: 0
			        }
			    },
			    tooltip: {
			        formatter: function () {
			            return '<b>' + this.point.name.toLowerCase()+ '</b><br/>'
			            +this.series.name + '</b><br/>'
			                + this.point.y + ' 分' ;
			        }
			    }
			});
        </script>
    </body>
</html>
