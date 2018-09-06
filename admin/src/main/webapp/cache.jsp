<%@ page isELIgnored="false"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>缓存清理</title>
		<meta charset="utf-8">
		<style type="text/css">
		select {height: 24px;border-radius:2px;border:1px solid;}
		input{height: 24px;width:250px;border-radius:2px;border:1px solid;}
		input.txt{width:180px;}
		button {height: 24px;width:60px;border-radius:2px;border:1px solid;}
		ul {padding:0px;list-style:none;font-size:12px;width:80%;text-align:left;margin:10px auto;border-top:1px dotted;}
		ul li{margin:0px;padding:0px;line-height:30px;margin-top:5px;}
		ul li .clear-box{width:60%;float:right;text-align:right;}
		ul li .clear-box .namespace{float:left;}
		.clear_btn{text-decoration:none;background:#33cc99;color:#fff;height:24px;cursor:pointer;border-radius:2px;line-height:24px;margin:0 10px;display:inline-block;width:40px;text-align:center;}
		</style>
		<script type="text/javascript" src="js/jquery-1.6.3.js"></script>
		
	</head>
	<body>
		<div style="margin:20px;border:1px solid ;border-radius:3px;min-height:200px;text-align: center;padding:20px;">
			 
			 <input id="key" placeholder="查询namespace">
			 
			 <button id="search">搜索</button>
			 
			 <div>
			 	 <ul id="data">
			 	 	
			 	 </ul>
			 </div>
			 
		</div>
		
		<script type="text/javascript">
		
		$(function(){
			var data = null;
			$.getJSON("getNamespace",function(ret){
				data = ret ;
 			});
			
			$(document).keydown(function(evt){if(evt.keyCode==13){$("#search").click();}});
			
			$("#search").click(function(){
				 $("#data").html("");
				 var html = "";
				 var key = $("#key").val(); 
		 		 if(data && key){
		 			 var kl = key.toLowerCase();
		 			 for(var i in data){
		 				 var use = i.toLowerCase();
		 				 if(use.indexOf(kl) >= 0 || use === kl ){
		 					 html+= ("<li class=''><span>"+ data[i] + 
		 					 "</span><div class='clear-box'>" + 
		 					 "<span class='namespace'>"+use+"</span>" +
		 					 "<div class='clear_box_right'><input class='txt'/>"+
		 					 "<a class='clear_btn' href='javascript:void(0)' onclick=\"clearCache('"+i+"',this)\">清除</a>"+
		 					 "</div></div></li>");
		 				 }
		 			 }
		 			 $("#data").append(html);
		 		 }
		 	});
		});
		
		function clearCache(ns,dom){
			 var val = $(dom).prev().val();
			 if(confirm("确定清除?") ){
 				$.get("clearByKey?key="+val+"&prefix="+ns ,function(ret){
 					alert(ret.message)
	 			});
	 		}
		}
		
		</script>
	</body>
</html>