//公用方法
var LIB = (function() {

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

	function ajaxGet(url, data, successFn, errFn) {

		axios.get(url, {
				params: data,
			})
			.then(function(response) {
				successFn(response);
			})
			.catch(function(error) {
				errFn(error);
				if(error.response) {
					// 请求已发出，但服务器响应的状态码不在 2xx 范围内
					console.log(error.response.data);
					console.log(error.response.status);
					console.log(error.response.headers);
				} else {
					// Something happened in setting up the request that triggered an Error
					console.log('Error', error.message);
				}
				console.log(error.config);
			});
	}

	function ajaxPost(url, data, successFn, errFn) {
		axios.post(url + '?' + data)
			.then(function(response) {
				successFn(response);
			})
			.catch(function(error) {
				if(error.response) {
					// 请求已发出，但服务器响应的状态码不在 2xx 范围内
					console.log(error.response.data);
					console.log(error.response.status);
					console.log(error.response.headers);
				} else {
					// Something happened in setting up the request that triggered an Error
					console.log('Error', error.message);
				}
				console.log(error.config);
				errFn(error);
			});
	}

	function ajaxPatch(url, data, successFn, errFn) {
		axios.patch(url + '?' + data)
			.then(function(response) {
				successFn(response);
			})
			.catch(function(error) {
				errFn(error);
				if(error.response) {
					// 请求已发出，但服务器响应的状态码不在 2xx 范围内
					console.log(error.response.data);
					console.log(error.response.status);
					console.log(error.response.headers);
				} else {
					// Something happened in setting up the request that triggered an Error
					console.log('Error', error.message);
				}
				console.log(error.config);
			});
	}
	
	return {
		getUrlParam: getUrlParam,
		isMobile: isMobile,
		ajaxGet: ajaxGet,
		ajaxPatch: ajaxPatch,
		ajaxPost: ajaxPost,
		isMoney: isMoney,
		isMail: isMail,
		setItem: setItem,
		getItem: getItem,
	}
})();