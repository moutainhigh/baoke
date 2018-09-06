/**
 * jsj
 * 敏感词管理
 * 2018年7月5日
 */

var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: [],
		name: '',// 关键字限制敏感词
		infoContent: '',//输入的限制内容
		entrytTime: '',// 录入时间
		addUser: '产品运营',// 账号
		dateInfo: { year: '', month: '', day: '', hour: '', min: '' },// 时间
		sendUp: false,// 设置发送方式，默认立即发送
		checkIdList: [], // 记录选中的id 
		allCheck: false,// 全选
		noDataShow: false,// 没有数据
		total: 0, // 记录总条数
		pageSize: LIB.pageSize, // 每页显示条数
		pageNo: 1, // 当前的页数
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
					list.push(dataSource[i].blackKeyWordId);
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

		pagechange: function (currentPage) {
			console.log('当前', currentPage, '页');
			this.pageNo = currentPage;
			// ajax请求, 向后台发送 currentPage, 来获取对应的数据
			this.queryUserList();
		},

		//添加敏感词
		addSensitive: function () {
			var that = this;
			var dateInfo = that.dataInfo;
			dateInfo = {
				year: new Date().getFullYear(),
				month: LIB.setDateTime(new Date().getMonth() + 1),
				day: LIB.setDateTime(new Date().getDate()),
				hour: LIB.setDateTime(new Date().getHours()),
				min: LIB.setDateTime(new Date().getMinutes())
			}
			this.dateInfo = $.extend({}, dateInfo)
			that.entrytTime = that.dateInfo.year + '' + that.dateInfo.month + '' + that.dateInfo.day + '';

			layer.open({
				type: 1,
				title: '敏感词添加',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['600px', ''],
				content: $('.add-char'),
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					if (that.infoContent == '') {
						alert('不能为空');
						return
					}
					var param = {
						name: that.infoContent,
					}
					LIB.ajaxPost(LIB.baseUrl +'/interact/saveBlackKeyWord', param, function (res) {
						if (res.code == 200) {
							that.queryUserList(1);
							// var num = that.dataSource.length + 1;
							// that.dataSource.push({ 'blackKeyWordId': num, 'name': that.infoContent, 'createTime': that.entrytTime, 'bkUserName': that.addUser });
							that.infoContent = '';
							that.dateInfo = "";
							layer.close(index);
						} else {
							layer.alert('接口异常，请重试。' + res.message);
						}
					}, function (err) {

						layer.alert('数据请求失败' + err);

					});
				
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
		 * 删除敏感词
		 * 
		 */
		delSensitive: function () {
			var that = this;
			var delList = "";
			// 未选中
			if (this.selectList.length == 0) {
				layer.alert('请选择一条以上数据进行操作！');
				return;
			}
			delList=this.selectList;
			layer.open({
				type: 1,
				title: '确定要删除吗',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['200px', ''],
				content: '',
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					// 执行删除操作
					var param = {
						'ids': delList,
					};
					LIB.ajaxPost(LIB.baseUrl +'/interact/deleteBlackKeyWordByIds', param, function (res) {
						if (res.code == 200) {
							layer.msg('删除成功！', {
								time: 1500
							});
							var dataSource = that.dataSource;
							for (var i = delList.length - 1; i >= 0; i--) {
								for (var j in dataSource) {
									if (delList[i] == dataSource[j].blackKeyWordId) {

										dataSource.splice(j, 1)
									}
								}
							}
							if (dataSource.length == 0) {
								that.noDataShow = true;
							}
							that.handleSeen = false;
							that.allCheck = false;
							for (var i in dataSource) {
								dataSource[i].isCheck = false;
							}
							layer.close(index);

						} else {
							layer.alert('接口异常，请重试。' + res.message);
						}
					}, function (err) {
						layer.alert('数据请求失败' + err);
					});

				}
			});
		},

		// 查询数据 （记得写分页）
		queryUserList: function (pageNo) {

			var param = {
				name: this.name,
				pageNo: pageNo || this.pageNo,
				pageSize: this.pageSize,
			}

			var _this = this;
			LIB.ajaxPost(LIB.baseUrl +'/interact/queryBlackKeyWordListByPage', param, function (res) {
				if (res.code == 200) {
					// 写入是否可操作 和 checkbox 是否被选中
					for (var i in res.data) {
						res.data[i].isCheck = false;
					}
					_this.dataSource = res.data || [];
					_this.total = res.pagination.total; // 记录总条数
				} else {
					layer.alert('接口异常，请重试。' + res.message);
				}
			}, function (err) {

				layer.alert('数据请求失败' + err);

			});
		},
	},
	//监听
	watch: {

	},

	//vue实例创建之前
	beforecreate: function () {

	},
	//dom 加载后
	mounted: function () {

	},
	//vue实例创建之后
	created: function () {
		//
		this.queryUserList();
	}
});