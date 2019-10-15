<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>学生信息管理系统</title>
    <link rel="stylesheet" type="text/css" href="student.css" />
    <script type="text/javascript" src="bootstrap.js"></script>
    <style>
    	a:hover{
		    color: #2e56c3;
		
		}
		a{
		    color: rgba(1, 2, 6, 0.8);
		}
		button:hover{
		    color: #fffdf4;
		
		
		}
		.container{
		    border:0 solid darkslategray;
		    border-radius:30px;
		    width: 95%;
		    height: 700px;
		    background-color: lightblue;
		    background-color: rgba(195, 208, 212, 0.5);
		    margin-left: 40px;
		    margin-top: 25px;
		    box-shadow: 5px 5px 20px rgba(200, 214, 255, 0.72);
		}
		#links{
		    border-right: 1.5px solid black;
		    height: 700px;
		    float: left;
		}
		#content{
		    float: right;
		    background-color: rgba(208, 212, 202, 0.2);
		    width: 87%;
		    height: 700px;
		    border-radius: 0 30px 30px 0;
		}
		.img{
		    margin-left: 13px;
		    margin-top: 10px;
		    width: 150px;
		    height: 150px;
		    border-radius: 30px;
		}
		
		.gz{
		    position: absolute;
		    top:630px;
		    left: 1320px;
		}
    </style>
    
</head>
	<body bgcolor="#a9a9a9" background="img/home.jpg">
	
		<div class="container" >
		    <!-- 页面导航 -->
		    <div id="links">
		        <img class="img" src="img/logo.jpg" />
		        <div class="box" style="margin-left: 35px;">
		            <table width="149.5px" height="200px">
		                <tr>
		                    <td> <a href="Insert_news.html" style="text-decoration: none;"> <h3>A:添加学生信息</h3></a> </td>
		                </tr>
		                <tr>
		                    <td> <a href="Scores.html" style="text-decoration: none;"> <h3>B:添加成绩</h3> </a> </td>
		                </tr>
		                <tr>
		                    <td> <a href="Find.jsp" style="text-decoration: none;"> <h3>C:查找成绩</h3> </a> </td>
		                </tr>
		                <tr>
		                    <td> <a href="Pic.jsp" style="text-decoration: none;"> <h3>D:成绩柱状图</h3> </a> </td>
		                </tr>
		                <tr>
			                <td> <a href="Print.jsp" style="text-decoration: none;"> <h3>E:学生报表</h3> </a> </td>
			            </tr>
			            <tr>
		               		<td> <a href="Reward.html" style="text-decoration: none;"> <h3>F:奖惩情况</h3> </a> </td>
		           		</tr>
		            </table>
		        </div>
		        <form action="../Insert_pass" method="post">
		        	<button style="background: pink;width: 100px;height: 40px;margin-left: 40px;margin-top:100px;font-size: 15px;border-color: pink;">注销</button>
		        </form>
		    </div>
		
		    <div id="content">
		   	    <h2>学生报表</h2>
				
				<form action="../Print" method="post">
					请选择信息：
					<select name="option">
						<option value="成绩报表"> 成绩报表 </option>
						<option value="信息报表"> 信息报表 </option>
						<option value="奖惩情况"> 奖惩情况 </option>
					</select>
					<input type="submit" value="提交" />
				</form>
				<hr/>
				<table border="1px" width="500px">
						<tr>
							<td>学号</td>
							<td>姓名</td>
							<td>专业</td>
							<td>班级</td>
							<td>语文</td>
							<td>数学</td>
							<td>英语</td>
							<td>体育</td>
							<td>总分</td>
						</tr>
						<c:forEach items="${list}" var="stu">
							<tr>
								<td> ${stu.id} </td>
								<td> ${stu.name} </td>
								<td> ${stu.zhuanye} </td>
								<td> ${stu.banji} </td>
								<td> ${stu.chinese} </td>
								<td> ${stu.math} </td>
								<td> ${stu.english} </td>
								<td> ${stu.pe} </td>
								<td> ${stu.chinese+stu.math+stu.english+stu.pe} </td>
							</tr>
						</c:forEach>
				</table>
				<form action="../Excel_print">
					<input type="submit" value="导出" />
				</form>
			</div>
		</div>
	</body>
</html>			