<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<style>
	.textClass {
		width : 500px;
	}
	.leftTd {
		width : 200px;
		vertical-align:middle;
		text-align : right;
	}
	td {
		padding : 5px;
	}
</style>
<head>
    <title>添加/修改任务</title>
</head>
<body style="align:center;text-align:center;">
    <form action="${pageContext.request.contextPath}/job/save" method="post">
        <input type="hidden" name="id" value="${sj.id}" />
        <input type="hidden" name="jobStatus" value="${sj.jobStatus}" />
        <table align="center" cellpadding="0" cellspacing="0" style="border:0;">
            <tr>
                <td class="leftTd">
                    job_class:
                </td>
                <td>
                    <input class="textClass" type="text" name="jobClass" value="${sj.jobClass}" />
                </td>
            </tr>
            <tr>
                <td class="leftTd">
                    job_method:
                </td>
                <td>
                    <input class="textClass" type="text" name="jobMethod" value="${sj.jobMethod}" />
                </td>
            </tr>
            <tr>
                <td class="leftTd">
                    method_args:
                </td>
                <td>
                    <textarea rows="10" class="textClass" name="methodArgs">${sj.methodArgs}</textarea>
                </td>
            </tr>
            <tr>
                <td class="leftTd">
                    job_name:
                </td>
                <td>
                    <input class="textClass" type="text" name="jobName" value="${sj.jobName}" />
                </td>
            </tr>
            <tr>
                <td class="leftTd">
                    job_group:
                </td>
                <td>
                    <input class="textClass" type="text" name="jobGroup" value="${sj.jobGroup}" />
                </td>
            </tr>
            <tr>
                <td class="leftTd">
                    cron_expression:
                </td>
                <td>
                    <input class="textClass" type="text" name="cronExpression" value="${sj.cronExpression}" />
                </td>
            </tr>
            <tr>
                <td class="leftTd">
                    description:
                </td>
                <td>
                    <input class="textClass" type="text" name="description" value="${sj.description}" />
                </td>
            </tr>
        </table>
        <input type="submit" />
    </form>
    <div style="text-align:left;margin:50px;">
        <p>
            需要修改的一般仅限于<strong>method_args</strong>和<strong>cron_expression</strong>
        </p>
        <p>
            <strong>method_args</strong>代表的是方法的参数，如果job_method为<strong>pushUnErnie ， pushUnAction</strong>，则需要有4个参数，分别是String message, String version, String title, String transmission 代表的意思是：需要推送的内容，进行模糊匹配的版本号，android推送的标题，android推送的透传内容，格式如下：message&&version&&title&&transmission
            <br>
            如果job_method为<strong>pushUnUpgrade</strong>，则需要有5个参数，分别是String message, String low, String high, String title, String transmission 代表的意思是：需要推送的内容，进行模糊匹配的低版本号，进行模糊匹配的高版本号，android推送的标题，android推送的透传内容，格式如下：message&&low&&high&&title&&transmission
        </p>
        <p>
            <strong>cron_expression</strong>是用来指定何时执行任务。
            <br>
            一个cron表达式有至少6个（也可能是7个）由空格分隔的时间元素。从左至右，这些元素的定义如下：
            <br>
            1．秒（0–59）
            <br>
            2．分钟（0–59）
            <br>
            3．小时（0–23）
            <br>
            4．月份中的日期（1–31）
            <br>
            5．月份（1–12或JAN–DEC）
            <br>
            6．星期中的日期（1–7或SUN–SAT）
            <br>
            7．年份（1970–2099）
        </p>
        <table width="80%" cellspacing="1" cellpadding="1" border="1">
            <tbody>
                <tr>
                    <td>
                        表 达 式
                    </td>
                    <td>
                        意 义
                    </td>
                </tr>
                <tr>
                    <td>
                        0 0 10,14,16 * * ?
                    </td>
                    <td>
                        每天上午10点，下午2点和下午4点
                    </td>
                </tr>
                <tr>
                    <td>
                        0 0,15,30,45 * 1-10 * ?
                    </td>
                    <td>
                        每月前10天每隔15分钟
                    </td>
                </tr>
                <tr>
                    <td>
                        30 0 0 1 1 ? 2012
                    </td>
                    <td>
                        在2012年1月1日午夜过30秒时
                    </td>
                </tr>
                <tr>
                    <td>
                        0 0 8-5 ? * MON-FRI
                    </td>
                    <td>
                        每个工作日的工作时间
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
</html>
