<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<style>
   table,th,td{ margin:0px; padding:0px; line-height:18px; color:#000; font-size:12px;}
   .mytable th{  background:#BCE774; text-align:center; font-weight:normal; width:150px;  padding:6px;}
   .mytable td{background:#ECFBD4; padding:3px;  }
   .mytable th,.mytable td{border-top:1px solid #e9e9e9;border-left:1px solid #e9e9e9;text-align:left; }
   .mytable{border-bottom:1px solid #e9e9e9;border-right:1px solid #e9e9e9;}
   button{
   	margin:10px;
   }
</style>
<script>
	var path = "${pageContext.request.contextPath}";
	function deleteJob(id) {
		if (window.confirm("确定要删除吗？")) {
			location = path + "/job/delete?id=" + id;
		}
	}
	function add() {
		location = path + "/jobdetail.jsp";
	}
	
</script>
<head>
    <title>
        任务管理
    </title>
</head>
<body style="text-align:center;">
    <h1>任务管理</h1>
    <button onclick="javascript:add();">添加任务</button>
    <table width="98%" class="mytable" cellpadding="0" cellspacing="0">
        <thead>
            <tr>
                <th>id</th>
                <th>job_class</th>
                <th>job_method</th>
                <th>method_args</th>
                <th>job_name</th>
                <th>job_group</th>
                <th>job_status</th>
                <th>cron_expression</th>
                <th>description</th>
                <th>action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${list}" varStatus="status">
            <tr>
                <td>${item.id }</td>
                <td>${item.jobClass }</td>
                <td>${item.jobMethod }</td>
                <td>${item.methodArgs }</td>
                <td>${item.jobName }</td>
                <td>${item.jobGroup }</td>
                <td>
                    <c:if test="${item.jobStatus == '0'}">
               	    运行中
                    </c:if>
                    <c:if test="${item.jobStatus == '1'}">
                    停止中
                    </c:if>
                </td>
                <td>${item.cronExpression }</td>
                <td>${item.description }</td>
                <td>
                    <a href="${pageContext.request.contextPath}/job/update?id=${item.id}">修改</a>
                    |
                    <c:if test="${item.jobStatus == '0'}">
                    <a href="${pageContext.request.contextPath}/job/stop?id=${item.id}">停止</a>
                    |
                    </c:if>
                    <c:if test="${item.jobStatus == '1'}">
                    <a href="${pageContext.request.contextPath}/job/start?id=${item.id}">启动</a>
                    |
                    </c:if>
                    <a href="javascript:deleteJob('${item.id }')">删除</a>
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
