/**
 * jsj
 * 页面广告管理
 * 2018年7月5日
 * last update xuess 2018年7月17日
 */

var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: [],
		// 场景类型
		scenType: 'APP_INDEX_TOP',
		// 状态
		status: '',
		// 上传之后图片得路径
		imgUrl: '',
		// 链接地址
		targetUrl: '',
		// 开始时间
		startTime: '',
		// 结束时间
		endTime: '',
		// 当前时间
		curTime: + new Date(),
		// 发布方式
		releaseMode: 0,
		noDataShow: false, // 没有数据
		total: 0, // 记录总条数
		pageSize: LIB.pageSize, // 每页显示条数
		pageNo: 1, // 当前的页数
	},
	//计算属性
	computed: {

	},
	//方法
	methods: {
		// 分页
		pagechange: function(currentPage) {
			console.log('当前', currentPage, '页');
			this.pageNo = currentPage;
			// ajax请求, 向后台发送 currentPage, 来获取对应的数据
			this.queryDataList();
		},
		/**
		 * 上传图片
		 */
		changeImages: function(event) {
			var _this = this;
			var file = event.target.files[0]
			var reader = new FileReader();
			reader.readAsDataURL(file); // 读出 base64 
			reader.onload = function() {
				//查看文件输出内容
				_this.imgUrl = this.result;
			}
			// file 转成formData
			var formdata = new FormData();
			formdata.append('img', file);

			LIB.ajaxPostFile(LIB.baseUrl + '/banner/uploadBannerImg', formdata, function(res) {
				if(res.code == 200) {
					console.log('上传···', res)
					_this.imgUrl = res.data.imgUrl;
				} else {
					layer.alert('接口异常，请重试！');
				}
			}, function(err) {
				layer.alert('数据请求失败' + err);
			});

		},
		/**
		 * 提示
		 */
		tipMsgShow: function() {
			layer.tips('广告页：建议图片尺寸375*665,小于500KB;<br>banner：335*140,小于200KB', '.help-button', {
				tips: [1, '#3595CC'],
				time: 4000
			});
		},
		
		/**
		 * 修改banner 状态
		 */
		updateBanner: function(type,item) {
			var _this = this;
			var param = {
				bannerId:item.bannerId,
				scenType: item.scenType,
				status: type,
				imgUrl:item.imgUrl,
				startTime: item.startTime,
				endTime: item.endTime,
			};
			
			LIB.ajaxPost(LIB.baseUrl + '/banner/saveBannerInfo', param, function(res) {
				if(res.code == 200) {
					layer.msg('操作成功！', {
						time: 1500
					});
					// 此处刷新 页面
					window.location.reload();
					
					/*
					 	var dataSource = _this.dataSource;
						for(var i in dataSource) {
							if(dataSource[i].bannerId == item.bannerId) {
								dataSource[i].status = type;
							}
						}
						this.dataSource = $.extend({}, dataSource)
					 */
				} else {
					layer.alert(res.message);
				}
			}, function(err) {
				layer.alert('数据请求失败' + err);
			});
		},

		/**
		 * 添加广告
		 */
		addBanner: function() {
			var _this = this;
			this.scenType = 'APP_INDEX_TOP';
			this.imgUrl = '';
			this.targetUrl = '';
			this.startTime = '';
			this.endTime = '';
			this.releaseMode = 0;

			layer.open({
				type: 1,
				title: '添加banner',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['600px', ''],
				content: $('.add-char'),
				btn: ['保存', '取消'],
				yes: function(index, layero) {

					if(!_this.imgUrl) {
						layer.alert('请上传图片');
						return;
					}
					if(!_this.targetUrl) {
						layer.alert('请输入链接');
						return;
					}
					// 立即发布 把填入的日期删除掉
					if(_this.releaseMode == 0){
						_this.startTime = '';
						_this.endTime = '';
					}else {
						if(!_this.startTime && !_this.endTime){
							layer.alert('请选择起止时间');
							return;
						}
					}
					
					var param = {
						scenType: _this.scenType,
						imgUrl: _this.imgUrl,
						targetUrl: _this.targetUrl,
						status: 1,
						startTime: _this.startTime,
						endTime: _this.endTime,
					};

					LIB.ajaxPost(LIB.baseUrl + '/banner/saveBannerInfo', param, function(res) {
						if(res.code == 200) {
							layer.msg('发布成功！', {
								time: 1500
							});
							// 此处刷新 页面
							window.location.reload();
							
							/*_this.dataSource.push({
								'bannerId': 9999,
								'location': _this.location,
								'imgUrl': _this.imgUrl,
								'targetUrl': _this.targetUrl,
								'endTime': _this.endTime,
								'startTime': _this.startTime,
								status: 1
							});*/
							
							layer.close(index);
						} else {
							layer.alert(res.message);
						}
					}, function(err) {
						layer.alert('数据请求失败' + err);
					});
				}
			});
		},

		// 查询数据 （记得写分页）
		queryDataList: function(pageNo) {

			var param = {
				//scenType: this.scenType,
				//status: this.status,
				current: pageNo || this.pageNo,
				pageSize: this.pageSize,
			}

			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/banner/queryBannerList', param, function(res) {
				if(res.code == 200) {
					_this.dataSource = res.data;
					_this.total = res.pagination.total; // 记录总条数
				} else {
					layer.alert('接口异常，请重试！');
				}
			}, function(err) {
				layer.alert('数据请求失败' + err);
			});
		},
	},
	//监听
	watch: {

	},

	//vue实例创建之前
	beforecreate: function() {

	},
	//dom 加载后
	mounted: function() {
		var _this = this;
		// 时间选择器
		laydate.render({
			elem: '#startChoose',
			type: 'datetime',
			range: true,
			done: function(value, date, endDate) {
				var dateTimeStart = new Date(date.year + '-' + date.month + '-' + date.date + ' ' + date.hours + ':' + date.minutes + ':' + date.seconds);
				var dateTimeEnd = new Date(endDate.year + '-' + endDate.month + '-' + endDate.date + ' ' + endDate.hours + ':' + endDate.minutes + ':' + endDate.seconds);
				_this.startTime = dateTimeStart.getTime();
				_this.endTime = dateTimeEnd.getTime();
			}
		});

	},
	//vue实例创建之后
	created: function() {
		this.queryDataList();
	}
});