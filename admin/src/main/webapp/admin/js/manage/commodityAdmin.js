/**
 * zmm
 * 商城管理
 * 2018年6月29日
 */
var vm = new Vue({
	el: "#main",
	data: {
		//数据源
		imgUrl: 'https://dummyimage.com/200x100',
		isShowSpe: true,
		txtspe: '',
		upimgUrl: '',
		targetUrl: ''

	},
	//计算属性
	computed: {

	},
	//方法
	methods: {
		//修改专题    
		changespe: function() {
			if(this.isShowSpe == true) { //提示按钮

				this.isShowSpe = false;
				$(".changebtn").text("确认修改");
			} else { //确认按钮

				$(".changebtn").text("修改专题标题");
				var txtsp = $(".speinput").val();
				if(!txtsp == '') {
					this.txtspe = txtsp;
				}
				this.isShowSpe = true;
			}
		},

		changeImage: function(event) {
			var that = this;
			var file = event.target.files[0]
			var reader = new FileReader();
			reader.readAsDataURL(file); // 读出 base64 
			reader.onload = function() {
				//查看文件输出内容
				that.imgUrl = this.result;
				that.uploadSuccess = true;
				//查看文件内容字节大小
				//console.log(new Blob([this.result]))
			}
			var formdata = new FormData();
			formdata.append('img', file);
			LIB.ajaxPostFile(LIB.baseUrl + '/mall/uploadMallRecommendImg', formdata, function(res) {
				if(res.code == 200) {
					layer.msg('上传成功！', {
						time: 1500
					});
				} else {
					layer.alert('接口异常，请重试！');
				}

				that.upimgUrl = res.data.imgUrl;

			}, function(err) {
				layer.alert('数据请求失败' + err);   
			});
		},
		//保存接口  
		requestSave: function() {

			var that = this;
			var param = {
				title: that.txtspe,
				imgUrl: that.upimgUrl || that.imgUrl,
				targetUrl: that.targetUrl,
				mallItemRecommendId: that.mallItemRecommendId ? that.mallItemRecommendId : '',
				parentId: that.parentId ? that.parentId : '',
				sort: 1
			};

			LIB.ajaxPost(LIB.baseUrl + '/mall/saveMallRecommendInfo', param, function(res) {
				if(res.code == 200) {
					layer.msg('保存成功！', {
						time: 1500
					});
				} else {
					layer.alert('接口异常，请重试！');
				} 
			}, function(err) {
				layer.alert('数据请求失败' + err);
			});
		},

		//查询推荐接口
		queryMallRecommendList: function() {

			var that = this;
			var param = {
				current: 1,
				pageSize: 1,
			}

			LIB.ajaxPost(LIB.baseUrl + '/mall/queryMallRecommendList', param, function(res) {   
				if(res.code == 200) {
					var objData = res.data.mallRecommendList.length > 0 ? res.data.mallRecommendList[0].mallRecommendList[0] : null;
					console.log(objData);
					if(objData) {
						that.mallItemRecommendId = objData.mallItemRecommendId;
						that.parentId = objData.parentId;
						that.sort = objData.sort;
						that.txtspe = res.data.mallRecommendList[0].title;
						that.imgUrl = objData.imgUrl;
						that.targetUrl = objData.targetUrl;
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
		// 请求数据
		this.queryMallRecommendList();
	}
});