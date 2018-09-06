/**
 * zmm
 * 视频审核
 * 2018年6月29日
 */
var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: [],
		videoItem: '',
		// 弹窗展示 商品信息
		showPrdData: [],
		// 昵称
		nickName: '',
		//标题
		videoTitle: '',
		// 状态
		status: '',
		failInfo: '',
		total: 0, // 记录总条数
		pageSize: LIB.pageSize, // 每页显示条数
		pageNo: 1, // 当前的页数
	},
	//计算属性
	computed: {

	},
	//方法
	methods: {

		//点击拒接 通过 方法
		cancelSpeak: function(type, val) {
			var that = this;
			var dataSource = that.dataSource;

			for(var i in dataSource) {
				if(dataSource[i].videoId == val) {

					if(type == "1") {

						that.stopSpeak(val, 20, i);
					} else {

						that.requestAudit(val, 30, i);
					}
				}
			}
		},

		pagechange: function(currentPage) {
			this.pageNo = currentPage;
			this.queryVideoInfoList();
		},

		// 点击拒绝弹窗
		stopSpeak: function(id, status, inx) {
			var that = this;
			that.failInfo = '';
			layer.open({
				type: 1,
				title: '审核失败原因',
				maxmin: true,
				maxWidth: "500",
				shadeClose: false, //点击遮罩关闭层
				scrollbar: false,
				content: $('.stop-speak'),
				btn: ['确认'],
				yes: function(index, layero) {
					if(!that.failInfo) {

						layer.alert('请输入失败原因！');
						return;
					}
					// console.log(that.failInfo);
					that.requestAudit(id, status, inx);
					layer.close(index);

				}

			});
		},

		//播放
		playVideo: function(item) {

			videoItem = item;
			this.videoItem = $.extend({}, videoItem);

			layer.open({
				type: 1,
				title: item.title,
				maxmin: true,
				offset: '200px',
				area: ['300px', ''],
				shadeClose: false, //点击遮罩关闭层
				content: $('.play-video'),
				btn: [],
				yes: function(index, layero) {
					// 这里是设置禁言请求 TODO
					layer.close(index);
				}
			});
		},

		// 关联商品弹窗
		commodityDetail: function(item) {
			var _this = this;
			this.showPrdData = item.itemList;

			layer.open({
				type: 1,
				title: ['商品详情', 'text-align:center;'],
				area: ['800px', '300px'],
				shadeClose: false, //点击遮罩关闭层
				content: $('.commodity-detail'),
				end: function() {
					_this.showPrdData = '';
				},
			});
		},

		// 视频列表查询数据 （记得写分页）
		queryVideoInfoList: function(pageNo) {
			var param = {
				status: this.status,
				videoTitle: this.videoTitle,
				nickName: this.nickName,
				current: pageNo || this.pageNo,
				pageSize: this.pageSize,
			}

			var _this = this;

			LIB.ajaxPost(LIB.baseUrl + '/video/queryVideoInfoList', param, function(res) {

				if(res.code == 200) {
					for(var i in res.data) {
						res.data[i].isIgnore = true;
						res.data[i].isReport = true;
					}
					_this.total = res.pagination.total;
					_this.dataSource = res.data || [];
				} else {
					layer.alert('接口异常，请重试！');
				}
			}, function(err) {

				layer.alert('数据请求失败' + err);

			});
		},

		//视频审核接口
		requestAudit: function(id, status, inx) {
			var that = this;
			var param = {
				videoId: id,
				status: status,
				auditResult: that.failInfo
			}

			    
			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/video/auditVideoInfo', param, function(res) {
				if(res.code == 200) {
					if(status == 20) {
						that.dataSource[inx].isReport = false;
						that.dataSource[inx].status = '20'
					} else {
						that.dataSource[inx].isIgnore = false;
						that.dataSource[inx].status = '30'
					}
				} else {
					layer.alert('接口异常，请重试！');
				}
			}, function(err) {

				layer.alert('数据请求失败' + err);

			});
		}

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
		//
		this.queryVideoInfoList();
	}
});