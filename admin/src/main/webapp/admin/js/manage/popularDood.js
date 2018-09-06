/**
 * zmm
 * 人气热门商品选择 
 * 2018年7月6日
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
		// 忽略举报,下架，type:1==>忽略；2==>下架
		handleReport: function (type,val) {
			var that=this;
			var dataSource=that.dataSource;
			for(var i in dataSource){
				if(dataSource[i].id==val){
					if(type=="1"){
						dataSource[i].isReport=false;
						dataSource[i].status='1'
					}else{
						// dataSource[i].isIgnore=false;
                        // dataSource[i].status='2'
                        if(dataSource[i].savestatue=='0'){
							dataSource[i].savestatue='1'
						}else if(dataSource[i].savestatue=='1'){
							dataSource[i].savestatue='0'
						}
					}
					dataSource[i].isForbid=true;
					
				}
			}
			this.dataSource=$.extend({}, dataSource);
			console.log(this.dataSource);
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
			console.log('titile:', dataSource.title);
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
			LIB.ajaxGet('/dataJson/popularDood.json', param, function (res) {
				_this.dataSource = res;
			
			}, function (err) {

				alert('数据请求失败' + err);

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