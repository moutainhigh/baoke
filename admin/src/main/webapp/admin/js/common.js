//公用方法
var LIB = (function() {

	// 当前ajaxurl
	var loadedUrl = '';

	/**
	 * 获取url指定参数的值
	 * @param {string} key - url指定参数名称
	 * @param {string} [url = window.location.href] - 指定链接，可选参数，默认为当前页面地址
	 * @return {string} url对应参数的值
	 */
	function getUrlParam(key, url) {
		if(key == undefined) return '';

		var rParam = new RegExp("(\\?|&)" + key + "=([^&]*)(&|$)", "i");
		var result = (url || window.location.search).match(rParam);

		if(result) return decodeURIComponent(result[2]);

		return '';
	}

	/**
	 * 判断指定字符串是否为手机号码
	 * @param {string} str - 字符串
	 * @return {boolean} 以1开头的11位数字为合法的手机号
	 */
	function isMobile(str) {
		return /^1\d{10}$/.test(str);
	}
	/**
	 * 判断指定字符串是否为非负浮点数
	 * @param {string} str - 字符串
	 * @return {boolean} 以1开头的11位数字为合法的手机号
	 */
	function isMoney(str) {
		return /^\d+(\.\d+)?$/.test(str);
	}

	/**
	 * 判断指定字符串是否为邮箱
	 * @param {string} str - 字符串
	 * @return {boolean} 以字母|数字|_|.|-开头，加上@，然后至少包含一个"."和两个单词，顶级域长度为2-4个字符
	 */
	function isMail(str) {
		return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})$/.test(str);
	}

	function setCookie(name, value) {
		var Days = 60;
		var exp = new Date();
		exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
		document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
	}

	function getCookie(name) {
		var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");　　
		return(arr = document.cookie.match(reg)) ? unescape(arr[2]) : null;
	}

	function setItem(key, val) {
		if(window.localStorage) {
			window.localStorage.setItem(key, val);
		} else {
			setCookie(key, val);
		}
	}

	function getItem(key) {
		if(window.localStorage) {
			return window.localStorage.getItem(key);
		} else {
			return getCookie(key);
		}
	}

	/**
	 * ajax请求方法, POST 方式
	 * @param {Object} url
	 * @param {Object} data
	 * @param {Object} successFn
	 * @param {Object} errFn
	 */
	function ajaxPost(url, data, successFn, errFn) {

		// 防止重复点击
		if(loadedUrl == url) {
			return;
		}

		//更新url
		loadedUrl == url;
		//加载层
		var loadingLayer = layer.load(1, {
			shade: [0.1, '#000'] //0.1透明度的白色背景
		});

		//data = encodeURI(data);
		$.ajax({
			url: url,
			type: "POST",
			data: data,
			dataType: "json",
			traditional: true,
			success: function(data) {
				// 关闭loading
				layer.close(loadingLayer);
				loadedUrl = '';
				successFn && successFn(data);
			},
			error: function() {
				// 关闭loading
				layer.close(loadingLayer);
				loadedUrl = '';
				errFn && errFn();
			}
		});

	}
	
	/**
	 * ajax请求方法, POST 方式 上传文件
	 * @param {Object} url
	 * @param {Object} data
	 * @param {Object} successFn
	 * @param {Object} errFn
	 */
	function ajaxPostFile(url, data, successFn, errFn) {

		// 防止重复点击
		if(loadedUrl == url) {
			return;
		}

		//更新url
		loadedUrl == url;
		//加载层
		var loadingLayer = layer.load(1, {
			shade: [0.1, '#000'] //0.1透明度的白色背景
		});

		//data = encodeURI(data);
		$.ajax({
			url: url,
			type: "POST",
			data: data,
			dataType: "json",
			processData: false,  
      contentType: false,
			success: function(data) {
				// 关闭loading
				layer.close(loadingLayer);
				loadedUrl = '';
				successFn && successFn(data);
			},
			error: function() {
				// 关闭loading
				layer.close(loadingLayer);
				loadedUrl = '';
				errFn && errFn();
			}
		});

	}

	/**
	 * ajax请求方法, GET 方式 
	 * @param {Object} url
	 * @param {Object} data
	 * @param {Object} successFn
	 * @param {Object} errFn
	 */
	function ajaxGet(url, data, successFn, errFn) {
		// 防止重复点击
		if(loadedUrl == url) {
			return;
		}

		//更新url
		loadedUrl == url;
		//加载层
		var loadingLayer = layer.load(1, {
			shade: [0.1, '#000'] //0.1透明度的白色背景
		});
		$.ajax({
			url: url,
			type: "GET",
			data: data,
			dataType: "json",
			success: function(data) {
				// 关闭loading
				layer.close(loadingLayer);
				loadedUrl = '';
				successFn && successFn(data);
			},
			error: function() {
				// 关闭loading
				layer.close(loadingLayer);
				loadedUrl = '';
				errFn && errFn();
			}
		});
		//data = encodeURI(data);

	}

	// vue 分页组件
	Vue.component('v-pagination', {
		data: function() {
			return {
				current: this.currentPage
			}
		},
		props: {
			total: { // 数据总条数
				type: Number,
				default: 0
			},
			display: { // 每页显示条数
				type: Number,
				default: 10
			},
			currentPage: { // 当前页码
				type: Number,
				default: 1
			},
			pagegroup: { // 分页条数
				type: Number,
				default: 5,
				coerce: function(v) {
					v = v > 0 ? v : 5;
					return v % 2 === 1 ? v : v + 1;
				}
			}
		},
		computed: {
			page: function() { // 总页数
				return Math.ceil(this.total / this.display);
			},
			grouplist: function() { // 获取分页页码
				var len = this.page,
					temp = [],
					list = [],
					count = Math.floor(this.pagegroup / 2),
					center = this.current;
				if(len <= this.pagegroup) {
					while(len--) {
						temp.push({
							text: this.page - len,
							val: this.page - len
						});
					};
					return temp;
				}
				while(len--) {
					temp.push(this.page - len);
				};
				var idx = temp.indexOf(center);
				(idx < count) && (center = center + count - idx);
				(this.current > this.page - count) && (center = this.page - count);
				temp = temp.splice(center - count - 1, this.pagegroup);
				do {
					var t = temp.shift();
					list.push({
						text: t,
						val: t
					});
				} while (temp.length);
				if(this.page > this.pagegroup) {
					(this.current > count + 1) && list.unshift({
						text: '...',
						val: list[0].val - 1
					});
					(this.current < this.page - count) && list.push({
						text: '...',
						val: list[list.length - 1].val + 1
					});
				}
				return list;
			}
		},
		methods: {
			setCurrent: function(idx) {
				if(this.current != idx && idx > 0 && idx < this.page + 1) {
					this.current = idx;
					this.$emit('pagechange', this.current);
				}
			}
		},
		template: '<nav class="dataTables_paginate paging_bootstrap"><ul class="pagination"><li :class="{disabled: current == 1}"><a href="javascript:;" @click="setCurrent(current - 1)"> « </a></li><li :class="{disabled: current == 1}"><a href="javascript:;" @click="setCurrent(1)"> 首页 </a></li><li v-for="p in grouplist" :class="{active: current == p.val}"><a href="javascript:;"  @click="setCurrent(p.val)"> {{ p.text }} </a></li><li :class="{disabled: current == page}"><a href="javascript:;" @click="setCurrent(page)"> 尾页 </a></li><li :class="{disabled: current == page}"><a href="javascript:;" @click="setCurrent(current + 1)"> »</a></li> </ul> </nav>"                                                                          @click="setCurrent(p.val)">{{p.text}}</a>      </li>      <li :class="{disabled:current == page}"><a href="javascript:;" @click="setCurrent(page)"> 尾页 </a></li>      <li :class="{disabled:current == page}"><a href="javascript:;" @click="setCurrent(current + 1)"> »</a></li>    </ul>  </nav>'
	})

	/**
	 * 时间格式化
	 * @param {Object} tempDate 毫秒数:1530181511000
	 * @param {Object} tempFmt  格式:yyyy-MM-dd hh:mm:ss  
	 */
	function formatDate(tempDate, tempFmt) {
		var date = tempDate;
		var fmt = tempFmt || 'yyyy-MM-dd hh:mm:ss';
		if(typeof date === 'string' || typeof date === 'number') {
			date = new Date(+tempDate);
		}
		var padLeftZero = function padLeftZero(str) {
			return('00' + str).substr(str.length);
		};

		if(/(y+)/.test(tempFmt)) {
			fmt = fmt.replace(RegExp.$1, ('' + date.getFullYear()).substr(4 - RegExp.$1.length));
		}
		var o = {
			'M+': date.getMonth() + 1,
			'd+': date.getDate(),
			'h+': date.getHours(),
			'm+': date.getMinutes(),
			's+': date.getSeconds()
		};

		for(var k in o) {
			if(new RegExp('(' + k + ')').test(fmt)) {
				var str = '' + o[k];
				fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : padLeftZero(str));
			}
		}
		return fmt;
	}

	/**
	 * vue 时间过滤器
	 * @param {Object} tempDate 毫秒数:1530181511000
	 * @param {Object} tempFmt  格式:yyyy-MM-dd hh:mm:ss 
	 */
	Vue.filter('dateFmt', function(tempDate, tempFmt) {
		var date = tempDate;
		var fmt = tempFmt || 'yyyy-MM-dd hh:mm:ss';
		if(typeof date === 'string' || typeof date === 'number') {
			date = new Date(+tempDate);
		}
		var padLeftZero = function padLeftZero(str) {
			return('00' + str).substr(str.length);
		};

		if(/(y+)/.test(tempFmt)) {
			fmt = fmt.replace(RegExp.$1, ('' + date.getFullYear()).substr(4 - RegExp.$1.length));
		}
		var o = {
			'M+': date.getMonth() + 1,
			'd+': date.getDate(),
			'h+': date.getHours(),
			'm+': date.getMinutes(),
			's+': date.getSeconds()
		};

		for(var k in o) {
			if(new RegExp('(' + k + ')').test(fmt)) {
				var str = '' + o[k];
				fmt = fmt.replace(RegExp.$1, RegExp.$1.length === 1 ? str : padLeftZero(str));
			}
		}
		return fmt;
	});

	function setDateTime(number) {
		return number < 10 ? '0' + number : number
	}

	// 接口地址
	var baseUrl = '';
	
	var pageSize = 12;

	return {
		getUrlParam: getUrlParam,
		isMobile: isMobile,
		ajaxPost: ajaxPost,
		ajaxGet: ajaxGet,
		ajaxPostFile: ajaxPostFile,
		isMoney: isMoney,
		isMail: isMail,
		setItem: setItem,
		getItem: getItem,
		formatDate: formatDate,
		setDateTime: setDateTime,
		baseUrl: baseUrl,
		pageSize: pageSize,
	}
})();