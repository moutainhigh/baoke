/**
 * jsj
 * 举报管理
 * 2018年7月4日
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
		content: '',// 举报内容
		videoTitle: '', // 视频标题
		noDataShow: false,// 没有数据
		total: 0, // 记录总条数
		pageSize: 12, // 每页显示条数
		pageNo: 1, // 当前的页数
	},
	//计算属性
	computed: {

	},
	//方法
	methods: {
		// 忽略举报,下架，type:1==>成功；2==>失败
		handleReport: function (type, val) {
			var that = this;
			var dataSource = that.dataSource;
			var param = {
				'complaintId': val,
				'status': type
			};
			var title = '';
			if (type === 1) {
				title = '是否通知主播并下架'
			} else {
				title = "是否忽略该举报"
			}
			console.log(title);
			layer.open({
				type: 1,
				title: '提示',
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['300px', ''],
				content: '<p style="text-align:center;padding:10px;">' + title + '</p>',
				btn: ['确定', '取消'],
				yes: function (index, layero) {
					LIB.ajaxPost(LIB.baseUrl + '/interact/saveVideoComplaintResult', param, function (res) {
						if (res.code == 200) {
							for (var i in dataSource) {
								if (dataSource[i].complaintId == val) {
									if (type == "1") {
										dataSource[i].isIgnore = false;
										dataSource[i].status = '1'
									} else {
										dataSource[i].isReport = false;
										dataSource[i].status = '2'
									}
									dataSource[i].isForbid = true;
								}
							}
							that.dataSource = $.extend([], dataSource);

						} else {
							layer.alert('接口异常，请重试！');
						}
					}, function (err) {
						layer.alert('数据请求失败' + err);
					});
					layer.close(index);
				}
			});

		},
		pagechange: function (currentPage) {
			console.log('当前', currentPage, '页');
			this.pageNo = currentPage;
			// ajax请求, 向后台发送 currentPage, 来获取对应的数据
			this.queryComplaintList();
		},
		// 查询数据 （记得写分页）
		queryComplaintList: function (pageNo) {
			var param = {
				content: this.content,
				videoTitle: this.videoTitle,
				nickName: this.nickName,
				current: pageNo || this.pageNo,
				pageSize: this.pageSize,
			}
			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/interact/queryVideoComplaintList', param, function (res) {
				console.log(res);
				if (res.code == 200) {
					for (var i in res.data.list) {
						res.data.list[i].isForbid = false;
						res.data.list[i].isIgnore = true;
						res.data.list[i].isReport = true;
					}
					_this.dataSource = res.data.list || [];
					_this.total = res.pagination.total; // 记录总条数
					if (_this.dataSource.length == 0) {
						_this.noDataShow = true;
					}
				} else {
					layer.alert('接口异常，请重试！');
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
		this.queryComplaintList();
	}
});