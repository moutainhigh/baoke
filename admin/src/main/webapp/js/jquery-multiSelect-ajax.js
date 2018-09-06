(function ($) {
    //弹出层
    $.openLayer = function (p) {
        var param = $.extend({
            returnValue: "", 			//以，分隔的选取结果id存放的位置id，默认为一个input。
            returnText: "", 			//以，分隔的选取结果文字存放的位置id，可以为span，div等容器。
            title: "请选择", 			//弹出窗口标题
            width: 650, 				//弹出窗口宽度
            span_width: { d1: 70, d3: 150 }, //可以自定义每一层级数据项显示宽度，用来对其排版。
            url: "", 					//ajax请求url
            dragEnable: true 			//是否允许鼠标拖动
        }, p || {});
        var fs = {
            init_Container: function () { //初始化头部和内容容器
                var close = "<span id='_cancel' style='cursor:pointer;'>[取消]</span>&nbsp;&nbsp;<span id='_ok' style='cursor:pointer;'>[确定]</span>";
                //头部
                var htmlDiv = "<div id='heads'><div id='headdiv'><span title='全选/全不选'><input type='checkbox' id='selectAll'/>" + param.title + "</span><span id='close'>" + close + "</span></div>";
                //内容容器创建部分
                htmlDiv += "<div id='container'><div id='selArea'><div>已选择：</div></div><div id='d1'></div></div></div>";
                pop.html(htmlDiv);
            },
            add_data: function (targetid) { //添加数据到容器，添加事件，初始化下一层次容器
                var data = ''; //返回数据变量

                $.ajax({
                    type: "post", 					//post方式
                    url: param.url, 						//ajax查询url
                    async: false, 					//同步方式，便于拿到返回数据做统一处理
                    success: function (d) { //ajax请求成功后返回数据
                        data = d;
                    }
                });
                //没有数据或者错误,添加提示信息返回
                if (data == "") {
                    $("#selArea").append("<span style='color:red;'>获取数据失败！</span>");
                    return;
                }

                var spanWidth = eval("param.span_width." + targetid.attr("id")); //每个数据显示项的宽度
                spanWidth = (spanWidth == undefined ? param.span_width.d1 : spanWidth); //没有设置的话，就使用第一个数据容器的值
                var inspanWidth = ($.browser.msie) ? 1 : 3; //内部文字和checkbox之间的距离
                
                //
                resp = $.parseJSON(data);
        		if(resp.success){
        			var allTags = resp.allTags;
        			var currentRingTags = resp.currentRingTags;

                    //可供选择的标签列表
                    var html = ''; //格式化数据存放容器，为了提高效率，使用了字符串
                    for (var i = 0; i < allTags.length; i++) {
                    	var tagObj = allTags[i];
                        html += "<span style='width:" + spanWidth + "px;white-space:nowrap;float:left;'><input type='checkbox' value='" + tagObj.id + "'><span style='margin-left:" + inspanWidth + "px;'>" + tagObj.tag + "</span></span>";
                    }
                    targetid.html(html); //格式化的html代码放入目标容器
                    
                    //已设定的标签
                    var ringTagHtml = '';
                    for (var i = 0; i < currentRingTags.length; i++) {
                    	var tagObj = currentRingTags[i];
                    	ringTagHtml += "<span style='white-space:nowrap;float:left;'><input type='checkbox' value='" + tagObj.id + "'><span style='margin-left:" + inspanWidth + "px;'>" + tagObj.tag + "</span></span>";
                    	
                    }
                    $("#selArea").append(ringTagHtml);
                    $("#selArea").find('input').each(function () {
                    	var value = $(this).val();
                    	$(this).attr("checked", true);
                    	$("#d1").find('input').each(function () {
                    		if($(this).val()==value){
                    			$(this).attr("checked", true);
                    		}
                        });
                    });
        		}else{
        			alert('ajax error!');
        		}
            },
            init_event: function () { //绑定已选择框中checkbox的事件，确定，取消事件响应
                var selArea = $("#selArea"),
                    d1 = $("#d1");
                selArea.delegate('input', 'click', function () {
                    $(this).parent().remove();
                    d1.find("input[value=" + this.value + "]").attr("checked", false);
                });
                d1.delegate('input', 'click', function () {
                    if (this.checked) {
                        selArea.append($(this).parent().clone().css({ "width": "", "background": "", "border": "" }));
                    } else {
                        selArea.find("input[value=" + this.value + "]").parent().remove();
                    }
                });
                $("#selectAll").click(function () {
                    selArea.find("input").each(function () {
                        this.click();
                    });
                    if (this.checked) {
                        d1.find("input").each(function () {
                            this.click();
                        });
                    }
                });
                $("#_cancel").click(function () {
                    bodybg.hide();
                    pop.hide();
                });
                $("#_ok").click(function () {
                    var vals = "";
                    var txts = "";
                    selArea.find("input").each(function () {
                        vals += "," + this.value;
                        txts += "," + $(this).next().text();
                    });
                    fs.set_returnVals(param.returnValue, vals);
                    fs.set_returnVals(param.returnText, txts);
                    
                    addLabel();//xdd 2014-03-08 添加标签================================================================

                    bodybg.hide();
                    pop.hide();
                });
            },
            set_selected: function (ins, a) {
                ins.each(function () {
                    if ($(this).next().html() == a) {
                        this.click();
                        return;
                    }
                });
            },
//            init_selected: function () {
//                var ts = $("#" + param.returnText).val().split(','),
//                    ins = $("#d1").find("input");
//                for (var i = 0, l = ts.length; i < l; i++) {
//                    fs.set_selected(ins, ts[i]);
//                }
//            },
            set_returnVals: function (id, vals) { //按"确定"按钮时处理、设置返回值
                if (id != "") {
                    var returnObj = $("#" + id);
                    if (returnObj.length > 0) {
                        if (returnObj.is("input")) {
                            returnObj.val(vals.substring(1));
                        } else {
                            returnObj.text(vals.substring(1));
                        }
                    }
                }
            },
            move: function () {
                if (param.dragEnable) { //允许鼠标拖动
                    var move = false; //移动标记
                    var ox, oy; //鼠标离控件左上角的相对位置
                    pop.mousedown(function (e) {
                        move = true;
                        ox = e.pageX - parseInt(pop.css("left"));
                        oy = e.pageY - parseInt(pop.css("top"));
                    }).mousemove(function (e) {
                        if (move) {
                            var x = e.pageX - ox; //移动时根据鼠标位置计算控件左上角的绝对位置
                            var y = e.pageY - oy;
                            pop.css({ top: y, left: x }); //控件新位置
                        }
                    }).mouseup(function () { move = false; });
                }
            },
            init_style: function () { //初始化css
                var margin = 4;
                var width = param.width - margin * 5;

                var css = "<style>";
                var aotu = "border:2px groove";
                css += "#popupAddr {position:absolute;border:3px ridge;width:" + param.width + "px;background-color:#e3e3e3;z-index:99;box-shadow:5px 5px 5px rgba(0,0,0,0.5)}";
                css += "#bodybg {width:100%;position:absolute;top:0;left:0;background-color:#000000;opacity:0.5}";
                css += "#heads {font-size:12px}";
                css += "#headdiv {color:white;background-color:#3C85ff;font-size:13px;line-height:30px;padding:0 12px;margin:1px;" + aotu + "}";
                css += "#close {float:right}";
                css += "#selArea {width:" + width + "px;min-height:48px;margin:" + margin + "px;padding:5px;background-color:#f4f4f4;float:left;" + aotu + "}";
                $("head").append(css + "</style>");
            }
        };

        var pop = $("#popupAddr"); //创建一个div元素
        var bodybg = $("#bodybg"); //创建背景层

        if (pop.length == 0) {
            fs.init_style(); //初始化样式
            pop = $("<div id='popupAddr'></div>");
            $("body").append(pop);
            bodybg = $("<div id='bodybg'></div>");
            $("body").append(bodybg);
            fs.move();
        }
        fs.init_Container(); //弹出层内容
        fs.add_data($("#d1")); //添加数据
        fs.init_event();

        //初始化已选中的项
//        fs.init_selected();

        //让多选框居中显示
        var yPos = ($(window).height() - pop.height()) / 2 + $(document).scrollTop();
        var xPos = ($(window).width() - pop.width()) / 2;
        pop.css({ top: yPos, left: xPos }).show();

        bodybg.height(document.body.scrollHeight);
        bodybg.show();
        pop.show();
    };
})(jQuery);

