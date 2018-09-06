/**
 * jsj
 * 视频管理
 * 2018年6月30日
 */

var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: [],
		// 昵称
		nickName: '',
		// 状态
		status: '',
		// 视频标题
		videoTitle: '',
		allCheck: false, // 全选
		total: 0, // 记录总条数
		pageSize: LIB.pageSize, // 每页显示条数
		pageNo: 1, // 当前的页数
		offlineReason: '' //下线原因
	},
	//计算属性
	computed: {
		// 选中哪些行
		selectList: function() {
			var dataSource = this.dataSource;
			var list = [];
			for(var i in dataSource) {
				if(dataSource[i].isCheck) {
					list.push(dataSource[i].videoId);
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

		pagechange: function(currentPage) {
			this.pageNo = currentPage;
			// ajax请求, 向后台发送 当前页码, 来获取对应的数据
			this.queryDataList();
		},

		//展开视频标题
		videoShowTitle: function(title) {
			layer.open({
				type: 1,
				title: '视频标题',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['300px', ''],
				content: '<p style="text-align:center;padding:10px;">' + title + '</p>',
				btn: ['关闭'],
				yes: function(index, layero) {
					layer.close(index);
				}
			});
		},
		//播放视频
		videoView: function(url) {
			layer.open({
				type: 1,
				offset: '100px',
				title: '视频内容',
				area: ['300px', ''],
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				content: '<video src=' + url + ' controls="controls" width="100%"></video>',
			});
		},

		//全选
		checkAll: function() {
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
		 * 封禁：type=80
		 * 上线：type=30
		 * 重新上线：type=30
		 */
		handleVideo: function(type, item) {
			var _this = this;
			var dataSource = this.dataSource;
			var newVideoList = [];
			//判断传入的参数是否是对象
			if(item) {
				newVideoList = [item.videoId];
			} else {
				// 未选中
				if(this.selectList.length == 0) {
					layer.alert('请选择一条以上数据进行操作！');
					return;
				}
				newVideoList = this.selectList;
			}
			// 上线
			if(type == 30) {
				this.requestData(30, newVideoList);
			}
			// 封禁
			else if(type == 80) {
				// 清空
				this.offlineReason = '';
				// 批量操作 不弹窗 TODO
				if(newVideoList.length > 1){
					// 请求更新
					_this.requestData(80, newVideoList);
				}else{
					layer.open({
						type: 1,
						title: '视频封禁原因',
						maxmin: true,
						shadeClose: false, //点击遮罩关闭层
						area: ['320px', ''],
						content: $('.offline-reason'),
						btn: ['提交', '取消'],
						yes: function(index, layero) {

							if(!_this.offlineReason) {
								layer.alert('请输入封禁原因！');
								return;
							}
							// 请求更新
							_this.requestData(80, newVideoList);
							// 关闭弹窗
							layer.close(index);
						}
					});
				}
			}

		},
		// 请求接口 上线 或 封禁
		requestData: function(type, newVideoList) {
			var _this = this;
			var param = {
				'ids': newVideoList,
				status: type,
				auditResult: this.offlineReason
			};
			LIB.ajaxPost(LIB.baseUrl + '/video/saveUpperLowerVideoInfo', param, function(res) {
				if(res.code == 200) {
					layer.msg('操作成功！', {
						time: 1500
					});
					var dataSource = _this.dataSource;
					for(var i = newVideoList.length - 1; i >= 0; i--) {
						for(var j in dataSource) {
							if(newVideoList[i] == dataSource[j].videoId) {
								dataSource[j].status = type;
								break;
							}
						}
					}
				} else {
					layer.alert('接口异常，请重试。' + res.message);
				}
			}, function(err) {
				layer.alert('数据请求失败' + err);
			});

		},

		// 查询数据 （记得写分页）
		queryDataList: function(pageNo) {

			var param = {
				nickName: this.nickName,
				status: this.status,
				videoTitle: this.videoTitle,
				current: pageNo || this.pageNo,
				pageSize: this.pageSize,
			}

			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/video/queryVideoInfoList', param, function(res) {

				if(res.code == 200) {
					for(var i in res.data) {
						res.data[i].isCheck = false;
						res.data[i].isForbid = false;
					}
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
		this.queryDataList();
	}
});