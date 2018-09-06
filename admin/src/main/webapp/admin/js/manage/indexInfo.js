/**
 * jsj
 * 举报管理
 * 2018年7月4日
 */

var vm = new Vue({
	el: "#main",
	data: {
		dataSource: {},//数据源
		hasDate: false,// 是否有数据
	},
	//计算属性
	computed: {

	},
	//方法
	methods: {
		// 查询数据 （记得写分页）
		queryAdminHome: function () {
			var param = {}
			var _this = this;
			LIB.ajaxPost(LIB.baseUrl + '/queryAdminHome', param, function (res) {
				if (res.code == 200) {

					_this.dataSource = res.data;
					if (_this.dataSource !== '') {
						_this.hasDate = true;
					}
					console.log(_this.dataSource);

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
		this.queryAdminHome();
	}
});