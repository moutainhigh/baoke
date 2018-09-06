/*
*作者：jasmine
*时间：2018-7-19
*描述：分享视频页面
*最后修改：jasmine 2018-7-19 调试主播信息接口
**/
var baseUrl = "http://act.baokekeji.com";
var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		dataSource: {},
		isPlay: false,
		clientHeight: '',
		videoId: LIB.getUrlParam('videoId'),
		sellerInfo:{},
		toast: '',
		//toast 
		isOpen: false,	
	},
	//计算属性
	computed: {

	},
	//方法
	methods: {
		playClick: function () {
			this.isPlay = !this.isPlay;
			this.$refs.videoPage.play();
		},
		pausetest: function () {
			this.isPlay = !this.isPlay;
		},
		queryInfo: function () {
			var param = {
				'videoId': this.videoId
			}
		
			var _this = this;
			LIB.ajaxPost(baseUrl + '/video/queryVideoInfoByVideoId', 'videoId=' + this.videoId, function (res) {
				if(res.data.code===200){
					_this.dataSource = res.data.data;
					_this.sellerInfo=res.data.data.sellerInfo;
					
				}else{
					this.openToast(res.message);
					return;
				}
			},function(err){
				console.log(err);
			})
		},
		//toast 弹窗
		openToast:function(toast) {
			var _this=this;
			this.toast = toast
			this.isOpen = true
			let timer = setTimeout(function() {
				_this.isOpen = false
				clearTimeout(timer)
			}, 2000)
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
		this.clientHeight = document.documentElement.clientHeight
		window.onresize = function temp () {
			this.clientHeight = document.documentElement.clientHeight;
		};
	},
	updated: function () {

	},
	//vue实例创建之后
	created: function () {
		//
		if(!this.videoId){
			this.openToast('参数错误');
			return;
		}
		this.queryInfo();
	}

});