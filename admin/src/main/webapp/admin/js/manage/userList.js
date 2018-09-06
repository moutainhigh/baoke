/**
 * xuess
 * 用户管理
 * 2018年6月27日
 */

var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: [],
		// 昵称
		nickName: '',
		// 角色
		userType: '',
		// 状态
		bannedStatus: '',
		// 禁言弹窗
		bannedInfo: {
			nickName: '',
			day: 0, //天
			hours: 0, //小时
			bannedReason: '', //禁言原因
		},
		total: 0, // 记录总条数
		pageSize: LIB.pageSize, // 每页显示条数
		pageNo: 1, // 当前的页数
	},
	//计算属性
	computed: {
		// 禁言时间 目标天数 时间戳  -1为永久禁言
		resDateTime: function() {
			if(this.bannedInfo.day == '-1') {
				return -1;
			} else {
				var day = +this.bannedInfo.day * 24 * 60 * 60 * 1000
				var hours = +this.bannedInfo.hours * 60 * 60 * 1000
				var time = +new Date();
				return time + day + hours;
			}
		}
	},
	//方法
	methods: {

		pagechange: function(currentPage) {
			this.pageNo = currentPage;
			// ajax请求, 向后台发送 当前页码, 来获取对应的数据
			this.queryUserList();
		},

		// 禁言
		stopSpeak: function(item) {
			var _this = this;
			this.bannedInfo.nickName = item.nickName;
			//初始化信息
			this.bannedInfo.day = 0;
			this.bannedInfo.hours = 0;
			this.bannedInfo.bannedReason = '';
			layer.open({
				type: 1,
				title: '禁言',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['500px', ''],
				content: $('.stop-speak'),
				btn: ['提交', '取消'],
				yes: function(index, layero) {

					if(!_this.bannedInfo.day && !_this.bannedInfo.hours) {
						layer.alert('请输入禁言时间！');
						return;
					} else if(!_this.bannedInfo.bannedReason.trim()) {
						layer.alert('请输入禁言原因！');
						return;
					}

					LIB.ajaxPost(LIB.baseUrl + '/user/banned', {
						'ids': [item.userId],
						status: _this.resDateTime == '-1' ? 2 : 1,
						userBannedEndTime: _this.resDateTime,
						userBannedReason: _this.bannedInfo.bannedReason
					}, function(res) {
						if(res.code == 200) {
							layer.msg('禁言成功！', {
								time: 2000
							});
							var tempData = _this.dataSource;

							for(var i = 0; i < tempData.length; i++) {
								if(tempData[i].userId == item.userId) {
									tempData[i].bannedStatus = 1;
									tempData[i].bannedEndTime = _this.resDateTime;
									tempData[i].bannedReason = _this.bannedInfo.bannedReason;
									break;
								}
							}
							// 关闭弹窗
							layer.close(index);
						} else {
							layer.alert('接口异常，请重试。' + res.message);
						}
					}, function(err) {
						layer.alert('数据请求失败' + err);
					});
				}
			});
		},
		// 解除禁言
		cancelSpeak: function(userId) {

			var _this = this;
			
			layer.confirm('是否对该用户解除禁言？', {
			  btn: ['确定','取消']
			}, function(index){
				LIB.ajaxPost(LIB.baseUrl + '/user/banned', {
					'ids': [userId],
					status: 0,
					userBannedEndTime: 0
				}, function(res) {
	
					if(res.code == 200) {
						layer.msg('解除禁言成功！', {
							time: 1500
						});
						var tempData = _this.dataSource;
	
						for(var i = 0; i < tempData.length; i++) {
							if(tempData[i].userId == userId) {
								tempData[i].bannedStatus = 0;
								tempData[i].bannedReason = '';
								break;
							}
						}
					} else {
						layer.alert('接口异常，请重试。' + res.message);
					}
				}, function(err) {
					layer.alert('数据请求失败' + err);
				});
				layer.close(index);
			});
		},

		// 查询数据
		queryUserList: function(pageNo) {

			var param = {
				nickName: this.nickName,
				userType: this.userType,
				bannedStatus: this.bannedStatus,
				current: pageNo || this.pageNo,
				pageSize: this.pageSize,
			}

			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/user/queryUserList', param, function(res) {

				if(res.code == 200) {
					_this.dataSource = res.data || [];
					_this.total = res.pagination.total; // 记录总条数
				} else {
					layer.alert('接口异常，请重试。' + res.message);
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

	},
	//vue实例创建之后
	created: function() {
		// 首屏数据加载
		this.queryUserList();
	}
});