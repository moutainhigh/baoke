function checkMobile(mobile){
	if(mobile.length<=0 || Number(mobile)>19999999999 || Number(mobile)<10000000000){
		return false;
	}
	return true;
}

function checkAlipay(alipay){
	if(checkMobile(alipay)){
		return true;
	}else{
		var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
		if(!myreg.test(alipay)){
			return false;
		}
		return true;
	}
}