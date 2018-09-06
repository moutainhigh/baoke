/**
 * zmm
 * 主播审核
 * 2018年6月29日
 */
var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: [],
		dataClassify: [],
		itemLook: '',
		//分页数据
		pagination: {},
		// 昵称
		nickName: '',
		// 状态
		status: '',
		// 角色
		role: '',
		failInfo: '',
		categoryDictId: '', //查询店铺分类
		categoryIds: '', //查询列表店铺分类

		total: 0, // 记录总条数
		pageSize: LIB.pageSize, // 每页显示条数
		pageNo: 1, // 当前的页数

	},
	//计算属性
	computed: {

	},
	//方法
	methods: {

		cancelSpeak: function(type, val) {
			var that = this;
			var dataSource = that.dataSource;

			for(var i in dataSource) {

				if(dataSource[i].sellerId == val) {

					if(type == "1") {

						that.stopSpeak(val, 30, i);

					} else {

						that.requestAudit(val, 20, i);
					}

				}
			}
		},

		pagechange: function(currentPage) {

			this.pageNo = currentPage;
			this.querySellerAuditList();
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
					that.requestAudit(id, status, inx);
					layer.close(index);
				}
			});
		},
		// 放大图片弹窗
		idCardPopu: function(imgUrl) {
			var json = {
				"data": [ //相册包含的图片，数组格式
					{
						"src": imgUrl, //原图地址
						"thumb": imgUrl //缩略图地址
					}
				]
			}
			layer.photos({
				area: ['500px'],
				photos: json,
				anim: 5
			});
		},

		// 查看
		viewDetail: function(item) {
			var _this = this;
			this.itemLook = item;
			layer.open({
				type: 1,
				maxmin: true,
				shadeClose: false, //点击遮罩关闭层
				area: ['600px', '850px'],
				content: $('.view-detail'),
				btn: ['确认'],
				end: function() {
					_this.itemLook = '';
				},
				yes: function(index, layero) {
					_this.itemLook = '';
					// 这里是设置禁言请求 TODO
					layer.close(index);
				}
			});
		},

		// 主播列表查询数据 （记得写分页）
		querySellerAuditList: function(pageNo) {

			var param = {
				status: this.status,
				sellerNickName: this.nickName,
				current: pageNo || this.pageNo,
				pageSize: this.pageSize,
				categoryIds: this.categoryIds,
			}

			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/seller/querySellerAuditList', param, function(res) {
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

		// 查询店铺分类接口
		requestClassify: function() {
			var param = {
				categoryDictId: this.categoryDictId,
			}

			var that = this;
			LIB.ajaxPost(LIB.baseUrl + '/category/queryCategoryList', param, function(res) {
				if(res.code == 200) {
					that.dataClassify = res.data;

				} else {
					layer.alert('接口异常，请重试！');
				}

			}, function(err) {
				layer.alert('数据请求失败' + err);
			});

		},

		//主播接口
		requestAudit: function(id, status, inx) {
			var that = this;
			var param = {
				sellerId: id,
				status: status,
				auditResult: that.failInfo,
			}

			    
			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/seller/auditSellerInfo', param, function(res) {
				if(res.code == 200) {
					if(status == 30) {
						that.dataSource[inx].isReport = false;
						that.dataSource[inx].status = '30'
					} else {
						that.dataSource[inx].isIgnore = false;
						that.dataSource[inx].status = '20'
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
		this.querySellerAuditList();
		this.requestClassify();
	}
});