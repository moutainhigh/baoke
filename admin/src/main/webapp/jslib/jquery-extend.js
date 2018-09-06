(function($){
	$.extend({
		getText : function(url) {
			var res = "";
			$.ajax({
				type : "GET",
				async : false,
				data:{
				},
				url : url,
				dataType : "text",
				success : function(data){
					res = data;
				},
				error:function(e){
					alert("请求资源：" + url + " 出错了");
				}
			});
			return res;
		},
		dateFormat : function(date, format) {
			if(!_.isDate(date)) {
				date = new Date(date * 1000);
			}
			if(_.isUndefined(format)) {
				format = "yyyy-MM-dd";
			}
			var o = {
				"M+" : date.getMonth()+1,					//month
				"d+" : date.getDate(),						//day
				"H+" : date.getHours(),						//hour
				"m+" : date.getMinutes(),					//minute
				"s+" : date.getSeconds(),					//second
				"q+" : Math.floor((date.getMonth()+3)/3),	//quarter
				"S" : date.getMilliseconds()				//millisecond
			} 
			if(/(y+)/.test(format)) {
				format = format.replace(RegExp.$1, (date.getFullYear()+"").substr(4 - RegExp.$1.length));
			}
			for(var k in o) {
				if(new RegExp("("+ k +")").test(format)) {
					format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
				}
			}
			return format;
		}
	});
})(jQuery);