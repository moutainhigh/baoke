/**
 * jsj
 * 评论管理
 * 2018年6月28日
 * lastupdate xuess 2018年7月16日
 */

var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: [],
		// 昵称
		nickName: '',
		// 标题
		title: '',
		allCheck: false, // 全选
		total: 0, // 记录总条数
		pageSize: LIB.pageSize, // 每页显示条数
		pageNo: 1, // 当前的页数
		// 禁言弹窗
		bannedInfo: {
			nickName: '',
			day: 0, //天
			hours: 0, //小时
			bannedReason: '', //禁言原因
		},
	},
	//计算属性
	computed: {
		// 禁言时间 -1为永久禁言
		resDateTime: function() {
			if(this.bannedInfo.day == '-1') {
				return -1;
			} else {
				var day = +this.bannedInfo.day * 24 * 60 * 60 * 1000
				var hours = +this.bannedInfo.hours * 60 * 60 * 1000
				var time = +new Date();
				return time + day + hours;
			}
		},
		// 选中哪些行 checkBox
		selectList: function() {
			var dataSource = this.dataSource;
			var list = [];
			for(var i in dataSource) {
				if(dataSource[i].isCheck) {
					list.push(dataSource[i].commentInfo.commentId);
				}
			}
			if(list.length == dataSource.length && dataSource.length != 0) {
				this.allCheck = true;
			} else {
				this.allCheck = false;
			}
			return list;
		},
	},
	//方法
	methods: {
		// 分页
		pagechange: function(currentPage) {
			this.pageNo = currentPage;
			// ajax请求, 向后台发送 当前页码, 来获取对应的数据
			this.queryDataList();
		},

		//展开评论内容
		showCommentDetails: function(content) {
			layer.open({
				type: 1,
				title: '评论详情',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['400px', ''],
				content: '<p style="text-align:center;padding:10px;">' + content + '</p>',
				btn: ['关闭'],
				yes: function(index, layero) {
					layer.close(index);
				}
			});
		},
		//全选
		checkAll: function() {
			var that = this;
			var dataSource = this.dataSource;
			var allCheck = this.allCheck;
			if(allCheck == false) {
				for(var i in dataSource) {
					dataSource[i].isCheck = !allCheck;
				}
				allCheck = true;
			} else {
				for(var i in dataSource) {
					dataSource[i].isCheck = !allCheck;
				}
			}
		},

		/**
		 * 删除评论
		 * typeof 判断传入的参数类型()
		 */
		delComment: function(id) {
			var that = this;
			var delList = "";
			if(typeof id == "string" || typeof id == "number") {
				delList = [id]
			} else {
				// 未选中
				if(this.selectList.length == 0) {
					layer.alert('请选择一条以上数据进行操作！');
					return;
				}
				delList = that.selectList;
			}

			layer.open({
				type: 1,
				title: '确定要删除吗',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['200px', ''],
				content: '',
				btn: ['确定', '取消'],
				yes: function(index, layero) {

					var param = {
						'ids': delList,
					};
					// 执行删除操作
					LIB.ajaxPost(LIB.baseUrl + '/interact/deleteVideoCommentByIds', param, function(res) {
						if(res.code == 200) {
							layer.msg('删除成功！', {
								time: 1500
							});
							var dataSource = that.dataSource;
							console.log(dataSource);
							for(var i = delList.length - 1; i >= 0; i--) {
								for(var j in dataSource) {
									if(delList[i] == dataSource[j].commentInfo.commentId) {
										dataSource.splice(j, 1);
										break;
									}
								}
							}
							
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

		/**
		 * 禁言用户
		 */
		cancelSpeak: function(userId, nickName) {

			var dataSource = this.dataSource;
			var checkList = [];
			if(typeof userId == "string" || typeof userId == "number") {
				checkList = [userId]
			} else {
				// 未选中
				if(this.selectList.length == 0) {
					layer.alert('请选择一条以上数据进行操作！');
					return;
				}
				checkList = this.selectList;
			}
			console.log(checkList);

			var _this = this;
			if(nickName) {
				this.bannedInfo.nickName = nickName;
			} else {
				this.bannedInfo.nickName = '所选用户';
			}
			// 初始化数据
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

					// 这里是设置禁言请求 TODO
					if(!_this.bannedInfo.day && !_this.bannedInfo.hours) {
						layer.alert('请输入禁言时间！');
						return;
					} else if(!_this.bannedInfo.bannedReason.trim()) {
						layer.alert('请输入禁言原因！');
						return;
					}

					LIB.ajaxPost(LIB.baseUrl + '/user/banned', {
						'ids': checkList,
						status: _this.resDateTime == '-1' ? 2 : 1,
						userBannedEndTime: _this.resDateTime,
						userBannedReason: _this.bannedInfo.bannedReason
					}, function(res) {

						if(res.code == 200) {
							layer.msg('禁言成功！', {
								time: 2000
							});
							for(var i in checkList) {
								for(var j in dataSource) {
									if(checkList[i] == dataSource[j].commentInfo.userId) {
										dataSource[j].isForbid = true;
										dataSource[j].isCheck = false;
										dataSource[j].status = 0;
//										break;
									}
								}
							}
							_this.dataSource = $.extend([], dataSource)
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

		// 查询数据 （记得写分页）
		queryDataList: function(pageNo) {

			var param = {
				nickName: this.nickName,
				title: this.title,
				current: pageNo || this.pageNo,
				pageSize: this.pageSize,
			}

			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/interact/queryVideoCommentList', param, function(res) {

				if(res.code == 200) {
					// 写入是否可操作 和 checkbox 是否被选中
					for(var i in res.data) {
						res.data[i].isCheck = false;
						res.data[i].isForbid = false;
					}
					_this.dataSource = res.data ||[];
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
		// 执行查询
		this.queryDataList();
	}
});