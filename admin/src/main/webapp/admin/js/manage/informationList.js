/**
 * jsj
 * 信息管理
 * 2018年7月3日
 */

var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: [],
		//分页数据
		pagination: {},
		// 昵称
		nickName: '',
		// 状态
		status: '',
		dateInfo: { year: '', month: '', day: '', hour: '', min: '' },// 时间
		checkedNames: { seller: false, user: false, specifyInfo: false },// 主播，普通用户，指定用户
		specify: '',// 指定用户的输入值
		infoContent: '', // 广播内容
		sendUp: "sendNow",// 设置发送方式，默认立即发送
		noDataShow: false,// 没有数据
		total: 150, // 记录总条数
		pageSize: 10, // 每页显示条数
		pageNo: 1, // 当前的页数
	},
	//计算属性
	computed: {

	},
	//方法
	methods: {
		//主播，普通用户，指定用户的选择判断
		changeCheckbox: function (val) {
			if (val == "seller") {
				this.checkedNames.seller = true;
				this.checkedNames.specifyInfo = false;
				this.specify = '';
			} else if (val == "user") {
				this.checkedNames.user = true;
				this.checkedNames.specifyInfo = false;
				this.specify = '';
			} else {
				this.checkedNames.seller = false;
				this.checkedNames.user = false;
			}
		},
		pagechange: function (currentPage) {
			console.log('当前', currentPage, '页');
			this.pageNo = currentPage;
			// ajax请求, 向后台发送 currentPage, 来获取对应的数据
			this.queryUserList();
		},
		// 查询
		queryUserInfo: function () {
			var that = this;
			var dataSource = that.dataSource;
			console.log('nickName:', that.nickName);
			console.log('videoTitle:', that.videoTitle);

		},
		//展开广播内容
		showInformation: function (content) {
			layer.open({
				type: 1,
				title: '评论详情',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['300px', ''],
				content: '<p style="text-align:center;padding:10px;">' + content + '</p>',
				btn: ['关闭'],
				yes: function (index, layero) {
					layer.close(index);
				}
			});
		},
		//发布广播信息
		postMessage: function () {
			var that = this;
			var param = {};
			var dateInfo = that.dataInfo;
			dateInfo = {
				year: new Date().getFullYear(),
				month: new Date().getMonth() + 1,
				day: new Date().getDate(),
				hour: new Date().getHours(),
				min: new Date().getMinutes()
			}
			console.log(dateInfo);
			this.dateInfo = $.extend({}, dateInfo)
			layer.open({
				type: 1,
				title: '信息编写',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['600px', ''],
				content: $('.stop-speak'),
				btn: ['发送', '取消'],
				yes: function (index, layero) {

					// 这里是设置禁言请求 TODO

					// layer.alert('禁言成功！', {
					// 	title: '提示框',
					// 	icon: 1,
					// });
					var checkedNames = that.checkedNames;
					var sendUp = that.sendUp;
					var infoContent = that.infoContent;
					var checkedNames = that.checkedNames;
					var specify = that.specify;
					var time = "";
					if (sendUp == "sendNow") {
						time = LIB.formatDate(new Date(), 'yyyy-MM-dd hh:mm:ss')
					} else {
						time = that.dateInfo.year + '' + that.dateInfo.month + '' + that.dateInfo.day + '' + that.dateInfo.hour + '' + that.dateInfo.min;
					}

					if (checkedNames['specifyInfo'] == true) {
						if (specify == '') {
							alert("为空");
						}
						return
					} else if (checkedNames['seller'] == false && checkedNames['user'] == false) {
						alert("请选择用户类型");
						return
					}
					if (infoContent == '') {
						alert("请选择广播内容");
						return
					}
					console.log(checkedNames, specify, sendUp, time, infoContent);
					layer.close(index);
				}
			});

		},


		// 查询数据 （记得写分页）
		queryUserList: function (pageNo) {

			var param = {
				nickName: this.nickName,
				status: this.status,
				role: this.role,
				pageNo: pageNo || this.pageNo,
				pageSize: this.pageSize,
			}

			var _this = this;
			LIB.ajaxGet('/dataJson/informationList.json', param, function (res) {

				_this.dataSource = res;
				for (var i in _this.dataSource) {
					_this.dataSource[i].isCheck = false;
					_this.dataSource[i].isForbid = false;
					//	_this.dataSource[i].time = LIB.formatDate(_this.dataSource[i].time,'yyyy-MM-dd hh:mm:ss')

				}
				console.log(_this.dataSource);
			}, function (err) {

				alert('数据请求失败' + err);

			});
		},
	},
	//监听
	watch: {
		// 监听指定用户输入框，有内容，则指定用户✔，(主播，普通用户)不可选中
		specify: function (newVal, oldVal) {
			if (newVal) {
				this.checkedNames.specifyInfo = true;
				this.checkedNames.user = false;
				this.checkedNames.seller = false;
			}
		},

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